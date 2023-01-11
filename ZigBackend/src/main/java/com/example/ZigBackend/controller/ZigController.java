package com.example.ZigBackend.controller;

import com.example.ZigBackend.service.ZigService;
import com.example.ZigBackend.utils.AuthenticationUtilities;
import com.example.ZigBackend.gen.Z1Classes.*;
import com.example.ZigBackend.utils.AuthenticationUtilities;
import com.example.ZigBackend.utils.AuthenticationUtilities.ConnectionProperties;
import org.exist.xmldb.EXistResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.CollectionManagementService;
import org.xmldb.api.modules.XMLResource;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileNotFoundException;


@Controller
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
}
