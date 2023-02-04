package main.java.com.xws.a1document.dto;

public class TFizickoLiceDTO {
	private String ime;
	private String prezime;
	private String drzavljanstvo;
	private TAdresaDTO adresa;
	private String telefon;
	private String email;
	
	public TFizickoLiceDTO() {
		
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getDrzavljanstvo() {
		return drzavljanstvo;
	}

	public void setDrzavljanstvo(String drzavljanstvo) {
		this.drzavljanstvo = drzavljanstvo;
	}

	public TAdresaDTO getAdresa() {
		return adresa;
	}

	public void setAdresa(TAdresaDTO adresa) {
		this.adresa = adresa;
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
