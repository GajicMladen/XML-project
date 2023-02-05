package com.example.patentservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.patentservice.beans.ZahtevZaPriznanjePatenta;
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
	
	@GetMapping(value = "/search/{query}", produces = "application/xml", consumes = "application/xml")
	public ResponseEntity<?> searchPatents(@PathVariable String query) {
		List<ZahtevZaPriznanjePatenta> ret = patentService.searchPatents(query);
		return new ResponseEntity<>(ret, HttpStatus.OK);
	}
}
