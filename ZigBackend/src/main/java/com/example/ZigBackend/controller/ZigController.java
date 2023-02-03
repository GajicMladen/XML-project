package com.example.ZigBackend.controller;

import com.example.ZigBackend.rdf.FusekiDB;
import com.example.ZigBackend.rdf.FusekiReader;
import com.example.ZigBackend.service.ZigService;
import com.example.ZigBackend.transformer.PDFTransformer;
import com.example.ZigBackend.gen.Z1Classes.*;
import com.example.ZigBackend.utils.AuthenticationUtilitiesExist.ConnectionProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.xml.bind.JAXBException;
import java.io.*;

import org.springframework.core.io.Resource;
import org.springframework.core.io.InputStreamResource;


@Controller
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "api/zig/")
public class ZigController {

    private static ConnectionProperties conn;

    @Autowired
    private ZigService zigService;

    @GetMapping("test")
    public void test(){
        System.out.println("ide test 1!!!!!!!!!!!!!!");
    }

    @GetMapping(value = "loadTestXML")
    public ResponseEntity<String> getXML() throws JAXBException, FileNotFoundException {

        ZahtevZaPriznanjeZiga zahtevZaPriznanjeZiga = zigService.getTestZahtev();
        return new ResponseEntity<>(zahtevZaPriznanjeZiga.toString(),HttpStatus.OK);
    }

    @GetMapping(value = "saveTestXML")
    public ResponseEntity<String> saveXML(){

        ZahtevZaPriznanjeZiga zahtevZaPriznanjeZiga = null;
        try {
            zahtevZaPriznanjeZiga =  zigService.saveTestXMLinDB();
        } catch (FileNotFoundException e) {
            return new ResponseEntity<>("File not found",HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (JAXBException e) {
            return new ResponseEntity<>("Jaxb err",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(zahtevZaPriznanjeZiga.toString(),HttpStatus.OK);
    }

    @GetMapping(value = "saveXML",consumes = "application/xml")
    public ResponseEntity<String> saveRealXML(@RequestBody ZahtevZaPriznanjeZiga zahtevZaPriznanjeZiga){
        zahtevZaPriznanjeZiga =  zigService.saveXMLinDB(zahtevZaPriznanjeZiga);
        return new ResponseEntity<>(zahtevZaPriznanjeZiga.toString(),HttpStatus.OK);
    }


    @GetMapping(value = "getAllFusekiSPO")
    public ResponseEntity<String> getAllFuseki() throws IOException {
        String res = FusekiReader.readAllDataFromDatabaseJSON();
        return new ResponseEntity<>(res,HttpStatus.OK);
    }

    @GetMapping(value = "testPDF")
    public ResponseEntity<String> testPDF(){
        String msg = "";
        try {
            PDFTransformer pdfTransformer = new PDFTransformer();
            pdfTransformer.generatePDF();
        } catch (IOException e) {
            msg += "IO exception\n";
            msg += e.toString();
            return new ResponseEntity<>(msg , HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            msg += "GEN exception\n";
            msg += e.toString();
            return new ResponseEntity<>(msg , HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("OK" , HttpStatus.OK);
    }
    @GetMapping("/downloadPDF")
    public ResponseEntity<Resource> downloadFile(){
        try {
            File pdfFile = zigService.getTestPDFFile();
            InputStreamResource resource = new InputStreamResource(new FileInputStream(pdfFile));

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + pdfFile.getName())
                    .contentType(MediaType.APPLICATION_PDF)
                    .contentLength(pdfFile.length())
                    .body(resource);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(null);
        }
    }
    @GetMapping("/downloadHTML")
    public ResponseEntity<Resource> downloadHTML(){
        try {
            File htmlFIle = zigService.getTestHTMLFile();
            InputStreamResource resource = new InputStreamResource(new FileInputStream(htmlFIle));

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + htmlFIle.getName())
                    .contentType(MediaType.APPLICATION_PDF)
                    .contentLength(htmlFIle.length())
                    .body(resource);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(null);
        }
    }
}
