package com.example.patentservice.db;

import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.Model;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.example.patentservice.utils.SparqlUtil;

@Component
public class FusekiManager {
	
	private static final String PATENT_NAMED_GRAPH_URI = "/example/patent/metadata"; 
	
	@Value("${fuseki.conn.endpoint}")
	private String endpoint;
	@Value("${fuseki.conn.dataset}")
	private String dataset;
	@Value("${fuseki.conn.query}")
	private String query;
	@Value("${fuseki.conn.update}")
	private String update;
	@Value("${fuseki.conn.data}")
	private String data;
	
	public String getEndpoint() {
		return endpoint;
	}

	public String getDataset() {
		return dataset;
	}
	
	public String getQueryEndpoint() {
		return String.join("/", endpoint, dataset, query);
	}

	public String getUpdateEndpoint() {
		return String.join("/", endpoint, dataset, update);
	}

	public String getDataEndpoint() {
		return String.join("/", endpoint, dataset, data);
	}
	
	public String getJsonMetadata(String sparqlQuery) throws Exception {
		String sparql = SparqlUtil.selectData(getDataEndpoint() + PATENT_NAMED_GRAPH_URI, sparqlQuery);
		QueryExecution q = QueryExecutionFactory.sparqlService(getQueryEndpoint(), sparql);
		ResultSet results = q.execSelect();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ResultSetFormatter.outputAsJSON(out, results);
		String json = new String(out.toByteArray(), StandardCharsets.UTF_8);
	    q.close();
	    return json;
	}
	
	public String getRdfMetadata(String sparqlQuery) {
		String sparql = SparqlUtil.constructData(getDataEndpoint() + PATENT_NAMED_GRAPH_URI, sparqlQuery);
		QueryExecution q = QueryExecutionFactory.sparqlService(getQueryEndpoint(), sparql);
		Model model = q.execConstruct();
		
		StringWriter out = new StringWriter();
		model.write(out, "N-TRIPLES");
		q.close();
		return out.toString();
		
	}
	
}
