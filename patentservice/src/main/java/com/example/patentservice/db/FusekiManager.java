package com.example.patentservice.db;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FusekiManager {
	
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
}
