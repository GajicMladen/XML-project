package com.example.patentservice.service;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.update.UpdateExecutionFactory;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateProcessor;
import org.apache.jena.update.UpdateRequest;
import org.apache.xmlgraphics.util.MimeConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xml.sax.InputSource;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

import com.example.patentservice.beans.ZahtevZaPriznanjePatenta;
import com.example.patentservice.db.FusekiManager;
import com.example.patentservice.grddl.PatentMetadataExtractor;
import com.example.patentservice.repository.PatentRepository;
import com.example.patentservice.utils.SparqlUtil;

import net.sf.saxon.TransformerFactoryImpl;

@Service
public class PatentService {
	
	private static final String BEANS_PACKAGE = "com.example.patentservice.beans";
	
	private static final String RESOURCES_PATH = "src/main/resources/data/";
	
	private static final String FOP_CONF_PATH = "src/main/resources/xslfo/fop.xconf";
	
	private static final String XSL_FO_PATH = "src/main/resources/xslfo/p_fo.xsl";
	
	private static final String XSL_PATH = "src/main/resources/xslfo/p.xsl";
	
	private static final String GENERATED_PATH = "src/main/resources/generated/";
	
	private static final String PATENT_NAMED_GRAPH_URI = "/example/patent/metadata";
	
	@Autowired
	private PatentRepository patentRepository;
	
	@Autowired
	private FusekiManager fusekiManager;
	
	public ZahtevZaPriznanjePatenta getMarshalledZahtev(String path) {
		ZahtevZaPriznanjePatenta zahtev = null;
		try {
			JAXBContext context = JAXBContext.newInstance(BEANS_PACKAGE);
			Unmarshaller um = context.createUnmarshaller();
			zahtev = (ZahtevZaPriznanjePatenta) um.unmarshal(new File(path));
		} catch(JAXBException e) {
			e.printStackTrace();
		}
		return zahtev;
	}
	
	public ZahtevZaPriznanjePatenta getMarshalledZahtev(XMLResource xml) {
		ZahtevZaPriznanjePatenta zahtev = null;
		try {
			JAXBContext context = JAXBContext.newInstance(BEANS_PACKAGE);
			Unmarshaller um = context.createUnmarshaller();
			zahtev = (ZahtevZaPriznanjePatenta) um.unmarshal(xml.getContentAsDOM());
		} catch(JAXBException e) {
			e.printStackTrace();
		} catch (XMLDBException e) {
			e.printStackTrace();
		}
		return zahtev;
	}
	
	public void unmarshallZahtevIntoFile(ZahtevZaPriznanjePatenta zahtev) {
		try {
			JAXBContext context = JAXBContext.newInstance(BEANS_PACKAGE);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			File file = new File(RESOURCES_PATH + zahtev.getPodaciZavod().getBrojPrijave() + ".xml");
			m.marshal(zahtev, file);
		} catch(JAXBException e) {
			e.printStackTrace();
		}
	}
	
	public void writeXMLFileToDB(String path) {
		patentRepository.writeXMLFiletoDB(path);
	}
	
	public String readXMLfromDB(String documentId) {
		XMLResource xml = patentRepository.readXMLfromDB(documentId);
		if(xml == null) return null;
		
		String ret = null;
		try {
			ret = xml.getContent().toString();
		} catch (XMLDBException e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	public boolean generatePDF(String documentId) {
		try {
			XMLResource xml = patentRepository.readXMLfromDB(documentId);
			if(xml == null) return false;
			TransformerFactory transformerFactory = new TransformerFactoryImpl();
			FopFactory fopFactory = FopFactory.newInstance(new File(FOP_CONF_PATH));
			File xsl = new File(XSL_FO_PATH);
			
			StreamSource transformSource = new StreamSource(xsl);
			
			FOUserAgent userAgent = fopFactory.newFOUserAgent();
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();	
			
			Transformer xslFoTransformer = transformerFactory.newTransformer(transformSource);
			Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, userAgent, outStream);
			
			Result res = new SAXResult(fop.getDefaultHandler());
			xslFoTransformer.transform(new StreamSource(new StringReader(xml.getContent().toString())), res);
			File pdfFile = new File(GENERATED_PATH + documentId + ".pdf");
			
			if (!pdfFile.getParentFile().exists()) {
				pdfFile.getParentFile().mkdir();
			}
			
			OutputStream out = new BufferedOutputStream(new FileOutputStream(pdfFile));
			out.write(outStream.toByteArray());
			out.close();
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	public boolean generateXHTML(String documentId) {
		try {
			XMLResource xml = patentRepository.readXMLfromDB(documentId);
			if (xml == null) return false;
			DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
			documentFactory.setNamespaceAware(true);
			documentFactory.setIgnoringComments(true);
			documentFactory.setIgnoringElementContentWhitespace(true);
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			
			StreamSource transformSource = new StreamSource(new File(XSL_PATH));
			Transformer transformer = transformerFactory.newTransformer(transformSource);
			transformer.setOutputProperty("{http://xml.apache.org/xalan}indent-amount", "2");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(OutputKeys.METHOD, "xhtml");
			DocumentBuilder builder = documentFactory.newDocumentBuilder();
			org.w3c.dom.Document document = null;
			document = builder.parse(new InputSource(new StringReader(xml.getContent().toString()))); 

			DOMSource source = new DOMSource(document);
			StreamResult result = new StreamResult(new FileOutputStream(GENERATED_PATH + documentId + ".html"));
			transformer.transform(source, result);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean writeMetadataRDF(String documentId) {
		try {
			XMLResource xml = patentRepository.readXMLfromDB(documentId);
			if (xml == null) return false;
			ZahtevZaPriznanjePatenta zahtev = getMarshalledZahtev(xml);
			Model model = PatentMetadataExtractor.extract(zahtev);
			
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			model.write(out, SparqlUtil.NTRIPLES);
//			model.write(System.out, SparqlUtil.RDF_XML);
			
			UpdateRequest request = UpdateFactory.create() ;
	        UpdateProcessor processor = UpdateExecutionFactory.createRemote(request, fusekiManager.getUpdateEndpoint());
	        processor.execute();
	        
			String sparqlUpdate = SparqlUtil.insertData(fusekiManager.getDataEndpoint() + PATENT_NAMED_GRAPH_URI, new String(out.toByteArray()));
			UpdateRequest update = UpdateFactory.create(sparqlUpdate);

			processor = UpdateExecutionFactory.createRemote(update, fusekiManager.getUpdateEndpoint());
			processor.execute();
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
