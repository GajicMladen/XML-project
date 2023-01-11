package com.example.ZigBackend.service;

import com.example.ZigBackend.db.ZigDB;
import org.exist.xmldb.EXistResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileNotFoundException;
import com.example.ZigBackend.gen.Z1Classes.ZahtevZaPriznanjeZiga;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

import static com.example.ZigBackend.utils.Utils.getTestZahtevZaPrizanjeZiga;

@Service
public class ZigService {

    public ZahtevZaPriznanjeZiga getTestZahtev() throws JAXBException, FileNotFoundException {
        ZahtevZaPriznanjeZiga zahtevZaPriznanjeZiga = getTestZahtevZaPrizanjeZiga();
        return zahtevZaPriznanjeZiga;
    }

    public ZahtevZaPriznanjeZiga saveTestXMLinDB() throws FileNotFoundException, JAXBException {
        ZahtevZaPriznanjeZiga zahtevZaPriznanjeZiga = getTestZahtevZaPrizanjeZiga();
        zahtevZaPriznanjeZiga.setBrojZahteva("Z1_MG");
        ZigDB.save(zahtevZaPriznanjeZiga);
        return zahtevZaPriznanjeZiga;
    }
}
