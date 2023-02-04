package main.java.com.xws.a1document.repository;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.OutputKeys;

import org.exist.xmldb.EXistResource;
import org.exist.xupdate.XUpdateProcessor;
import org.springframework.stereotype.Repository;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.CollectionManagementService;
import org.xmldb.api.modules.XMLResource;
import org.xmldb.api.modules.XQueryService;
import org.xmldb.api.modules.XUpdateQueryService;

import main.java.com.xws.a1document.dto.ResenjeDTO;
import main.java.com.xws.a1document.util.ExistAuthUtilities;
import main.java.com.xws.a1document.util.ExistAuthUtilities.ConnectionProperties;
import main.java.com.xws.a1document.xml.model.ObrazacA1;

@Repository
public class A1Repository {

	
	public void save(String documentName, OutputStream outputStream) throws Exception {

        String documentId = documentName + ".xml";
        String collectionId = "/db/service/a/requests";
        
        ConnectionProperties conn = ExistAuthUtilities.loadProperties();
        Class<?> cl = Class.forName(conn.driver);
        Database database = (Database) cl.newInstance();
        database.setProperty("create-database", "true");
        DatabaseManager.registerDatabase(database);
        
        Collection col = null;
        XMLResource res = null;
        OutputStream os = new ByteArrayOutputStream();

        try {
            col = getOrCreateCollection(conn, collectionId);
            System.out.println("[INFO] Retrieving the collection: " + collectionId);
            System.out.println("[INFO] Inserting the document: " + documentId);
            res = (XMLResource) col.createResource(documentId, XMLResource.RESOURCE_TYPE);

            // link the stream to the XML resource
            res.setContent(outputStream);
            System.out.println("[INFO] Storing the document: " + res.getId());

            col.storeResource(res);
            System.out.println("[INFO] Done.");

        } finally {
        	if(res != null) {
                try { 
                	((EXistResource)res).freeResources(); 
                } catch (XMLDBException xe) {
                	xe.printStackTrace();
                }
            }
            
            if(col != null) {
                try { 
                	col.close(); 
                } catch (XMLDBException xe) {
                	xe.printStackTrace();
                }
            }
        }
    }
	
	public ObrazacA1 getByBrojPrijave(String id) throws Exception {
		String documentId = id + ".xml";
        String collectionId = "/db/service/a/requests";
        
        ConnectionProperties conn = ExistAuthUtilities.loadProperties();
        Class<?> cl = Class.forName(conn.driver);
        Database database = (Database) cl.newInstance();
        database.setProperty("create-database", "true");
        DatabaseManager.registerDatabase(database);
        
        Collection col = null;
        XMLResource res = null;
		ObrazacA1 obrazac = null;
        
		try {    
        	System.out.println("[INFO] Retrieving the collection: " + collectionId);
            col = DatabaseManager.getCollection(conn.uri + collectionId);
            col.setProperty(OutputKeys.INDENT, "yes");
            
            System.out.println("[INFO] Retrieving the document: " + documentId);
            res = (XMLResource)col.getResource(documentId);
            
            if(res == null) {
                System.out.println("[WARNING] Document '" + documentId + "' can not be found!");
            } else {
            	System.out.println("[INFO] Binding XML resouce to an JAXB instance: ");
                JAXBContext context = JAXBContext.newInstance("main.java.com.xws.a1document.xml.model");    			
    			Unmarshaller unmarshaller = context.createUnmarshaller();    			
    			obrazac = (ObrazacA1) unmarshaller.unmarshal(res.getContentAsDOM());    		
    			
            }
        } finally {
            //don't forget to clean up!
            
            if(res != null) {
                try { 
                	((EXistResource)res).freeResources(); 
                } catch (XMLDBException xe) {
                	xe.printStackTrace();
                }
            }
            
            if(col != null) {
                try { 
                	col.close(); 
                } catch (XMLDBException xe) {
                	xe.printStackTrace();
                }
            }
        }
        
		return obrazac;
	}
	
	public List<ObrazacA1> getAllZahtevi(String status) throws Exception {
		
		ConnectionProperties conn = ExistAuthUtilities.loadProperties();
		Class<?> cl = Class.forName(conn.driver);
		Database database = (Database) cl.newInstance();
        database.setProperty("create-database", "true");
        
        DatabaseManager.registerDatabase(database);
        Collection col = DatabaseManager.getCollection(conn.uri + "/db/service/a/requests");
    	
        XQueryService xqueryService = (XQueryService) col.getService("XQueryService", "1.0");
        xqueryService.setProperty("indent", "yes");
		
        //String xPathExp = "//obrazac_a_1";
        String xPathExp = "//zahtev[@status='" + status + "']/ancestor::*";          
        
        ResourceSet result = xqueryService.query(xPathExp);
        ResourceIterator i = result.getIterator();
        XMLResource res = null;
        List<ObrazacA1> obrasci = new ArrayList<>();
        while (i.hasMoreResources()) {
            //EXistResource resource = (EXistResource) i.nextResource();
        	res = (XMLResource) i.nextResource();
            JAXBContext context;
			try {
				context = JAXBContext.newInstance("main.java.com.xws.a1document.xml.model");
				Unmarshaller unmarshaller = context.createUnmarshaller();				                
                ObrazacA1 obrazac = (ObrazacA1) unmarshaller.unmarshal(res.getContentAsDOM());
                obrasci.add(obrazac);
				
			} catch (JAXBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
            System.out.println(res.getContent());
        }
		return obrasci;
	}
	
	public void solveRequest(ResenjeDTO resenjeDTO) throws Exception {
		String documentId = resenjeDTO.getId() + ".xml";
        String collectionId = "/db/service/a/requests";
        
        ConnectionProperties conn = ExistAuthUtilities.loadProperties();
        Class<?> cl = Class.forName(conn.driver);
        Database database = (Database) cl.newInstance();
        database.setProperty("create-database", "true");
        DatabaseManager.registerDatabase(database);
        
        Collection col = null;
        
        try { 
        	String statusXPath = "//zahtev/@status";        	
        	String datumXPath = "//zahtev/@datobrade";
        	String imeXPath = "//zahtev/@ime";
        	String prezimeXPath = "//zahtev/@prezime";
        	String obrazlozenjeXPath = "//zahtev/@obrazlozenje";
        	
        	String patch = "<xu:modifications version=\"1.0\" xmlns:xu=\"" + XUpdateProcessor.XUPDATE_NS
        				   + "\" >" + "<xu:update select=\"%1$s\">%2$s</xu:update>"
        				   + "</xu:modifications>";
        	
        	col = DatabaseManager.getCollection(conn.uri + collectionId, conn.user, conn.password);
            col.setProperty("indent", "yes");
            XUpdateQueryService xupdateService = (XUpdateQueryService) col.getService("XUpdateQueryService", "1.0");
            xupdateService.setProperty("indent", "yes");
            
            String update1 = String.format(patch, statusXPath, resenjeDTO.getStatus());
            System.out.println("[INFO] Updating " + statusXPath + " node.");
            long mods1 = xupdateService.updateResource(documentId, update1);
            System.out.println("[INFO] " + mods1 + " modifications processed.");
            
            String update2 = String.format(patch, datumXPath, resenjeDTO.getDatum());
            System.out.println("[INFO] Updating " + datumXPath + " node.");
            long mods2 = xupdateService.updateResource(documentId, update2);
            System.out.println("[INFO] " + mods2 + " modifications processed.");
            
            String update3 = String.format(patch, imeXPath, resenjeDTO.getIme());
            System.out.println("[INFO] Updating " + imeXPath + " node.");
            long mods3 = xupdateService.updateResource(documentId, update3);
            System.out.println("[INFO] " + mods3 + " modifications processed.");
            
            String update4 = String.format(patch, prezimeXPath, resenjeDTO.getPrezime());
            System.out.println("[INFO] Updating " + prezimeXPath + " node.");
            long mods4 = xupdateService.updateResource(documentId, update4);
            System.out.println("[INFO] " + mods4 + " modifications processed.");
            
            String update5 = String.format(patch, obrazlozenjeXPath, resenjeDTO.getObrazlozenje());
            System.out.println("[INFO] Updating " + obrazlozenjeXPath + " node.");
            long mods5 = xupdateService.updateResource(documentId, update5);
            System.out.println("[INFO] " + mods5 + " modifications processed.");
        	
        } catch (Exception e) {
       
		}
        
	}
	
	private static Collection getOrCreateCollection(ConnectionProperties conn, String collectionUri) throws XMLDBException {
        return getOrCreateCollection(conn, collectionUri, 0);
    }
    
    private static Collection getOrCreateCollection(ConnectionProperties conn, String collectionUri, int pathSegmentOffset) throws XMLDBException {
        
        Collection col = DatabaseManager.getCollection(conn.uri + collectionUri, conn.user, conn.password);
        
        // create the collection if it does not exist
        if(col == null) {
        
         	if(collectionUri.startsWith("/")) {
                collectionUri = collectionUri.substring(1);
            }
            
        	String pathSegments[] = collectionUri.split("/");
            
        	if(pathSegments.length > 0) {
                StringBuilder path = new StringBuilder();
            
                for(int i = 0; i <= pathSegmentOffset; i++) {
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
            return getOrCreateCollection(conn, collectionUri, ++pathSegmentOffset);
        } else {
            return col;
        }
    }
}
