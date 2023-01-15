package main.java.com.xws.a1document.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

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

import org.springframework.stereotype.Service;
import org.w3c.dom.Document;

@Service
public class XhtmlService {
	
	private DocumentBuilderFactory documentFactory;
	private TransformerFactory transformerFactory;
	
	public static final String INPUT_FILE = "data/A-1.xml";
	public static final String XSL_FILE = "data/a-xhtml.xsl";
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
}
