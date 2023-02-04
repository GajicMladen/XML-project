package main.java.com.xws.a1document.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import main.java.com.xws.a1document.dto.ObrazacA1DTO;
import main.java.com.xws.a1document.dto.ResenjeDTO;
import main.java.com.xws.a1document.service.A1Service;
import main.java.com.xws.a1document.service.ExistService;
import main.java.com.xws.a1document.service.PdfService;
import main.java.com.xws.a1document.service.RdfService;
import main.java.com.xws.a1document.service.XhtmlService;
import main.java.com.xws.a1document.util.ExistAuthUtilities;
import main.java.com.xws.a1document.xml.model.ObrazacA1;


@Controller
@CrossOrigin(origins = "http://localhost:4200")
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
	
	
	@PostMapping(value="save", consumes = "application/xml")
	public ResponseEntity<?> save(@RequestBody ObrazacA1DTO obrazac) throws Exception {
		a1Service.save(a1Service.getObrazacA1(obrazac));
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping(value="get", produces = "application/xml")
	public ResponseEntity<?> getByBrojPrijave(@RequestParam String id) throws Exception {
		ObrazacA1 obrazac = a1Service.getByBrojPrijave(id);
		return new ResponseEntity<>(obrazac, HttpStatus.OK);
	}
	
	@GetMapping(value="getAllPending", produces = "application/xml")
	public ResponseEntity<?> getAllPending() throws Exception {
		List<ObrazacA1> obrasci = a1Service.getAllPending();				
		return new ResponseEntity<>(obrasci, HttpStatus.OK);
	}
	
	@GetMapping(value="getAllApproved", produces = "application/xml")
	public ResponseEntity<?> getAllApproved() throws Exception {
		List<ObrazacA1> obrasci = a1Service.getAllApproved();
		return new ResponseEntity<>(obrasci, HttpStatus.OK);
	}
	
	@GetMapping(value="search", produces = "application/xml")
	public ResponseEntity<?> search(@RequestParam String query) {
		List<ObrazacA1> obrasci = new ArrayList<ObrazacA1>();
		try {
			obrasci = existService.search(query, ExistAuthUtilities.loadProperties());
		} catch (Exception e) { 
			e.printStackTrace();
		}
		return new ResponseEntity<>(obrasci, HttpStatus.OK);
	}
	
	@PostMapping(value="approved", consumes = "application/xml")
	public ResponseEntity<?> approveZahtev(@RequestBody ResenjeDTO resenjeDTO) throws Exception {
		a1Service.approveZahtev(resenjeDTO);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping(value="denied", consumes = "application/xml")
	public ResponseEntity<?> denyZahtev(@RequestBody ResenjeDTO resenjeDTO) throws Exception {
		a1Service.denyZahtev(resenjeDTO);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping(value = "getJson")
    public ResponseEntity<?> getJsonMetadataById(@RequestParam String id) throws Exception {
		String result = a1Service.getMetadataById(id, "JSON");              
        return new ResponseEntity<>(result, HttpStatus.OK);        
    }

    @GetMapping(value = "getRdf", produces = "application/xml")
    public ResponseEntity<String> getRdfMetadataById(@RequestParam String id) throws Exception {
        String result = a1Service.getMetadataById(id, "RDF");        
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    @GetMapping(value="getPdf")
	public ResponseEntity<?> generatePdf(@RequestParam String id) throws Exception {
		File pdf = pdfService.getPdf(id);		
        return new ResponseEntity<>(new FileSystemResource(pdf), HttpStatus.OK);		
	}
    
    @GetMapping(value="getXhtml")
    public ResponseEntity<?> generateXhtml(@RequestParam String id) throws Exception {
    	File html = xhtmlService.getXhtml(id);
    	return new ResponseEntity<>(new FileSystemResource(html), HttpStatus.OK);
    }
	
}
