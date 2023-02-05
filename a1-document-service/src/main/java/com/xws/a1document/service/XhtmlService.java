package main.java.com.xws.a1document.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.StringReader;
import java.nio.file.Files;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import main.java.com.xws.a1document.repository.A1Repository;

@Service
public class XhtmlService {
	
	@Autowired
	private A1Repository a1Repository;
	
	private DocumentBuilderFactory documentFactory;
	private TransformerFactory transformerFactory;
	
	public static final String INPUT_FILE = "data/A-1.xml";
	public static final String XSL_FILE = "data/a-xhtml.xsl";
	public static final String OUTPUT_FOLDER = "gen/xhtml/";
	public static final String HTML_FILE = "gen/a.html";
	
	private void init() {
		documentFactory = DocumentBuilderFactory.newInstance();
		documentFactory.setNamespaceAware(true);
		documentFactory.setIgnoringComments(true);
		documentFactory.setIgnoringElementContentWhitespace(true);
		transformerFactory = TransformerFactory.newInstance();
	}
	
	public void generateXHTML() throws FileNotFoundException, TransformerException {
		init();
		try {
			StreamSource transformSource = new StreamSource(new File(XSL_FILE));
			Transformer transformer = transformerFactory.newTransformer(transformSource);
			transformer.setOutputProperty("{http://xml.apache.org/xalan}indent-amount", "2");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(OutputKeys.METHOD, "xhtml");
			DOMSource source = new DOMSource(buildDocument(INPUT_FILE));
			StreamResult result = new StreamResult(new FileOutputStream(HTML_FILE));
			transformer.transform(source, result);
			System.out.println("[INFO] File \"" + HTML_FILE + "\" generated successfully.");
			System.out.println("[INFO] End.");
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			e.printStackTrace();
		}

	}
	
	public Document buildDocument(String filePath) {
		Document document = null;
		try {
			DocumentBuilder builder = documentFactory.newDocumentBuilder();
			document = builder.parse(new File(filePath));
			
			if (document != null) {
				System.out.println("[INFO] File parsed with no errors.");
			} else {
				System.out.println("[WARN] Document is null.");
			}
		} catch (Exception e) {
			return null;
		}
		return document;
	}

	public File getXhtml(String id) throws Exception {
		init();
		String obrazac = a1Repository.getByBrojPrijaveAsString(id);
		try {
			StreamSource transformSource = new StreamSource(new File(XSL_FILE));
			Transformer transformer = transformerFactory.newTransformer(transformSource);
			transformer.setOutputProperty("{http://xml.apache.org/xalan}indent-amount", "2");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(OutputKeys.METHOD, "xhtml");
			
			DocumentBuilder builder = documentFactory.newDocumentBuilder();
            Document document = builder.parse(new InputSource(new StringReader(obrazac)));
            DOMSource source = new DOMSource(document);            
            File htmlFile = new File(OUTPUT_FOLDER + id + ".html");
            if (!htmlFile.getParentFile().exists()) {
    			System.out.println("[INFO] A new directory is created: " + htmlFile.getParentFile().getAbsolutePath() + ".");
    			htmlFile.getParentFile().mkdir();
    		}
            StreamResult result = new StreamResult(Files.newOutputStream(htmlFile.toPath()));            
            //StreamResult result = new StreamResult(new FileOutputStream(filePath));
            transformer.transform(source, result);
            System.out.println("[INFO] Generated XHTML file!");
            return htmlFile;		
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			e.printStackTrace();
		}
		return null;
	}
}
