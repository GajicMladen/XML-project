package com.example.patentservice.dto;

import java.util.List;

public class Zahtev {
	  private NazivPronalaska nazivPronalaska;
	  private PodnosilacPrijave podnosilacPrijave;
	  private PodaciOPronalazacu podaciOPronalazacu;
	  private Punomocnik punomocnik;
	  private Adresa adresaZaDostavljanje;
	  private NacinDostavljanja nacinDostavljanja;
	  private Dopuna dopuna;
	  private List<PravaPrvenstva> priznavanjePravaPrvenstva;
	
	public NazivPronalaska getNazivPronalaska() {
		return nazivPronalaska;
	}
	public void setNazivPronalaska(NazivPronalaska nazivPronalaska) {
		this.nazivPronalaska = nazivPronalaska;
	}
	public PodnosilacPrijave getPodnosilacPrijave() {
		return podnosilacPrijave;
	}
	public void setPodnosilacPrijave(PodnosilacPrijave podnosilacPrijave) {
		this.podnosilacPrijave = podnosilacPrijave;
	}
	public PodaciOPronalazacu getPodaciOPronalazacu() {
		return podaciOPronalazacu;
	}
	public void setPodaciOPronalazacu(PodaciOPronalazacu podaciOPronalazacu) {
		this.podaciOPronalazacu = podaciOPronalazacu;
	}
	public Punomocnik getPunomocnik() {
		return punomocnik;
	}
	public void setPunomocnik(Punomocnik punomocnik) {
		this.punomocnik = punomocnik;
	}
	public Adresa getAdresaZaDostavljanje() {
		return adresaZaDostavljanje;
	}
	public void setAdresaZaDostavljanje(Adresa adresaZaDostavljanje) {
		this.adresaZaDostavljanje = adresaZaDostavljanje;
	}
	public NacinDostavljanja getNacinDostavljanja() {
		return nacinDostavljanja;
	}
	public void setNacinDostavljanja(NacinDostavljanja nacinDostavljanja) {
		this.nacinDostavljanja = nacinDostavljanja;
	}
	public Dopuna getDopuna() {
		return dopuna;
	}
	public void setDopuna(Dopuna dopuna) {
		this.dopuna = dopuna;
	}
	public List<PravaPrvenstva> getPriznavanjePravaPrvenstva() {
		return priznavanjePravaPrvenstva;
	}
	public void setPriznavanjePravaPrvenstva(List<PravaPrvenstva> priznavanjePravaPrvenstva) {
		this.priznavanjePravaPrvenstva = priznavanjePravaPrvenstva;
	}

	  
	}

