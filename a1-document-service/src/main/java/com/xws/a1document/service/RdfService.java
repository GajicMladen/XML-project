package main.java.com.xws.a1document.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.update.UpdateExecutionFactory;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateProcessor;
import org.apache.jena.update.UpdateRequest;
import org.springframework.stereotype.Service;

import main.java.com.xws.a1document.util.FusekiAuthUtilities;
import main.java.com.xws.a1document.util.SparqlUtil;
import main.java.com.xws.a1document.xml.model.ObrazacA1;

@Service
public class RdfService {
	
	final static private String A_NAMESPACE = "http://www.tim777.com/a";
	private static final String GRAPH_URI = "metadata";

	public void extractMetadata(ObrazacA1 obrazac) throws IOException {
		Model model = getRdfModel(obrazac);
		saveModel(model);
	}

	private Model getRdfModel(ObrazacA1 obrazac) {
		Model model  = ModelFactory.createDefaultModel();
		Resource resource = model.createResource(A_NAMESPACE + "/" + obrazac.getZahtev().getBrojPrijave().getValue());
		addTripletToModel(model, resource, "broj_prijave", obrazac.getZahtev().getBrojPrijave().getValue());
		addTripletToModel(model, resource, "datum_podnosenja", obrazac.getZahtev().getDatumPodnosenja().getValue());
		addTripletToModel(model, resource, "email_podnosilac", obrazac.getZahtev().getPodnosilac().getLice().getEmail());
		addTripletToModel(model, resource, "telefon_podnosilac", obrazac.getZahtev().getPodnosilac().getLice().getTelefon());
		addTripletToModel(model, resource, "naslov_dela", obrazac.getZahtev().getNaslovAutorskogDela().getNaslov().getValue());
		addTripletToModel(model, resource, "vrsta_dela", obrazac.getZahtev().getVrstaAutorskogDela().getValue());
		addTripletToModel(model, resource, "forma_zapisa", obrazac.getZahtev().getFormaZapisaAutorskogDela().getValue());
        return model;
	}
	
	private void addTripletToModel(Model model, Resource resource, String propertyName, String propertyValue) {
        Property prop = model.createProperty(A_NAMESPACE + "/predicate/" + propertyName);
        Literal val = model.createLiteral(propertyValue);
        Statement triplet = model.createStatement(resource, prop, val);

        model.add(triplet);
    }
	
	private void saveModel(Model model) throws IOException {
		FusekiAuthUtilities.ConnectionProperties conn = FusekiAuthUtilities.loadProperties();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		model.write(out, "N-TRIPLES");
		System.out.println("\n[INFO] Populating graph \"" + conn.dataEndpoint + "/" + GRAPH_URI + "\" with extracted metadata.");		
		String sparqlUpdate = insertData(conn.dataEndpoint + "/" + GRAPH_URI, new String(out.toByteArray()));
		System.out.println(sparqlUpdate);
		UpdateRequest update = UpdateFactory.create(sparqlUpdate);

		@SuppressWarnings("deprecation")
		UpdateProcessor processor = UpdateExecutionFactory.createRemote(update, conn.updateEndpoint);
		processor.execute();
	}
	
	public static String insertData(String graphURI, String ntriples) {
		return String.format("INSERT DATA { GRAPH <%1$s> { %2$s } }", graphURI, ntriples);
	}
	
	public String getJsonMetadata(String sparqlQuery) throws Exception {
		FusekiAuthUtilities.ConnectionProperties conn = FusekiAuthUtilities.loadProperties();
		System.out.println("\n[INFO] Getting RDF data from \"" + conn.dataEndpoint + "/" + GRAPH_URI + "\".");
		String sparql = SparqlUtil.selectData(conn.dataEndpoint + "/" + GRAPH_URI, sparqlQuery);
		System.out.println(sparql);
		QueryExecution query = QueryExecutionFactory.sparqlService(conn.queryEndpoint, sparql);
		ResultSet results = query.execSelect();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ResultSetFormatter.outputAsJSON(out, results);
		String json = new String(out.toByteArray(), StandardCharsets.UTF_8);
		System.out.println(json);
	    query.close();
	    return json;
	}

	public String getRdfMetadata(String sparqlQuery) throws Exception {
		FusekiAuthUtilities.ConnectionProperties conn = FusekiAuthUtilities.loadProperties();
		System.out.println("\n[INFO] Getting RDF data from \"" + conn.dataEndpoint + "/" + GRAPH_URI + "\".");
		String sparql = SparqlUtil.constructData(conn.dataEndpoint + "/" + GRAPH_URI, sparqlQuery);
        System.out.println(sparql);
        QueryExecution query = QueryExecutionFactory.sparqlService(conn.queryEndpoint, sparql);
        Model model = query.execConstruct();

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        model.write(out, "N-TRIPLES");
        query.close();
        return out.toString();		
	}
	
	
}
