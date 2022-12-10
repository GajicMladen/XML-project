package com.example.xmltim11.controller;

import com.example.xmltim11.gen.Z1Classes.ZahtevZaPriznanjeZiga;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileNotFoundException;


@Controller
@RequestMapping(value = "api/zig/")
public class ZigController {


    @GetMapping(value = "loadXML")
    public ResponseEntity<Integer> getXML(){

        try{

        System.out.println("[INFO] Example 1: JAXB unmarshalling.\n");

        // DefiniÅ¡e se JAXB kontekst (putanja do paketa sa JAXB bean-ovima)
        JAXBContext context = JAXBContext.newInstance("com.example.xmltim11.gen.Z1Classes");

        // Unmarshaller je objekat zaduÅ¾en za konverziju iz XML-a u objektni model
        Unmarshaller unmarshaller = context.createUnmarshaller();

        File file = ResourceUtils.getFile("classpath:data/Z1.xml");
        // Unmarshalling generiÅ¡e objektni model na osnovu XML fajla
        ZahtevZaPriznanjeZiga zahtevZaPriznanjeZiga = (ZahtevZaPriznanjeZiga) unmarshaller.unmarshal(file);


        System.out.println(zahtevZaPriznanjeZiga);


        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        return  new ResponseEntity(1,HttpStatus.OK);
    }
}
