package main.java.com.xws.a1document.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import main.java.com.xws.a1document.service.A1Service;
import main.java.com.xws.a1document.service.ExistService;
import main.java.com.xws.a1document.service.PdfService;
import main.java.com.xws.a1document.service.RdfService;
import main.java.com.xws.a1document.service.XhtmlService;
import main.java.com.xws.a1document.util.ExistAuthUtilities;
import main.java.com.xws.a1document.xml.model.ObrazacA1;

@Controller
@RequestMapping("api/a1/")
public class A1Controller {
	
	@Autowired
	private A1Service a1Service;
	
	@Autowired
	private ExistService existService;
	
	@Autowired
	private PdfService pdfService;
	
	@Autowired
	private XhtmlService xhtmlService;
	
	@Autowired
	private RdfService rdfService;
	
	@GetMapping(value = "unmarshal")
	public ResponseEntity<?> unmarshalling () {
		try {
			System.out.println("JAXB unmarshalling: \n");
			
			JAXBContext context = JAXBContext.newInstance("main.java.com.xws.a1document.xml.model");
			Unmarshaller unmarshaller = context.createUnmarshaller();
			ObrazacA1 obrazac = (ObrazacA1) unmarshaller.unmarshal(new File("./data/A-1.xml"));
			a1Service.printObrazac(obrazac);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<>("Unmarshalling ended.", HttpStatus.OK);
	}
	
	@GetMapping(value = "marshal")
	public ResponseEntity<?> marshalling() {
		try {
			System.out.println("[INFO] Example 2: JAXB unmarshalling/marshalling.\n");
			
			JAXBContext context = JAXBContext.newInstance("main.java.com.xws.a1document.xml.model");
			Unmarshaller unmarshaller = context.createUnmarshaller();
			ObrazacA1 obrazac = (ObrazacA1) unmarshaller.unmarshal(new File("./data/A-1.xml"));
			obrazac.getZahtev().getPodnosilac().getLice().setEmail("djordjejovanovic27@gmail.com");
			obrazac.getZahtev().getFormaZapisaAutorskogDela().setValue("Stampani tekst");
			
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			
			FileOutputStream fos = new FileOutputStream("./data/marchal.xml");
			marshaller.marshal(obrazac, System.out);
			marshaller.marshal(obrazac, fos);
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new ResponseEntity<>("Marshalling ended.", HttpStatus.OK);
	}
	
	@GetMapping(value="write")
	public ResponseEntity<?> writeToDatabase() throws Exception {
		existService.write(ExistAuthUtilities.loadProperties());
		return new ResponseEntity<>("Successfully stored in database.", HttpStatus.OK);
	}
	
	@GetMapping(value="read")
	public ResponseEntity<?> readFromDatabse() throws Exception {
		existService.read(ExistAuthUtilities.loadProperties());
		return new ResponseEntity<>("Successfully read from database.", HttpStatus.OK);
	}
	
	
	@GetMapping(value="pdf")
	public ResponseEntity<?> generatePdf() throws Exception {
		pdfService.generatePDF();
		return new ResponseEntity<>("PDF generated!", HttpStatus.OK);
	}
	
	@GetMapping(value="xhtml")
	public ResponseEntity<?> generateXhtml() throws Exception {
		xhtmlService.generateXHTML();
		return new ResponseEntity<>("XHTML generated!", HttpStatus.OK);
	}
	
	@GetMapping(value="rdf")
	public ResponseEntity<?> extraxtMetadata() throws JAXBException, IOException {
		JAXBContext context = JAXBContext.newInstance("main.java.com.xws.a1document.xml.model");
		Unmarshaller unmarshaller = context.createUnmarshaller();
		ObrazacA1 obrazac = (ObrazacA1) unmarshaller.unmarshal(new File("./data/A-1.xml"));
		rdfService.extractMetadata(obrazac);
		return new ResponseEntity<>("Metadata extracted!", HttpStatus.OK);
	}
}
