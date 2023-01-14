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
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.CollectionManagementService;
import org.xmldb.api.modules.XMLResource;
import org.xmldb.api.modules.XPathQueryService;
import org.xmldb.api.modules.XUpdateQueryService;

@Component
public class ExistManager {
	
	private final static String TARGET_NAMESPACE = "http://test.com";
	
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
}
