package com.example.ZigBackend.grddl;

import com.example.ZigBackend.gen.Z1Classes.ZahtevZaPriznanjeZiga;
import com.example.ZigBackend.utils.Utils;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;

public class ZigMetadataExtractor {
    final static private String ZIG_NAMESPACE = "http://www.tim777.com/zig";

    static public Model extract(ZahtevZaPriznanjeZiga zahtev) {
        //podnosilac_email
        // pronalazac_email
        // punomocnik_email
        // vrsta_ziga
        // broj_zahteva (id)
        // datum_podnosenja
        Model model = ModelFactory.createDefaultModel();

        String idPrijave = Utils.formatNameOfRequestForZig(zahtev.getBrojZahteva(), ".xml");
        Resource resource = model.createResource(ZIG_NAMESPACE + "/" + zahtev.getBrojZahteva());

        //
        addRDFTripletToModel(model, resource, "podnosilac_email", zahtev.getPodnosilac().getKontakt().getEmail());
        addRDFTripletToModel(model, resource, "pronalazac_email", zahtev.getPunomocnik().getKontakt().getEmail());
//        if(zahtev.getZajednickiPosrednik().getKontakt() != null)
//            addRDFTripletToModel(model, resource, "punomocnik_email", zahtev.getZajednickiPosrednik().getKontakt().getEmail());
        addRDFTripletToModel(model, resource, "vrsta_ziga", zahtev.getOpisZiga().getTipZiga().value());
        addRDFTripletToModel(model, resource, "broj_zahteva", zahtev.getBrojZahteva());
//        addRDFTripletToModel(model, resource, "datum_podnosenja", zahtev.getD().getDatumPrijema().toString());
        return model;
    }

    static private void addRDFTripletToModel(Model model, Resource resource, String propertyName, String propertyValue) {
        Property prop = model.createProperty(ZIG_NAMESPACE + "/predicate/" + propertyName);
        Literal val = model.createLiteral(propertyValue);
        Statement triplet = model.createStatement(resource, prop, val);

        model.add(triplet);
    }
}