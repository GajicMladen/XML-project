package com.example.patentservice.repository;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.modules.XMLResource;

import com.example.patentservice.beans.ZahtevZaPriznanjePatenta;
import com.example.patentservice.db.ExistManager;
import com.example.patentservice.dto.Resenje;

@Repository
public class PatentRepository {
	
	public static final String COLLECTION_ID = "/db/sample/patents";
	public static final String RESENJE_COLLECTION_ID = "/db/sample/resenja";
	public static final String RESOURCE_PATH = "src/main/resources/data/";
	private static final String BEANS_PACKAGE = "com.example.patentservice.beans";
	
	@Autowired
	private ExistManager existManager;
	
	public void writeXMLFiletoDB(String path, String collection) {
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
			res = existManager.load(COLLECTION_ID, documentId);
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
	
	public Resenje saveResenjeToDB(Resenje resenje) {
		try {
        	
        	String xmlString = resenje.toString();
        	existManager.solveRequest(COLLECTION_ID, resenje.getPatent(), resenje);
        	existManager.storeFromText(RESENJE_COLLECTION_ID, resenje.getSifra() + ".xml", xmlString);
        
        	return resenje;
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
	
	public List<XMLResource> getZahtevList(String status) throws Exception {
		List<XMLResource> ret = new ArrayList<XMLResource>();
		ResourceSet res = existManager.getZahteviByStatus(COLLECTION_ID, status);
		ResourceIterator i = res.getIterator();
		while (i.hasMoreResources()) {
			XMLResource resource = (XMLResource) i.nextResource();
			ret.add(resource);
		}
		return ret;
	}
	
	public List<XMLResource> getPatentSearch(String query) throws Exception {
		List<XMLResource> ret = new ArrayList<XMLResource>();
		ResourceSet res = existManager.search(COLLECTION_ID, query);
		ResourceIterator i = res.getIterator();
		while (i.hasMoreResources()) {
			XMLResource resource = (XMLResource) i.nextResource();
			ret.add(resource);
		}
		return ret;
	}
	
	public XMLResource getPatentById(String id) throws Exception {
		return existManager.load(COLLECTION_ID, id);
	}
}
