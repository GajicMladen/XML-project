package com.example.patentservice.repository;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xmldb.api.modules.XMLResource;

import com.example.patentservice.beans.ZahtevZaPriznanjePatenta;
import com.example.patentservice.db.ExistManager;

@Repository
public class PatentRepository {
	
	public static final String COLLECTION_ID = "/db/sample/patents";
	public static final String RESOURCE_PATH = "src/main/resources/data/";
	private static final String BEANS_PACKAGE = "com.example.patentservice.beans";
	
	@Autowired
	private ExistManager existManager;
	
	public void writeXMLFiletoDB(String path) {
		try {
			String[] tokens = path.split("/");
			String fileName = tokens[tokens.length - 1];
			existManager.store(COLLECTION_ID, fileName, path);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public XMLResource readXMLfromDB(String documentId) {
		XMLResource res = null;
		try {
			res = existManager.load(COLLECTION_ID, documentId + ".xml");
			return res;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public ZahtevZaPriznanjePatenta saveZahtevToDB(ZahtevZaPriznanjePatenta zahtev) {
		try {
        	JAXBContext jaxbContext = JAXBContext.newInstance(BEANS_PACKAGE);
        	Marshaller marshaller = jaxbContext.createMarshaller();
        	
        	StringWriter sw = new StringWriter();
        	marshaller.marshal(zahtev, sw);
        	String xmlString = sw.toString();
        	existManager.storeFromText(COLLECTION_ID, zahtev.getPodaciZavod().getBrojPrijave() + ".xml", xmlString);
        	return zahtev;
        } catch (Exception e) {
        	return null;
        }
	}
	
	public int getCount() {
		int ret = 0;
		try {
			ret = Integer.valueOf(existManager.getCount(COLLECTION_ID));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
}
