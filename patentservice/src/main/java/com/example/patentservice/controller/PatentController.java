package com.example.patentservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.patentservice.beans.ZahtevZaPriznanjePatenta;
import com.example.patentservice.dto.Zahtev;
import com.example.patentservice.service.PatentService;


@RestController
@RequestMapping(value = "api/patent/")
public class PatentController {
	
	public static final String COLLECTION_ID = "db/sample/patent";
	
	@Autowired
	private PatentService patentService;
	
	@PostMapping(value = "createPatent", produces = "application/xml", consumes = "application/xml")
	public ResponseEntity<?> createPatent(@RequestBody Zahtev zahtev) {
		ZahtevZaPriznanjePatenta zahtevBean = patentService.convertDTOToBean(zahtev);
		patentService.writePatentBeanToDb(zahtevBean);
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}
}
