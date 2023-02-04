package main.java.com.xws.a1document.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import main.java.com.xws.a1document.xml.model.ObrazacA1.Zahtev.NaslovDeloPrerade;
import main.java.com.xws.a1document.xml.model.ObrazacA1.Zahtev.Punomocnik;
import main.java.com.xws.a1document.xml.model.TAutor;
import main.java.com.xws.a1document.xml.model.TAutorPreminuo;
import main.java.com.xws.a1document.xml.model.TAutorZiv;
import main.java.com.xws.a1document.xml.model.TPravnoLice;

@XmlRootElement(name="obrazac")
public class ObrazacA1DTO {
	@XmlElement(name="fizicko_lice")
	private TFizickoLiceDTO fizicko_lice;
	
	@XmlElement(name="pravno_lice")
	private TPravnoLiceDTO pravno_lice;
	
	@XmlElement(name="pseudonim_znak_autora")
	private String pseudonim_znak_autora;
	
	@XmlElement(name="punomocnik")
	private PunomocnikDTO punomocnik;
	
	@XmlElement(name="naslov_autorskog_dela")
	private NaslovAutorskogDelaDTO naslov_autorskog_dela;
	
	@XmlElement(name="naslov_delo_prerade")
	private NaslovDeloPrerade naslov_delo_prerade;
	
	@XmlElement(name="vrsta_autorskog_dela")
	private String vrsta_autorskog_dela;
	
	@XmlElement(name="forma_zapisa_autorskog_dela")
	private String forma_zapisa_autorskog_dela;
	
	@XmlElement(name="podnosilac_autor")
	private TAutor podnosilac_autor;
	
	@XmlElement(name="autor_anoniman")
	private TAutor autor_anoniman;
	
	@XmlElement(name="autor_ziv")
	private TAutorZivDTO autor_ziv;
	
	@XmlElement(name="autor_preminuo")
	private TAutorPreminuoDTO autor_preminuo;
	
	@XmlElement(name="autorsko_delo_stvoreno_radnim_odnosom")
	private String autorsko_delo_stvoreno_radnim_odnosom;
	
	@XmlElement(name="nacin_koriscenja")
	private String nacin_koriscenja;
	
	@XmlElement(name="prilozi")
	private PriloziDTO prilozi;
	
	@XmlElement(name="broj_prijave")
	private String broj_prijave;
	
	@XmlElement(name="datum_podnosenja")
	private String datum_podnosenja;
	
	public ObrazacA1DTO() {
		
	}
	
	public TFizickoLiceDTO getFizicko_lice() {
		return fizicko_lice;
	}
	public void setFizicko_lice(TFizickoLiceDTO fizicko_lice) {
		this.fizicko_lice = fizicko_lice;
	}
	public TPravnoLiceDTO getPravno_lice() {
		return pravno_lice;
	}
	public void setPravno_lice(TPravnoLiceDTO pravno_lice) {
		this.pravno_lice = pravno_lice;
	}
	public String getPseudonim_znak_autora() {
		return pseudonim_znak_autora;
	}
	public void setPseudonim_znak_autora(String pseudonim_znak_autora) {
		this.pseudonim_znak_autora = pseudonim_znak_autora;
	}
	public PunomocnikDTO getPunomocnik() {
		return punomocnik;
	}
	public void setPunomocnik(PunomocnikDTO punomocnik) {
		this.punomocnik = punomocnik;
	}
	public NaslovAutorskogDelaDTO getNaslov_autorskog_dela() {
		return naslov_autorskog_dela;
	}
	public void setNaslov_autorskog_dela(NaslovAutorskogDelaDTO naslov_autorskog_dela) {
		this.naslov_autorskog_dela = naslov_autorskog_dela;
	}
	public NaslovDeloPrerade getNaslov_delo_prerade() {
		return naslov_delo_prerade;
	}
	public void setNaslov_delo_prerade(NaslovDeloPrerade naslov_delo_prerade) {
		this.naslov_delo_prerade = naslov_delo_prerade;
	}
	public String getVrsta_autorskog_dela() {
		return vrsta_autorskog_dela;
	}
	public void setVrsta_autorskog_dela(String vrsta_autorskog_dela) {
		this.vrsta_autorskog_dela = vrsta_autorskog_dela;
	}
	public String getForma_zapisa_autorskog_dela() {
		return forma_zapisa_autorskog_dela;
	}
	public void setForma_zapisa_autorskog_dela(String forma_zapisa_autorskog_dela) {
		this.forma_zapisa_autorskog_dela = forma_zapisa_autorskog_dela;
	}
	public TAutor getPodnosilac_autor() {
		return podnosilac_autor;
	}
	public void setPodnosilac_autor(TAutor podnosilac_autor) {
		this.podnosilac_autor = podnosilac_autor;
	}
	public TAutor getAutor_anoniman() {
		return autor_anoniman;
	}
	public void setAutor_anoniman(TAutor autor_anoniman) {
		this.autor_anoniman = autor_anoniman;
	}
	public TAutorZivDTO getAutor_ziv() {
		return autor_ziv;
	}
	public void setAutor_ziv(TAutorZivDTO autor_ziv) {
		this.autor_ziv = autor_ziv;
	}
	public TAutorPreminuoDTO getAutor_preminuo() {
		return autor_preminuo;
	}
	public void setAutor_preminuo(TAutorPreminuoDTO autor_preminuo) {
		this.autor_preminuo = autor_preminuo;
	}
	public String getAutorsko_delo_stvoreno_radnim_odnosom() {
		return autorsko_delo_stvoreno_radnim_odnosom;
	}
	public void setAutorsko_delo_stvoreno_radnim_odnosom(String autorsko_delo_stvoreno_radnim_odnosom) {
		this.autorsko_delo_stvoreno_radnim_odnosom = autorsko_delo_stvoreno_radnim_odnosom;
	}
	public String getNacin_koriscenja() {
		return nacin_koriscenja;
	}
	public void setNacin_koriscenja(String nacin_koriscenja) {
		this.nacin_koriscenja = nacin_koriscenja;
	}
	public PriloziDTO getPrilozi() {
		return prilozi;
	}
	public void setPrilozi(PriloziDTO prilozi) {
		this.prilozi = prilozi;
	}
	public String getBroj_prijave() {
		return broj_prijave;
	}
	public void setBroj_prijave(String broj_prijave) {
		this.broj_prijave = broj_prijave;
	}
	public String getDatum_podnosenja() {
		return datum_podnosenja;
	}
	public void setDatum_podnosenja(String datum_podnosenja) {
		this.datum_podnosenja = datum_podnosenja;
	}
	
	
}
