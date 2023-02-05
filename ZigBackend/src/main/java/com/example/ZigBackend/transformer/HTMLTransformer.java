package com.example.ZigBackend.transformer;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.swing.text.html.HTML;
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

//import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import org.exist.xmldb.RemoteXMLResource;
import org.springframework.core.io.InputStreamResource;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;
//import com.itextpdf.text.pdf.PdfWriter;
//import com.itextpdf.tool.xml.XMLWorkerHelper;

public class HTMLTransformer {

    private static DocumentBuilderFactory documentFactory;

    private static TransformerFactory transformerFactory;

    public static final String INPUT_FILE = "Z1.xml";

    public static final String XSL_FILE = "Z1.xsl";

    public static final String HTML_FILE = "Z1.html";


    static {

        /* Inicijalizacija DOM fabrike */
        documentFactory = DocumentBuilderFactory.newInstance();
        documentFactory.setNamespaceAware(true);
        documentFactory.setIgnoringComments(true);
        documentFactory.setIgnoringElementContentWhitespace(true);

        /* Inicijalizacija Transformer fabrike */
        transformerFactory = TransformerFactory.newInstance();

    }
    public HTMLTransformer(){

        /* Inicijalizacija DOM fabrike */
        documentFactory = DocumentBuilderFactory.newInstance();
        documentFactory.setNamespaceAware(true);
        documentFactory.setIgnoringComments(true);
        documentFactory.setIgnoringElementContentWhitespace(true);

        /* Inicijalizacija Transformer fabrike */
        transformerFactory = TransformerFactory.newInstance();
    }
    public org.w3c.dom.Document buildDocument(String filePath) {

        org.w3c.dom.Document document = null;
        try {

            DocumentBuilder builder = documentFactory.newDocumentBuilder();
            document = builder.parse(loadFile(filePath));

            if (document != null)
                System.out.println("[INFO] File parsed with no errors.");
            else
                System.out.println("[WARN] Document is null.");

        } catch (Exception e) {
            return null;

        }

        return document;
    }
    public org.w3c.dom.Document buildDocumentFromXMLResource(RemoteXMLResource xmlResource) {

        org.w3c.dom.Document document = null;
        try {

            DocumentBuilder builder = documentFactory.newDocumentBuilder();
            document = builder.parse(xmlResource.getStreamContent());

            if (document != null)
                System.out.println("[INFO] File parsed with no errors.");
            else
                System.out.println("[WARN] Document is null.");

        } catch (Exception e) {
            return null;

        }

        return document;
    }
    public File generateHTML(String xmlPath, String xslPath) throws FileNotFoundException {

        try {

            // Initialize Transformer instance
            StreamSource transformSource = new StreamSource(loadFile(xslPath));
            Transformer transformer = transformerFactory.newTransformer(transformSource);
            transformer.setOutputProperty("{http://xml.apache.org/xalan}indent-amount", "2");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            // Generate XHTML
            transformer.setOutputProperty(OutputKeys.METHOD, "xhtml");

            // Transform DOM to HTML
            DOMSource source = new DOMSource(buildDocument(xmlPath));
            File htmlFile = loadFile(HTML_FILE);
            StreamResult result = new StreamResult(new FileOutputStream(htmlFile));
            transformer.transform(source, result);
            return htmlFile;

        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (TransformerFactoryConfigurationError e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (TransformerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    private File loadFile(String path) throws IOException {
        File xslFile = File.createTempFile("temp", ".html");
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(path);
        if (is != null) {
            Files.copy(is, xslFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            return xslFile;
        }
        System.out.println("Nema fajla u resursima");
        System.out.println(path);
        return null;
    }

    public File generateHTMLFromXML(RemoteXMLResource xmlResource, String xslPath) throws FileNotFoundException {

        try {

            // Initialize Transformer instance
            StreamSource transformSource = new StreamSource(loadFile(xslPath));
            Transformer transformer = transformerFactory.newTransformer(transformSource);
            transformer.setOutputProperty("{http://xml.apache.org/xalan}indent-amount", "2");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            // Generate XHTML
            transformer.setOutputProperty(OutputKeys.METHOD, "xhtml");

            // Transform DOM to HTML
            DOMSource source = new DOMSource(xmlResource.getContentAsDOM());
            File htmlFile = loadFile(HTML_FILE);
            StreamResult result = new StreamResult(new FileOutputStream(htmlFile));
            transformer.transform(source, result);
            return htmlFile;

        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (TransformerFactoryConfigurationError e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (TransformerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (XMLDBException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
