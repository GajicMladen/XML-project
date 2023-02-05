package com.example.patentservice.controller;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.patentservice.beans.ZahtevZaPriznanjePatenta;
import com.example.patentservice.dto.Resenje;
import com.example.patentservice.dto.Zahtev;
import com.example.patentservice.service.PatentService;


@RestController
@RequestMapping(value = "api/patent")
public class PatentController {
	
	public static final String COLLECTION_ID = "db/sample/patent";
	
	@Autowired
	private PatentService patentService;
	
	@PostMapping(value = "/createPatent", produces = "application/xml", consumes = "application/xml")
	public ResponseEntity<?> createPatent(@RequestBody Zahtev zahtev) {
		ZahtevZaPriznanjePatenta zahtevBean = patentService.convertDTOToBean(zahtev);
		patentService.writePatentBeanToDb(zahtevBean);
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}
	
	@GetMapping(value = "/getAllPatents/{status}", produces = "application/xml", consumes = "application/xml")
	public ResponseEntity<?> getAllPatents(@PathVariable String status) {
		List<ZahtevZaPriznanjePatenta> ret = patentService.getAllZahtevi(status);
		return new ResponseEntity<>(ret, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getPatentById/{id}", produces = "application/xml", consumes = "application/xml")
	public ResponseEntity<?> getPatentById(@PathVariable String id) {
		ZahtevZaPriznanjePatenta ret = patentService.getZahtevById(id);
		if (ret == null)
			return new ResponseEntity<>("<error>Patent nije pronadjen!</error>", HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(ret, HttpStatus.OK);
	}
	
	@GetMapping(value = "/search", produces = "application/xml", consumes = "application/xml")
	public ResponseEntity<?> searchPatents(@RequestParam String query, @RequestParam String status) {
		List<ZahtevZaPriznanjePatenta> ret = patentService.searchPatents(query, status);
		return new ResponseEntity<>(ret, HttpStatus.OK);
	}
	
	@GetMapping(value="/downloadPDF/{id}")
	public ResponseEntity<?> downloadPDF(@PathVariable String id){
		File pdf = patentService.generatePDF(id);
		return new ResponseEntity<>(new FileSystemResource(pdf), HttpStatus.OK);
	}
	
	@GetMapping(value="/downloadXHTML/{id}")
	public ResponseEntity<?> downloadXHTML(@PathVariable String id) {
		File xhtml = patentService.generateXHTML(id);
		return new ResponseEntity<>(new FileSystemResource(xhtml), HttpStatus.OK);
	}
	
	@GetMapping(value="/downloadRDF/{id}", produces = "application/xml")
	public ResponseEntity<?> downloadRDFL(@PathVariable String id) {
		try {
			String rdf = patentService.getMetadataById(id, "RDF");
			return new ResponseEntity<>(rdf, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null , HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value="/downloadJSON/{id}")
	public ResponseEntity<?> downloadJSON(@PathVariable String id) {
		try {
			String json = patentService.getMetadataById(id, "JSON");
			return new ResponseEntity<>(json, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(value="/sendResenje", produces = "application/xml", consumes = "application/xml")
	public ResponseEntity<?> sendResenje(@RequestBody Resenje resenje) {
		patentService.writeResenjeBeanToDb(resenje);
		ZahtevZaPriznanjePatenta ret = patentService.getZahtevById(resenje.getPatent());
		return ResponseEntity.status(HttpStatus.OK).body(ret);
	}
}
