package com.example.patentservice.dto;

public class PodnosilacPrijave {
	  private String tipPodnosioca;
	  private FizickoLice fizickoLice;
	  private String poslovnoIme;
	  private Adresa lokacija;
	  private Kontakt kontakt;
	  private boolean podnosilacPronalazac;
	public String getTipPodnosioca() {
		return tipPodnosioca;
	}
	public void setTipPodnosioca(String tipPodnosioca) {
		this.tipPodnosioca = tipPodnosioca;
	}
	public FizickoLice getFizickoLice() {
		return fizickoLice;
	}
	public void setFizickoLice(FizickoLice fizickoLice) {
		this.fizickoLice = fizickoLice;
	}
	public String getPoslovnoIme() {
		return poslovnoIme;
	}
	public void setPoslovnoIme(String poslovnoIme) {
		this.poslovnoIme = poslovnoIme;
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
	public boolean isPodnosilacPronalazac() {
		return podnosilacPronalazac;
	}
	public void setPodnosilacPronalazac(boolean podnosilacPronalazac) {
		this.podnosilacPronalazac = podnosilacPronalazac;
	}

	  
	}
