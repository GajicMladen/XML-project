package main.java.com.xws.a1document.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.OutputKeys;

import org.exist.xmldb.EXistResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.CollectionManagementService;
import org.xmldb.api.modules.XMLResource;
import org.xmldb.api.modules.XQueryService;

import main.java.com.xws.a1document.util.ExistAuthUtilities.ConnectionProperties;
import main.java.com.xws.a1document.xml.model.ObrazacA1;

@Service
public class ExistService {
	
	@Autowired
	private A1Service a1Service;
	
	public void write(ConnectionProperties conn) throws ClassNotFoundException, XMLDBException, InstantiationException, IllegalAccessException {
		String collectionId = "/db/a";
		String documentId = "a1.xml"; 
		String filePath = "data/A-1.xml";
		
		System.out.println("[INFO] Loading driver class: " + conn.driver);
    	Class<?> cl = Class.forName(conn.driver);
    	
    	Database database = (Database) cl.newInstance();
        database.setProperty("create-database", "true");
        
        DatabaseManager.registerDatabase(database);
        
        Collection col = null;
        XMLResource res = null;
        
        try {
        	System.out.println("[INFO] Retrieving the collection: " + collectionId);
            col = getOrCreateCollection(conn, collectionId);
            System.out.println("[INFO] Inserting the document: " + documentId);
            res = (XMLResource) col.createResource(documentId, XMLResource.RESOURCE_TYPE);
            
            File f = new File(filePath);
            
            if(!f.canRead()) {
                System.out.println("[ERROR] Cannot read the file: " + filePath);
                return;
            }
            
            res.setContent(f);
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
	
	public void read(ConnectionProperties conn) throws ClassNotFoundException, InstantiationException, IllegalAccessException, XMLDBException, JAXBException {
		String collectionId = "db/a";
		String documentId = "a1.xml";
		
		System.out.println("[INFO] Loading driver class: " + conn.driver);
        Class<?> cl = Class.forName(conn.driver);
        
        Database database = (Database) cl.newInstance();
        database.setProperty("create-database", "true");
        
        DatabaseManager.registerDatabase(database);
        
        Collection col = null;
        XMLResource res = null;
        
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
    			
    			ObrazacA1 obrazac = (ObrazacA1) unmarshaller.unmarshal(res.getContentAsDOM());
    			
    			System.out.println("[INFO] Showing the document as JAXB instance: ");
    			//a1Service.printObrazac(obrazac);
    			
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

	public List<ObrazacA1> search(String query, String status, ConnectionProperties conn) throws ClassNotFoundException, XMLDBException, InstantiationException, IllegalAccessException {
		Class<?> cl = Class.forName(conn.driver);
		Database database = (Database) cl.newInstance();
        database.setProperty("create-database", "true");
        
        DatabaseManager.registerDatabase(database);
        Collection col = DatabaseManager.getCollection(conn.uri + "/db/service/a/requests");
    	
        XQueryService xqueryService = (XQueryService) col.getService("XQueryService", "1.0");
        xqueryService.setProperty("indent", "yes");
		
        String xPathExp = "/*[contains(., '" + query + "')]";     
        
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

}
