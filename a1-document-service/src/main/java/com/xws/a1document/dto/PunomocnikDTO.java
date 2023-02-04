package main.java.com.xws.a1document.dto;

public class PunomocnikDTO {
	private String ime;
	private String prezime;
	private TAdresaDTO adresa;
	
	public PunomocnikDTO() {
		
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

	public TAdresaDTO getAdresa() {
		return adresa;
	}

	public void setAdresa(TAdresaDTO adresa) {
		this.adresa = adresa;
	}
	
	
}
