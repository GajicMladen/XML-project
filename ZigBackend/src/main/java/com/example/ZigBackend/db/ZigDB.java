package com.example.ZigBackend.db;

import com.example.ZigBackend.gen.Z1Classes.ZahtevZaPriznanjeZiga;
import com.example.ZigBackend.marshal.Marshal;
import com.example.ZigBackend.gen.Z1Classes.EStatusResenja;
import com.example.ZigBackend.service.MarshallerZig;
import com.example.ZigBackend.utils.AuthenticationUtilitiesExist;
import com.example.ZigBackend.utils.DBSetUp;
import org.exist.xmldb.EXistResource;
import org.exist.xmldb.RemoteXMLResource;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.*;
import org.xmldb.api.modules.CollectionManagementService;
import org.xmldb.api.modules.XMLResource;
import org.xmldb.api.modules.XPathQueryService;
import org.xmldb.api.modules.XQueryService;

import javax.xml.transform.OutputKeys;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import static com.example.ZigBackend.utils.Utils.formatNameOfRequestForZig;

public class ZigDB {

    public static int getNumberOfRequests() {
        try {
            AuthenticationUtilitiesExist.ConnectionProperties conn = AuthenticationUtilitiesExist.loadProperties();
            String collectionId = DBSetUp.setupDBConnection(conn);
            Collection col = getOrCreateCollection(collectionId, conn);
            return col.getResourceCount();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static void save(ZahtevZaPriznanjeZiga zahtevZaPriznanjeZiga) {
        OutputStream marshaledPatent = Marshal.marshalZig(zahtevZaPriznanjeZiga);
        try {
            AuthenticationUtilitiesExist.ConnectionProperties conn = AuthenticationUtilitiesExist.loadProperties();
            String collectionId = DBSetUp.setupDBConnection(conn);
            String documentId = formatNameOfRequestForZig(zahtevZaPriznanjeZiga.getBrojZahteva(), ".xml");
            Collection col = getOrCreateCollection(collectionId, conn);
            XMLResource res = (XMLResource) col.createResource(documentId, XMLResource.RESOURCE_TYPE);
            res.setContent(marshaledPatent);
            col.storeResource(res);
            System.out.println("[INFO] Successful writing to ExistDB!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ZahtevZaPriznanjeZiga getZahtev(String brojPrijave) {
        try {
            Collection col = getCollection();
            XMLResource res = (XMLResource) col.getResource(brojPrijave.replace('/', '-') + ".xml");
            if (res != null) {
                return MarshallerZig.unmarshal(res);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static RemoteXMLResource getZahtevXMLResource(String brojPrijave) {
        try {
            Collection col = getCollection();
            XMLResource res = (XMLResource) col.getResource(brojPrijave.replace('/', '-') + ".xml");
            if (res != null) {
                return (RemoteXMLResource) res;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static int getNumberOfZahtevi() {
        try {
            AuthenticationUtilitiesExist.ConnectionProperties conn = AuthenticationUtilitiesExist.loadProperties();
            String collectionId = DBSetUp.setupDBConnection(conn);
            Collection col = getOrCreateCollection(collectionId, conn);
            return col.getResourceCount();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static List<XMLResource> getAllByYear(String yy) {
//        String xpathExp = "//sz:Broj_prijave_ziga[text()='" + "/" + yy + "']/ancestor::sz:Zahtev_za_priznanje_ziga";
        String xpathExp = "//zahtev_za_priznanje_ziga[starts-with(@datum_zahteva,'"+yy+"')]";// /ancestor::sz:Zahtev_za_priznanje_ziga";

        return getAllByFilter(xpathExp);
    }

    public static List<XMLResource> getAllByStatus(EStatusResenja status) {
//        String xpathExp = "//sz:Status[text()='" + status + "']/ancestor::sz:Zahtev_za_priznanje_ziga";
        String xpathExp = "//zahtev_za_priznanje_ziga//status[text()='"+status+"']/..";
        return getAllByFilter(xpathExp);
    }

    private static List<XMLResource> getAllByFilter(String xpathExp){
        List<XMLResource> resources = new ArrayList<XMLResource>();

        try {
            Collection col = getCollection();
            XQueryService xpathService = getXPathQueryServiceForZig(col);
            ResourceSet result = xpathService.query(xpathExp);
            collectXMLResourcesFromResult(result, resources, col);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resources;
    }

    private static XQueryService getXPathQueryServiceForZig(Collection col) throws XMLDBException {
//        XPathQueryService xpathService = (XPathQueryService) col.getService("XPathQueryService", "1.0");
//        xpathService.setProperty("indent", "yes");
        XQueryService xpathService = (XQueryService) col.getService("XQueryService","1.0");
        xpathService.setProperty("indent","yes");
        return xpathService;
    }

    private static void collectXMLResourcesFromResult(ResourceSet result, List<XMLResource> resources, Collection col) throws XMLDBException {
        ResourceIterator iter = result.getIterator();
        XMLResource resource = null;

        try {
            while (iter.hasMoreResources()) {
                resource = (XMLResource) iter.nextResource();
                resources.add(resource);
            }
        } finally {
            if (resource != null) {
                ((EXistResource) resource).freeResources();
            }
            if (col != null) {
                col.close();
            }
        }
    }

    public static List<XMLResource> searchResourcesForText(List<String> words, boolean matchCase) throws Exception {
        Collection col = getCollection();
        List<XMLResource> resources = new ArrayList<XMLResource>();
        XMLResource resource = null;

        try {
            // ovde ne moze da se koristi getXPathQueryServiceForZig() jer postavi namespace, informacija o namespace-u se prenese na Resource
            // i kasnije ne moze da se izmarshaluje zbog ns ... zato filtraciju obradjeni/neobgradjeni ne raditi preko baze
            XPathQueryService xPathQueryService = (XPathQueryService) col.getService("XPathQueryService", "1.0");
            xPathQueryService.setProperty("indent", "yes");
            String xPathExp = createXPathExpressionForTextSearch(words, matchCase);
            ResourceIterator iter = xPathQueryService.query(xPathExp).getIterator();

            while (iter.hasMoreResources()) {
                Resource res =  iter.nextResource();
                System.out.println(res.getContent());
                RemoteXMLResource resxml = (RemoteXMLResource) res;
                resources.add(resxml);
            }

            return resources;
        } finally {
            if (resource != null) {
                ((EXistResource) resource).freeResources();
            }
            if (col != null) {
                col.close();
            }
        }
    }

    private static String createXPathExpressionForTextSearch(List<String> words, boolean matchCase) { //"//sz:Prilog//sz:Tip_priloga[text()='DOKAZ_O_UPLATI_TAKSE']//..//sz:Status_priloga='NIJE_PREDATO'"
        int wordsDone = 0;
        String xpath = "/*[";

        for (String word : words){
            xpath = xpath.concat("contains(");

            if (!matchCase) {
                xpath = xpath.concat("lower-case(.)");
                word = word.toLowerCase();
            } else {
                xpath = xpath.concat(".");
            }

            xpath = xpath.concat(", ").concat("\"").concat(word).concat("\"");
            xpath = xpath.concat(")");

            wordsDone++;
            if (wordsDone != words.size()){
                xpath = xpath.concat(" and ");
            }
        }

        xpath = xpath.concat("]");

        return xpath;
    }

    // use this one
    private static Collection getCollection() throws XMLDBException, ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
        AuthenticationUtilitiesExist.ConnectionProperties conn = AuthenticationUtilitiesExist.loadProperties();
        String collectionId = DBSetUp.setupDBConnection(conn);
        Collection col = getOrCreateCollection(collectionId, conn);
        col.setProperty(OutputKeys.INDENT, "yes");

        return col;
    }

    private static Collection getOrCreateCollection(String collectionUri, AuthenticationUtilitiesExist.ConnectionProperties conn) throws XMLDBException {
        return getOrCreateCollection(collectionUri, 0, conn);
    }

    private static Collection getOrCreateCollection(String collectionUri, int pathSegmentOffset, AuthenticationUtilitiesExist.ConnectionProperties conn) throws XMLDBException {

        Collection col = DatabaseManager.getCollection(conn.uri + collectionUri, conn.user, conn.password);
        // create the collection if it does not exist
        if (col == null) {

            if (collectionUri.startsWith("/")) {
                collectionUri = collectionUri.substring(1);
            }

            String pathSegments[] = collectionUri.split("/");

            if (pathSegments.length > 0) {
                StringBuilder path = new StringBuilder();

                for (int i = 0; i <= pathSegmentOffset; i++) {
                    path.append("/" + pathSegments[i]);
                }

                Collection startCol = DatabaseManager.getCollection(conn.uri + path, conn.user, conn.password);

                if (startCol == null) {

                    // child collection does not exist

                    String parentPath = path.substring(0, path.lastIndexOf("/"));
                    Collection parentCol = DatabaseManager.getCollection(conn.uri + parentPath, conn.user, conn.password);

                    CollectionManagementService mgt = (CollectionManagementService) parentCol.getService("CollectionManagementService", "1.0");

                    System.out.println("[INFO] Creating the collection: " + pathSegments[pathSegmentOffset]);
                    col = mgt.createCollection(pathSegments[pathSegmentOffset]);

                    col.close();
                    parentCol.close();

                } else {
                    startCol.close();
                }
            }
            return getOrCreateCollection(collectionUri, ++pathSegmentOffset, conn);
        } else {
            return col;
        }
    }

}
