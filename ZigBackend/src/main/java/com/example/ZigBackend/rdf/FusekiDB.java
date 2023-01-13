package com.example.ZigBackend.rdf;

import com.example.ZigBackend.gen.Z1Classes.ZahtevZaPriznanjeZiga;
import com.example.ZigBackend.grddl.ZigMetadataExtractor;
import org.apache.jena.rdf.model.Model;

import java.io.IOException;

public class FusekiDB {
    public static void save(ZahtevZaPriznanjeZiga zahtevZaPriznanjeZiga) {
        System.out.println("[INFO] Marshalling customized address book:");
        Model metadataModel = ZigMetadataExtractor.extract(zahtevZaPriznanjeZiga);
        try {
            FusekiWriter.saveRdfGraphToDatabase(metadataModel);
            FusekiReader.readAllDataFromDatabase(System.out);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
