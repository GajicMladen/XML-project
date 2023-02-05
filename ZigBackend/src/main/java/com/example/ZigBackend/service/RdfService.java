package com.example.ZigBackend.service;

import com.example.ZigBackend.utils.AuthenticationUtilitiesFuseki;
import com.example.ZigBackend.utils.SparqlUtil;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.Model;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;

@Service
public class RdfService {

    final static private String A_NAMESPACE = "http://www.tim777.com/zig";
    private static final String GRAPH_URI = "metadata";

    public String getJsonMetadata(String sparqlQuery) throws Exception {
        AuthenticationUtilitiesFuseki.ConnectionProperties conn = AuthenticationUtilitiesFuseki.loadProperties();
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
        AuthenticationUtilitiesFuseki.ConnectionProperties conn = AuthenticationUtilitiesFuseki.loadProperties();
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
