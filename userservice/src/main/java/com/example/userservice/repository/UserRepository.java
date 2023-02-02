package com.example.userservice.repository;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.modules.XMLResource;

import com.example.userservice.beans.User;
import com.example.userservice.db.ExistManager;

@Repository
public class UserRepository {
		
	public static final String COLLECTION_ID = "/db/sample/users";
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
	
	public User getUserByUsername(String username) {

        String xPathExp = "//user[username='" + username + "']";
        ResourceSet res = null;
        try {
        	JAXBContext jaxbContext = JAXBContext.newInstance(User.class);
        	Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        	User ret = null;
        	res = existManager.retrieve(COLLECTION_ID, xPathExp);
			Resource r = res.getMembersAsResource();
			String x = (String) r.getContent();
			System.out.println(x);
	        ResourceIterator i = res.getIterator();
	        if (i.hasMoreResources()) {
	        	Resource re = i.nextResource();
		        String xml = (String) re.getContent();
		        ret = (User) unmarshaller.unmarshal(new StringReader(xml));
	        }
	        return ret;
        } catch (Exception e) {
        	return null;
        }
	}
	
	public User saveUserToDB(User user) {
		try {
        	JAXBContext jaxbContext = JAXBContext.newInstance(User.class);
        	Marshaller marshaller = jaxbContext.createMarshaller();
        	
        	StringWriter sw = new StringWriter();
        	marshaller.marshal(user, sw);
        	String xmlString = sw.toString();
        	existManager.storeFromText(COLLECTION_ID, user.getUsername() + ".xml", xmlString);
        	return user;
        } catch (Exception e) {
        	return null;
        }
	}
}
