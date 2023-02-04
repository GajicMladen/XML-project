package main.java.com.xws.a1document.dto;

public class TPravnoLiceDTO {
	private String poslovno_ime;
	private SedisteDTO sediste;
	private String telefon;
	private String email;
	
	public TPravnoLiceDTO() {
		
	}

	public String getPoslovno_ime() {
		return poslovno_ime;
	}

	public void setPoslovno_ime(String poslovno_ime) {
		this.poslovno_ime = poslovno_ime;
	}

	public SedisteDTO getSediste() {
		return sediste;
	}

	public void setSediste(SedisteDTO sediste) {
		this.sediste = sediste;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
