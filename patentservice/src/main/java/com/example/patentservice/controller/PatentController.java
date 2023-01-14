package com.example.patentservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.patentservice.service.PatentService;

@Controller
@RequestMapping(value = "api/patent/")
public class PatentController {
	
	@Autowired
	private PatentService patentService;
	
	@PostMapping(value = "writeXMLFiletoDB/{patentId}")
	public ResponseEntity<?> writeXMLFileToDB(@PathVariable String patentId){
		String filePath = "src/main/resources/data/" + patentId +".xml";
		patentService.writeXMLFileToDB(filePath);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	@GetMapping(value = "readXMLfromDB/{patentId}", produces = "application/xml")
	public ResponseEntity<?> readXMLfromDB(@PathVariable String patentId) {
		String xml = patentService.readXMLfromDB(patentId);
		if (xml == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(xml, HttpStatus.OK);
	}
	
	@GetMapping(value = "generatePDF/{patentId}")
	public ResponseEntity<?> generatePDF(@PathVariable String patentId) {
		boolean success = patentService.generatePDF(patentId);
		if (!success) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping(value = "generateXHTML/{patentId}")
	public ResponseEntity<?> generateXHTML(@PathVariable String patentId) {
		boolean success = patentService.generateXHTML(patentId);
		if (!success) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping(value = "writeMetadataRDF/{patentId}")
	public ResponseEntity<?> writeMetadataRDF(@PathVariable String patentId) {
		boolean success = patentService.writeMetadataRDF(patentId);
		if (!success) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
