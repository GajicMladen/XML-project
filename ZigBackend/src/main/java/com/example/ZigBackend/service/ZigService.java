package com.example.ZigBackend.service;

import com.example.ZigBackend.db.ZigDB;
import com.example.ZigBackend.repository.ZigRepository;
import com.example.ZigBackend.gen.Z1Classes.EStatusResenja;
import com.example.ZigBackend.transformer.HTMLTransformer;
import com.example.ZigBackend.rdf.FusekiDB;
import com.example.ZigBackend.transformer.PDFTransformer;
import org.exist.xmldb.RemoteXMLResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.ZigBackend.gen.Z1Classes.ZahtevZaPriznanjeZiga;
import org.xml.sax.SAXException;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

import static com.example.ZigBackend.utils.Utils.getTestZahtevZaPrizanjeZiga;

@Service
public class ZigService {

    @Autowired
    private RdfService rdfService;

    private ZigRepository zigRepository = new ZigRepository();

    public ZahtevZaPriznanjeZiga getTestZahtev() throws JAXBException, FileNotFoundException {
        ZahtevZaPriznanjeZiga zahtevZaPriznanjeZiga = getTestZahtevZaPrizanjeZiga();
        return zahtevZaPriznanjeZiga;
    }

    public ZahtevZaPriznanjeZiga saveTestXMLinDB() throws FileNotFoundException, JAXBException {
        ZahtevZaPriznanjeZiga zahtevZaPriznanjeZiga = getTestZahtevZaPrizanjeZiga();
        ZigDB.save(zahtevZaPriznanjeZiga);
        FusekiDB.save(zahtevZaPriznanjeZiga);
        return zahtevZaPriznanjeZiga;
    }

    public  ZahtevZaPriznanjeZiga saveXMLinDB(ZahtevZaPriznanjeZiga zahtevZaPriznanjeZiga){
        ZigDB.save(zahtevZaPriznanjeZiga);
        FusekiDB.save(zahtevZaPriznanjeZiga);
        return zahtevZaPriznanjeZiga;
    }

    public File getTestPDFFile(){
        try {
            PDFTransformer pdfTransformer = new PDFTransformer();
            return pdfTransformer.generatePDF();
        } catch (SAXException e) {
            throw new RuntimeException("SAX exception\n"+e.toString() );
        } catch (IOException e) {
            throw new RuntimeException("IO exception" +e.toString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public File getPDFFileFromXML(String brojZahteva){
        try {
            RemoteXMLResource xmlResource = zigRepository.getZahtevXMLResource(brojZahteva);
            PDFTransformer pdfTransformer = new PDFTransformer();
            return pdfTransformer.generatePDFFromXML(xmlResource);
        } catch (SAXException e) {
            throw new RuntimeException("SAX exception\n"+e.toString() );
        } catch (IOException e) {
            throw new RuntimeException("IO exception" +e.toString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    public File getTestHTMLFile(){
        try {
            HTMLTransformer htmlTransformer = new HTMLTransformer();
            return htmlTransformer.generateHTML( HTMLTransformer.INPUT_FILE,HTMLTransformer.XSL_FILE);
        } catch (IOException e) {
            throw new RuntimeException("IO exception" +e.toString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public File getHTMLFileFromXML(String brojZahteva){
        try {
            HTMLTransformer htmlTransformer = new HTMLTransformer();
            RemoteXMLResource xmlResource = zigRepository.getZahtevXMLResource(brojZahteva);
            return htmlTransformer.generateHTMLFromXML(xmlResource,HTMLTransformer.XSL_FILE);
        } catch (IOException e) {
            throw new RuntimeException("IO exception" +e.toString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ZahtevZaPriznanjeZiga getZahtevById(String brojZahteva){
        return  zigRepository.getZahtev(brojZahteva);
    }
    public List<ZahtevZaPriznanjeZiga> getAllApplied() throws JAXBException, XMLDBException {
        return zigRepository.getAllApplied();
    }

    public List<ZahtevZaPriznanjeZiga> getAllApproved() throws JAXBException, XMLDBException {
        return zigRepository.getAllApproved();
    }

    public List<ZahtevZaPriznanjeZiga> getAllCanceled() throws JAXBException, XMLDBException {
        return zigRepository.getAllCanceled();
    }

    public List<ZahtevZaPriznanjeZiga> getAllDenied() throws JAXBException, XMLDBException {
        return zigRepository.getAllDenied();
    }

    public ZahtevZaPriznanjeZiga approveRequest(ZahtevZaPriznanjeZiga zahtevZaPriznanjeZiga){
        zahtevZaPriznanjeZiga.setStatus(EStatusResenja.PRIHVACENO);
        return zahtevZaPriznanjeZiga;
    }

    public ZahtevZaPriznanjeZiga denyRequest(ZahtevZaPriznanjeZiga zahtevZaPriznanjeZiga){
        zahtevZaPriznanjeZiga.setStatus(EStatusResenja.ODBIJENO);
        return zahtevZaPriznanjeZiga;
    }

    public String getMetadataById(String id, String type) throws Exception {
        if (type.equals("JSON")) {
            String sparqlQuery = "VALUES ?subject { <" + "http://www.tim777.com/zig/" + id + "> } ?subject ?predicate ?object .";
            return rdfService.getJsonMetadata(sparqlQuery);
        } else {
            String sparqlQuery = "<http://www.tim777.com/zig/" + id + "> ?predicate ?object .";
            return rdfService.getRdfMetadata(sparqlQuery);
        }
    }

    public List<ZahtevZaPriznanjeZiga> searchZigs(String query) {
        List<ZahtevZaPriznanjeZiga> ret = new ArrayList<ZahtevZaPriznanjeZiga>();
        try {
            List<XMLResource> resources = zigRepository.getZigSearch(query);
            for (XMLResource res: resources)
                ret.add( MarshallerZig.unmarshal(res) );
        } catch (Exception e) {
            return ret;
        }
        return ret;
    }

}
