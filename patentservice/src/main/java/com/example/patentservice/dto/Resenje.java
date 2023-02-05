package com.example.patentservice.dto;

import javax.xml.datatype.XMLGregorianCalendar;

public class Resenje {
	private XMLGregorianCalendar datumResenja;
	private String status;
	private String sifra;
	private String sluzbenik;
	private String patent;
	private String obrazlozenje;
	
	
	public String getObrazlozenje() {
		return obrazlozenje;
	}
	public void setObrazlozenje(String obrazlozenje) {
		this.obrazlozenje = obrazlozenje;
	}
	public XMLGregorianCalendar getDatumResenja() {
		return datumResenja;
	}
	public void setDatumResenja(XMLGregorianCalendar datumResenja) {
		this.datumResenja = datumResenja;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSifra() {
		return sifra;
	}
	public void setSifra(String sifra) {
		this.sifra = sifra;
	}
	public String getSluzbenik() {
		return sluzbenik;
	}
	public void setSluzbenik(String sluzbenik) {
		this.sluzbenik = sluzbenik;
	}
	public String getPatent() {
		return patent;
	}
	public void setPatent(String patent) {
		this.patent = patent;
	}
	
	@Override
	public String toString() {
	    return "<resenje>\n" + 
	           "  <datumResenja>" + datumResenja + "</datumResenja>\n" + 
	           "  <status>" + status + "</status>\n" + 
	           "  <sifra>" + sifra + "</sifra>\n" + 
	           "  <sluzbenik>" + sluzbenik + "</sluzbenik>\n" + 
	           "  <patent>" + patent + "</patent>\n" + 
	           "  <obrazlozenje>" + obrazlozenje + "</obrazlozenje>\n" + 
	           "</resenje>";
	}
}
