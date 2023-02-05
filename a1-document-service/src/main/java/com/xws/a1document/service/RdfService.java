package main.java.com.xws.a1document.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.List;

import org.apache.jena.base.Sys;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.update.UpdateExecutionFactory;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateProcessor;
import org.apache.jena.update.UpdateRequest;
import org.springframework.stereotype.Service;

import main.java.com.xws.a1document.dto.MetadataSearch;
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
	
	public List<ObrazacA1> metadataSearch(MetadataSearch metadata) throws Exception {
		FusekiAuthUtilities.ConnectionProperties conn = FusekiAuthUtilities.loadProperties();
		StringBuilder sparqlQuery = new StringBuilder("SELECT * FROM <http://localhost:8079/fuseki/DocumentADataset/data/metadata> WHERE {");
		// Query = SELECT * FROM <http://localhost:8079/fuseki/DocumentADataset/data/metadata> WHERE { VALUES ?subject { <http://www.tim777.com/a/A-20230204222150> } ?subject ?predicate ?object . }
		sparqlQuery.append("?subject <http://www.tim777.com/a/broj_prijave> ?broj_prijave . ");
		sparqlQuery.append("FILTER(");
		sparqlQuery.append(String.format("!CONTAINS(?%s,'%s')", metadata.getFirst_type(), metadata.getFirst_value()));
		sparqlQuery.append(")}");
        System.out.println(sparqlQuery);
        QueryExecution query = QueryExecutionFactory.sparqlService(conn.queryEndpoint, sparqlQuery.toString());
        ResultSet results = query.execSelect();
        String varName;
        RDFNode varValue;
        while(results.hasNext()) {
            // A single answer from a SELECT sparqlQuery
        	QuerySolution querySolution = results.next();
        	Iterator<String> variableBindings = querySolution.varNames();
            // Retrieve variable bindings
            while (variableBindings.hasNext()) {
            	varName = variableBindings.next();
                varValue = querySolution.get(varName);
                System.out.println(varName);
                System.out.println(varValue);
                System.out.println("********************");
            }            
        }
		return null;
	}
	
	//
	//SELECT * FROM <http://localhost:8079/fuseki/DocumentADataset/data/metadata>
		//WHERE{ ?data <http://www.tim777.com/a/broj_prijave> ?broj_prijave . FILTER(CONTAINS(?broj_prijave,'A-20230203012800'))}
}
