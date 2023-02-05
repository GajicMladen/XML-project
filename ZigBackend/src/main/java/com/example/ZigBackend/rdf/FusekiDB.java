package com.example.ZigBackend.rdf;

import com.example.ZigBackend.DTOs.MetadataSearchParams;
import com.example.ZigBackend.gen.Z1Classes.ZahtevZaPriznanjeZiga;
import com.example.ZigBackend.grddl.ZigMetadataExtractor;
import org.apache.jena.rdf.model.Model;

import java.io.IOException;
import java.util.List;

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

    public static List<String> findByMetadata(MetadataSearchParams param) throws IOException {
        return FusekiReader.findByMetadata(param);
    }

    public static List<String> findByMultipleMetadata(List<MetadataSearchParams> params) throws IOException {
        return FusekiReader.findByMetadata(params);
    }

    public static String getRdfString(String brojPrijave) throws Exception {
        return FusekiReader.getRdfString(brojPrijave);
    }

    public static String getJsonString(String brojPrijave) throws Exception {
        return FusekiReader.getJsonString(brojPrijave);
    }
}
