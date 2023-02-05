package com.example.patentservice.dto;

public class Dopuna {
    private boolean dopunskaPrijava;
    private boolean izdvojenaPrijava;
    private String brojOsnovnePrijave;
    private String datumOsnovnePrijave;
    
	public boolean isDopunskaPrijava() {
		return dopunskaPrijava;
	}
	public void setDopunskaPrijava(boolean dopunskaPrijava) {
		this.dopunskaPrijava = dopunskaPrijava;
	}
	public boolean isIzdvojenaPrijava() {
		return izdvojenaPrijava;
	}
	public void setIzdvojenaPrijava(boolean izdvojenaPrijava) {
		this.izdvojenaPrijava = izdvojenaPrijava;
	}
	public String getBrojOsnovnePrijave() {
		return brojOsnovnePrijave;
	}
	public void setBrojOsnovnePrijave(String brojOsnovnePrijave) {
		this.brojOsnovnePrijave = brojOsnovnePrijave;
	}
	public String getDatumOsnovnePrijave() {
		return datumOsnovnePrijave;
	}
	public void setDatumOsnovnePrijave(String datumOsnovnePrijave) {
		this.datumOsnovnePrijave = datumOsnovnePrijave;
	}
    
    
}
