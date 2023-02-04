package com.example.patentservice.db;

import java.io.File;

import javax.xml.transform.OutputKeys;

import org.exist.xmldb.EXistResource;
import org.exist.xupdate.XUpdateProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.CollectionManagementService;
import org.xmldb.api.modules.XMLResource;
import org.xmldb.api.modules.XPathQueryService;
import org.xmldb.api.modules.XQueryService;
import org.xmldb.api.modules.XUpdateQueryService;

@Component
public class ExistManager {
	
	private final static String TARGET_NAMESPACE = "";
	
	public static final String UPDATE = "<xu:modifications version=\"1.0\" xmlns:xu=\"" + XUpdateProcessor.XUPDATE_NS
			+ "\" xmlns=\"" + TARGET_NAMESPACE + "\">" + "<xu:update select=\"%1$s\">%2$s</xu:update>"
			+ "</xu:modifications>";
	public static final String APPEND = "<xu:modifications version=\"1.0\" xmlns:xu=\"" + XUpdateProcessor.XUPDATE_NS
			+ "\" xmlns=\"" + TARGET_NAMESPACE + "\">" + "<xu:append select=\"%1$s\" child=\"last()\">%2$s</xu:append>"
			+ "</xu:modifications>";

	
	@Autowired
	private AuthenticationManager authManager;
	
	public void createConnection() throws ClassNotFoundException, InstantiationException, IllegalAccessException, XMLDBException {
		Class<?> cl = Class.forName(authManager.getDriver());
		Database db = (Database) cl.newInstance();
	
		//Database db2 = (Database) new DatabaseImpl();
		db.setProperty("create-database", "true");
		DatabaseManager.registerDatabase(db);
	}
	
	public void closeConnection(Collection col, XMLResource res) throws XMLDBException {
		if (col != null) {
			col.close();
		}
		if (res != null) {
			((EXistResource) res).freeResources();
		}
	}
	
	public Collection getOrCreateCollection(String collectionUri, int pathOffset) throws XMLDBException { 
		Collection col = DatabaseManager.getCollection(authManager.getUri() + collectionUri, authManager.getUser(), authManager.getPassword());
		if (col == null) {
			if(collectionUri.startsWith("/")) {
				collectionUri = collectionUri.substring(1);
			}
			String[] pathSegments = collectionUri.split("/");
			
			if(pathSegments.length > 0) {
				StringBuilder path = new StringBuilder();
				for(int i = 0; i <= pathOffset; i++) {
					path.append("/" + pathSegments[i]);
				}
				
				Collection startCol = DatabaseManager.getCollection(authManager.getUri() + path, authManager.getUser(), authManager.getPassword());
				if (startCol == null) {
					String parentPath = path.substring(0, path.lastIndexOf("/"));
					Collection parentCol = DatabaseManager.getCollection(authManager.getUri() + parentPath, authManager.getUser(), authManager.getPassword());
					CollectionManagementService service = (CollectionManagementService) parentCol.getService("CollectionManagementService", "1.0");
					col = service.createCollection(pathSegments[pathOffset]);
					col.close();
					parentCol.close();
				} else {
					startCol.close();
				}
			}
			return getOrCreateCollection(collectionUri, ++pathOffset);
		} else return col;
	}
	
	public void store(String collectionId, String documentId, String filePath) throws ClassNotFoundException, InstantiationException, IllegalAccessException, XMLDBException {
		createConnection();
		Collection col = null;
		XMLResource res = null;
		try {
			col = getOrCreateCollection(collectionId, 0);
			res = (XMLResource) col.createResource(documentId, XMLResource.RESOURCE_TYPE);
			File f = new File(filePath);
			if (!f.canRead()) {
				return;
			}
			res.setContent(f);
			col.storeResource(res);
		} finally {
			closeConnection(col, res);
		}
	}
	
	public void storeFromText(String collectionId, String documentId, String xmlString) throws ClassNotFoundException, InstantiationException, IllegalAccessException, XMLDBException {
		createConnection();
		Collection col = null;
		XMLResource res = null;
		try {
			col = getOrCreateCollection(collectionId, 0);
			res = (XMLResource) col.createResource(documentId, XMLResource.RESOURCE_TYPE);
			res.setContent(xmlString);
			col.storeResource(res);
		} finally {
			closeConnection(col, res);
		}
	}
	
	public XMLResource load(String collectionUri, String documentId) throws ClassNotFoundException, InstantiationException, IllegalAccessException, XMLDBException {
		createConnection();
		Collection col = null;
		XMLResource res = null;
		try {
			col = DatabaseManager.getCollection(authManager.getUri() + collectionUri, authManager.getUser(), authManager.getPassword());
			col.setProperty(OutputKeys.INDENT, "yes");
			res = (XMLResource) col.getResource(documentId);
			return res;
		} finally {
			if(col != null)
				col.close();
		}
	}
	
	public ResourceSet retrieve(String collectionUri, String xPathExp) throws ClassNotFoundException, InstantiationException, IllegalAccessException, XMLDBException {
		createConnection();
		Collection col = null;
		ResourceSet res = null;
		try {
			col = DatabaseManager.getCollection(authManager.getUri() + collectionUri, authManager.getUser(), authManager.getPassword());
			XPathQueryService xPathService = (XPathQueryService)col.getService("XPathQueryService", "1.0");
			xPathService.setProperty("indent", "yes");
			xPathService.setNamespace("", TARGET_NAMESPACE);
			res = xPathService.query(xPathExp);
			return res;
		} finally {
			if(col != null)
				col.close();
		}
	}
	
	public void update (int template, String collectionUri, String document, String contextXPath, String patch) throws ClassNotFoundException, InstantiationException, IllegalAccessException, XMLDBException {
		createConnection();
		Collection col = null;
		String chosenTemplate = null;
		switch(template) {
			case 0: {
				chosenTemplate = UPDATE;
				break;
			}
			case 1: {
				chosenTemplate = APPEND;
				break;
			}
			default: {
				return;
			}
		}
		try {
			col = DatabaseManager.getCollection(authManager.getUri() + collectionUri, authManager.getUser(), authManager.getPassword());
			XUpdateQueryService service = (XUpdateQueryService) col.getService("XUpdateQueryService", "1.0");
			service.setProperty("indent", "yes");
			service.updateResource(document, String.format(chosenTemplate, contextXPath, patch));
		} finally {
			if(col != null)
				col.close();
		}
	}
	
	public ResourceSet getZahteviByStatus(String collectionUri, String status) throws Exception {
		createConnection();
        Collection col = DatabaseManager.getCollection(authManager.getUri() + collectionUri, authManager.getUser(), authManager.getPassword());
    	
        XQueryService xqueryService = (XQueryService) col.getService("XQueryService", "1.0");
        xqueryService.setProperty("indent", "yes");
		
        String xPathExp = "//*[status_zahteva='" + status + "']/ancestor::*";          
        
        ResourceSet result = xqueryService.query(xPathExp);
		return result;
	}
	
	public String getCount(String collectionUri) throws Exception{
		createConnection();
        Collection col = DatabaseManager.getCollection(authManager.getUri() + collectionUri, authManager.getUser(), authManager.getPassword());
    	
        XQueryService xqueryService = (XQueryService) col.getService("XQueryService", "1.0");
        xqueryService.setProperty("indent", "yes");
		
        String xPathExp = "count(//zahtev_za_priznanje_patenta)";          
        
        ResourceSet result = xqueryService.query(xPathExp);
        ResourceIterator i = result.getIterator();
        Resource r = i.nextResource();
        String count = (String)r.getContent();
		return count;
	}
	
//	public void solveRequest(String collectionUri, String id, String newStatus) throws Exception {
//		createConnection();
//		String documentId = id + ".xml";
//        Collection col = null;
//        
//        try { 
//        	String statusXPath = "//status_zahteva";        	
//        	String datumXPath = "//datum_podnosenja";
//        	String imeXPath = "//sluzbenik";
//        	String obrazlozenjeXPath = "//obrazlozenje";
//        	
//        	String patch = "<xu:modifications version=\"1.0\" xmlns:xu=\"" + XUpdateProcessor.XUPDATE_NS
//        				   + "\" >" + "<xu:update select=\"%1$s\">%2$s</xu:update>"
//        				   + "</xu:modifications>";
//        	
//        	col = DatabaseManager.getCollection(authManager.getUri() + collectionUri, authManager.getUser(), authManager.getPassword());
//            col.setProperty("indent", "yes");
//            XUpdateQueryService xupdateService = (XUpdateQueryService) col.getService("XUpdateQueryService", "1.0");
//            xupdateService.setProperty("indent", "yes");
//            
//            String update1 = String.format(patch, statusXPath, resenjeDTO.getStatus());
//            System.out.println("[INFO] Updating " + statusXPath + " node.");
//            long mods1 = xupdateService.updateResource(documentId, update1);
//            System.out.println("[INFO] " + mods1 + " modifications processed.");
//            
//            String update2 = String.format(patch, datumXPath, resenjeDTO.getDatum());
//            System.out.println("[INFO] Updating " + datumXPath + " node.");
//            long mods2 = xupdateService.updateResource(documentId, update2);
//            System.out.println("[INFO] " + mods2 + " modifications processed.");
//            
//            String update3 = String.format(patch, imeXPath, resenjeDTO.getIme());
//            System.out.println("[INFO] Updating " + imeXPath + " node.");
//            long mods3 = xupdateService.updateResource(documentId, update3);
//            System.out.println("[INFO] " + mods3 + " modifications processed.");
//            
//            String update4 = String.format(patch, obrazlozenjeXPath, resenjeDTO.getObrazlozenje());
//            System.out.println("[INFO] Updating " + obrazlozenjeXPath + " node.");
//            long mods4 = xupdateService.updateResource(documentId, update5);
//            System.out.println("[INFO] " + mods5 + " modifications processed.");
//        	
//        } catch (Exception e) {
//       
//		}
//        
//	}
	
	public ResourceSet search(String collectionUri, String query) throws ClassNotFoundException, XMLDBException, InstantiationException, IllegalAccessException {
		createConnection();
		Collection col = DatabaseManager.getCollection(authManager.getUri() + collectionUri, authManager.getUser(), authManager.getPassword());
    	
        XQueryService xqueryService = (XQueryService) col.getService("XQueryService", "1.0");
        xqueryService.setProperty("indent", "yes");
		
        String xPathExp = "/*[contains(., '" + query + "')]";     
        
        ResourceSet result = xqueryService.query(xPathExp);
		return result;
	}
}
