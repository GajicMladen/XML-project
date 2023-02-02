package com.example.ZigBackend.rdf;

import com.example.ZigBackend.utils.AuthenticationUtilitiesFuseki;
import com.example.ZigBackend.utils.SparqlUtil;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FusekiReader {
    private static final String GRAPH_URI = "metadata"; // http://localhost:8088/fuseki/PatentData/data/metadata

    public static void readAllDataFromDatabase(OutputStream os) throws IOException {
        AuthenticationUtilitiesFuseki.ConnectionProperties conn = AuthenticationUtilitiesFuseki.loadProperties();

        System.out.println("\n[INFO] Retrieving ALL triples from RDF Databse from the graph \"" + conn.dataEndpoint + "/" + GRAPH_URI + "\".");
        String sparqlQuery = SparqlUtil.selectData(conn.dataEndpoint + "/" + GRAPH_URI, "?s ?p ?o");

        // Create a QueryExecution that will access a SPARQL service over HTTP
        QueryExecution query = QueryExecutionFactory.sparqlService(conn.queryEndpoint, sparqlQuery);
        ResultSet results = query.execSelect();
        ResultSetFormatter.out(System.out, results);
        ResultSetFormatter.out(os, results);
        query.close() ;
    }
    public static String readAllDataFromDatabaseJSON() throws IOException {
        AuthenticationUtilitiesFuseki.ConnectionProperties conn = AuthenticationUtilitiesFuseki.loadProperties();

        System.out.println("\n[INFO] Retrieving ALL triples from RDF Databse from the graph \"" + conn.dataEndpoint + "/" + GRAPH_URI + "\".");
        String sparqlQuery = SparqlUtil.selectData(conn.dataEndpoint + "/" + GRAPH_URI, "?s ?p ?o");

        // Create a QueryExecution that will access a SPARQL service over HTTP
        QueryExecution query = QueryExecutionFactory.sparqlService(conn.queryEndpoint, sparqlQuery);
        ResultSet results = query.execSelect();
        String res = ResultSetFormatter.asText(results);
//        ResultSetFormatter.out(System.out, results);
        System.out.println(res);
        query.close() ;
        return res;
    }
}
