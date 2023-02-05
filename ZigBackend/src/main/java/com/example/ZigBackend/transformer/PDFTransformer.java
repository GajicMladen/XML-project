package com.example.ZigBackend.transformer;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.io.FileUtils;
import org.apache.fop.apps.*;
import org.exist.xmldb.RemoteXMLResource;
import org.xml.sax.SAXException;

import net.sf.saxon.TransformerFactoryImpl;
import org.xmldb.api.modules.XMLResource;

import static org.apache.fop.fonts.type1.AdobeStandardEncoding.e;

public class PDFTransformer {

    private FopFactory fopFactory;

    private TransformerFactory transformerFactory;

    public static final String INPUT_FILE = "Z1.xml";

    public static final String XSL_FILE = "zig_fo.xsl";

    public static final String OUTPUT_FILE = "test.pdf";


    public PDFTransformer() throws SAXException, IOException {

        // Initialize FOP factory object

        ClassLoader classLoader = getClass().getClassLoader();
        File file = loadFile("fop.xconf");
        fopFactory = FopFactory.newInstance(file);
        // Setup the XSLT transformer factory
        transformerFactory = new TransformerFactoryImpl();


    }

    public File generatePDF() throws Exception {

        System.out.println("[INFO] " + PDFTransformer.class.getSimpleName());

        // Point to the XSL-FO file
        File xslFile = loadFile(XSL_FILE);

        // Create transformation source
        StreamSource transformSource = new StreamSource(xslFile);

        // Initialize the transformation subject
        StreamSource source = new StreamSource( loadFile(INPUT_FILE));

        // Initialize user agent needed for the transformation
        FOUserAgent userAgent = fopFactory.newFOUserAgent();

        // Create the output stream to store the results
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();

        // Initialize the XSL-FO transformer object
        Transformer xslFoTransformer = transformerFactory.newTransformer(transformSource);

        // Construct FOP instance with desired output format
        Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, userAgent, outStream);

        // Resulting SAX events
        Result res = new SAXResult(fop.getDefaultHandler());

        // Start XSLT transformation and FOP processing
        xslFoTransformer.transform(source, res);

        // Generate PDF file
        File pdfFile = createNewFile("test.pdf");

        OutputStream out = new BufferedOutputStream(Files.newOutputStream(pdfFile.toPath()));
        out.write(outStream.toByteArray());

        System.out.println("[INFO] File \"" + pdfFile.getCanonicalPath() + "\" generated successfully.");
        out.close();

        System.out.println("[INFO] End.");

        return pdfFile;

    }

    private File loadFile(String path) throws IOException {
        File xslFile = File.createTempFile("temp", ".xml");
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(path);
        if (is != null) {
            Files.copy(is, xslFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            return xslFile;
        }
        System.out.println("Nema fajla u resursima");
        System.out.println(path);
        return null;
    }

    public File createNewFile(String name) throws IOException {
        System.out.println("Generisanje novog fajla u resurse");
        URL url = this.getClass().getClassLoader().getResource("");
        File resourcesDirectory = new File(url.getFile());
        File newFile = new File(resourcesDirectory, name);
        if(!newFile.exists()){
            newFile.createNewFile();
        }
        return newFile;
    }



    public File generatePDFFromXML(RemoteXMLResource xmlResource) throws Exception {

        System.out.println("[INFO] " + PDFTransformer.class.getSimpleName());

        // Point to the XSL-FO file
        File xslFile = loadFile(XSL_FILE);

        // Create transformation source
        StreamSource transformSource = new StreamSource(xslFile);

        // Initialize the transformation subject
        StreamSource source = new StreamSource( xmlResource.getStreamContent() );

        // Initialize user agent needed for the transformation
        FOUserAgent userAgent = fopFactory.newFOUserAgent();

        // Create the output stream to store the results
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();

        // Initialize the XSL-FO transformer object
        Transformer xslFoTransformer = transformerFactory.newTransformer(transformSource);

        // Construct FOP instance with desired output format
        Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, userAgent, outStream);

        // Resulting SAX events
        Result res = new SAXResult(fop.getDefaultHandler());

        // Start XSLT transformation and FOP processing
        xslFoTransformer.transform(source, res);

        // Generate PDF file
        File pdfFile = createNewFile("test.pdf");

        OutputStream out = new BufferedOutputStream(Files.newOutputStream(pdfFile.toPath()));
        out.write(outStream.toByteArray());

        System.out.println("[INFO] File \"" + pdfFile.getCanonicalPath() + "\" generated successfully.");
        out.close();

        System.out.println("[INFO] End.");

        return pdfFile;

    }
}
