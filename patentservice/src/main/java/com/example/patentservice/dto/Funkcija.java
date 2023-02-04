package com.example.patentservice.dto;

public class Funkcija {
	  private boolean punomocnikZastupanje;
	  private boolean punomocnikPrijemPismena;
	  private boolean zajednickiPredstavnik;
	public boolean isPunomocnikZastupanje() {
		return punomocnikZastupanje;
	}
	public void setPunomocnikZastupanje(boolean punomocnikZastupanje) {
		this.punomocnikZastupanje = punomocnikZastupanje;
	}
	public boolean isPunomocnikPrijemPismena() {
		return punomocnikPrijemPismena;
	}
	public void setPunomocnikPrijemPismena(boolean punomocnikPrijemPismena) {
		this.punomocnikPrijemPismena = punomocnikPrijemPismena;
	}
	public boolean isZajednickiPredstavnik() {
		return zajednickiPredstavnik;
	}
	public void setZajednickiPredstavnik(boolean zajednickiPredstavnik) {
		this.zajednickiPredstavnik = zajednickiPredstavnik;
	}

	  
}
