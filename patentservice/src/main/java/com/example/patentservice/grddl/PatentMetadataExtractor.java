package com.example.patentservice.grddl;

import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;

import com.example.patentservice.beans.TDetaljiRanijePrijave;
import com.example.patentservice.beans.ZahtevZaPriznanjePatenta;

public class PatentMetadataExtractor {
	
	final static private String PATENT_NAMESPACE = "http://www.tim777.com/patent";

    public static Model extract(ZahtevZaPriznanjePatenta zahtev) {
        // status_zahteva
    	// sluzbenik
    	// obrazlozenje
    	// broj_prijave
    	// datum_prijema
    	// datum_podnosenja
    	// podnosilac_email
    	// detalji_ranije_prijave
    	
        Model model = ModelFactory.createDefaultModel();

        Resource resource = model.createResource(PATENT_NAMESPACE + "/" + zahtev.getPodaciZavod().getBrojPrijave());
        
        addRDFTripletToModel(model, resource, "status_zahteva", zahtev.getPodaciZavod().getStatusZahteva());
        addRDFTripletToModel(model, resource, "sluzbenik", zahtev.getPodaciZavod().getSluzbenik());
        addRDFTripletToModel(model, resource, "obrazlozenje", zahtev.getPodaciZavod().getObrazlozenje());
        addRDFTripletToModel(model, resource, "broj_prijave", zahtev.getPodaciZavod().getBrojPrijave());
        addRDFTripletToModel(model, resource, "datum_prijema", zahtev.getPodaciZavod().getDatumPrijema().toString());
        addRDFTripletToModel(model, resource, "datum_podnosenja", zahtev.getPodaciZavod().getDatumPodnosenja().toString());
        addRDFTripletToModel(model, resource, "podnosilac_email", zahtev.getPodnosilacPrijave().getKontakt().getEMail());
        
        
        for(TDetaljiRanijePrijave drp: zahtev.getPriznanjePravaPrvenstvaIzRanijihPrijava().getDetaljiRanijePrijave()) {
        	addRDFTripletToModel(model, resource, "detalji_ranije_prijave", drp.getBrojPrijave());
        }
        
        return model;
    }

    public static void addRDFTripletToModel(Model model, Resource resource, String propertyName, String propertyValue) {
        Property prop = model.createProperty(PATENT_NAMESPACE + "/predicate/" + propertyName);
        Literal val = model.createLiteral(propertyValue);
        Statement triplet = model.createStatement(resource, prop, val);

        model.add(triplet);
    }
}
