package main.java.com.xws.a1document.service;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import net.sf.saxon.TransformerFactoryImpl;

@Service
public class PdfService {
	
	private FopFactory fopFactory;
	private TransformerFactory transformerFactory;
	
	public static final String INPUT_FILE = "data/A-1.xml";
	public static final String XSL_FILE = "data/a-fo.xsl";
	public static final String OUTPUT_FILE = "gen/a.pdf";
	
	private void init() throws SAXException, IOException {
		fopFactory = FopFactory.newInstance(new File("src/fop.xconf"));
		transformerFactory = new TransformerFactoryImpl();
	}
	
	public void generatePDF() throws Exception {
		init();
		StreamSource transformSource = new StreamSource(new File(XSL_FILE));
		StreamSource source = new StreamSource(new File(INPUT_FILE));
		FOUserAgent userAgent = fopFactory.newFOUserAgent();
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		Transformer xslFoTransformer = transformerFactory.newTransformer(transformSource);
		Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, userAgent, outStream);
		Result res = new SAXResult(fop.getDefaultHandler());
		xslFoTransformer.transform(source, res);
		File pdfFile = new File(OUTPUT_FILE);
		if (!pdfFile.getParentFile().exists()) {
			System.out.println("[INFO] A new directory is created: " + pdfFile.getParentFile().getAbsolutePath() + ".");
			pdfFile.getParentFile().mkdir();
		}
		
		OutputStream out = new BufferedOutputStream(new FileOutputStream(pdfFile));
		out.write(outStream.toByteArray());
		
		System.out.println("[INFO] File \"" + pdfFile.getCanonicalPath() + "\" generated successfully.");
		out.close();
		
		System.out.println("[INFO] PDF generated.");
	}
}

