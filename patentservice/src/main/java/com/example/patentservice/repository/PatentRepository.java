package com.example.patentservice.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xmldb.api.modules.XMLResource;

import com.example.patentservice.db.ExistManager;

@Repository
public class PatentRepository {
	
	public static final String COLLECTION_ID = "/db/sample/patents";
	public static final String RESOURCE_PATH = "src/main/resources/data/";
	
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
}
