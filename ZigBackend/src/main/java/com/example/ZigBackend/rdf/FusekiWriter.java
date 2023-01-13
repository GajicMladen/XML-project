package com.example.ZigBackend.rdf;

import java.io.*;

import com.example.ZigBackend.grddl.ZigMetadataExtractor;
import com.example.ZigBackend.utils.AuthenticationUtilitiesFuseki;
import com.example.ZigBackend.utils.SparqlUtil;
import com.example.ZigBackend.utils.Utils;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.update.UpdateExecutionFactory;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateProcessor;
import org.apache.jena.update.UpdateRequest;
import org.xml.sax.SAXException;

import javax.xml.transform.TransformerException;


/**
 * 
 * [PRIMER 1]
 * 
 * Primer demonstrira rad sa Apache Jena programskim API-em za izvrsavanje CRUD 
 * operacija nad semantickim grafovima skladistenim u Apache Jena Fuseki RDF storu. 
 * 
 * - Kreiranje RDF modela
 * - Učitavanje RDF/XML u model
 * - Kreiranje/brisanje imenovanog grafa
 * - Populisanje grafa tripletima iz RDF modela
 * 
 */
public class FusekiWriter {
	private static final String GRAPH_URI = "metadata"; // http://localhost:8088/fuseki/ZigData/data/metadata

	public static void saveRdfGraphToDatabase(Model metadataModel) throws IOException {
		AuthenticationUtilitiesFuseki.ConnectionProperties conn = AuthenticationUtilitiesFuseki.loadProperties();

		// zbog prirode metoda koje slede, potrebno je sadrzaj grafa (tj. modela) pretociti u instancu ByteArrayOutputStream-a
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		metadataModel.write(out, SparqlUtil.NTRIPLES);

		System.out.println("\n[INFO] Populating graph \"" + conn.dataEndpoint + "/" + GRAPH_URI + "\" with extracted metadata.");
		// ovde se prethodno popunjeni ByteArray objekat prosledjuje da bi se napravio quary string za komunikaciju sa bazom
		String sparqlUpdate = SparqlUtil.insertData(conn.dataEndpoint + "/" + GRAPH_URI, new String(out.toByteArray()));
		System.out.println(sparqlUpdate);

		UpdateRequest update = UpdateFactory.create(sparqlUpdate);

		UpdateProcessor processor = UpdateExecutionFactory.createRemote(update, conn.updateEndpoint);
		processor.execute();
	}
}
