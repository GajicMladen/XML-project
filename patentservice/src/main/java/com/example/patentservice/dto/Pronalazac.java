package com.example.patentservice.dto;

public class Pronalazac {
	  private FizickoLice fizickoLice;
	  private Adresa lokacija;
	  private Kontakt kontakt;
	public FizickoLice getFizickoLice() {
		return fizickoLice;
	}
	public void setFizickoLice(FizickoLice fizickoLice) {
		this.fizickoLice = fizickoLice;
	}
	public Adresa getLokacija() {
		return lokacija;
	}
	public void setLokacija(Adresa lokacija) {
		this.lokacija = lokacija;
	}
	public Kontakt getKontakt() {
		return kontakt;
	}
	public void setKontakt(Kontakt kontakt) {
		this.kontakt = kontakt;
	}

	  
}
