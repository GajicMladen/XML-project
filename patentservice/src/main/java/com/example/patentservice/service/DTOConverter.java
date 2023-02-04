package com.example.patentservice.service;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

import org.springframework.stereotype.Service;

import com.example.patentservice.beans.TAdresa;
import com.example.patentservice.beans.TAdresaDrzava;
import com.example.patentservice.beans.TDetaljiRanijePrijave;
import com.example.patentservice.beans.TFizickoLice;
import com.example.patentservice.beans.TKontakt;
import com.example.patentservice.beans.TKontaktFaks;
import com.example.patentservice.beans.TNaziv;
import com.example.patentservice.beans.ZahtevZaPriznanjePatenta;
import com.example.patentservice.beans.ZahtevZaPriznanjePatenta.Dopuna;
import com.example.patentservice.beans.ZahtevZaPriznanjePatenta.NacinDostavljanja;
import com.example.patentservice.beans.ZahtevZaPriznanjePatenta.NazivPronalaska;
import com.example.patentservice.beans.ZahtevZaPriznanjePatenta.PodaciOPronalazacu;
import com.example.patentservice.beans.ZahtevZaPriznanjePatenta.PodaciOPronalazacu.Pronalazac;
import com.example.patentservice.beans.ZahtevZaPriznanjePatenta.PodnosilacPrijave;
import com.example.patentservice.beans.ZahtevZaPriznanjePatenta.PriznanjePravaPrvenstvaIzRanijihPrijava;
import com.example.patentservice.beans.ZahtevZaPriznanjePatenta.Punomocnik;
import com.example.patentservice.beans.ZahtevZaPriznanjePatenta.Punomocnik.Funkcija;
import com.example.patentservice.dto.Adresa;
import com.example.patentservice.dto.Kontakt;
import com.example.patentservice.dto.PravaPrvenstva;
import com.example.patentservice.dto.Zahtev;

@Service
public class DTOConverter {
	public void setNazivPronalaskaDTO(ZahtevZaPriznanjePatenta zp, Zahtev dto) {
		zp.setNazivPronalaska(new NazivPronalaska());
		TNaziv nazivSrpski = new TNaziv();
		nazivSrpski.setLang("sr");
		nazivSrpski.setValue(dto.getNazivPronalaska().getNazivSrpski());
		
		TNaziv nazivEngleski = new TNaziv();
		nazivEngleski.setLang("en");
		nazivEngleski.setValue(dto.getNazivPronalaska().getNazivEngleski());
		
		zp.getNazivPronalaska().getNaziv().add(nazivSrpski);
		zp.getNazivPronalaska().getNaziv().add(nazivEngleski);
	}
	
	public void setDetaljiRanijihPrijavaDTO(ZahtevZaPriznanjePatenta zp, Zahtev dto) {
		PriznanjePravaPrvenstvaIzRanijihPrijava p = new PriznanjePravaPrvenstvaIzRanijihPrijava();
		for (PravaPrvenstva pp: dto.getPriznavanjePravaPrvenstva()) {
			TDetaljiRanijePrijave dr = new TDetaljiRanijePrijave();
			dr.setBrojPrijave(pp.getBrojPrijave());
			dr.setDvoslovnaOznaka(pp.getDvoslovnaOznaka());
			GregorianCalendar gc = convertDTOTime(pp.getDatumPodnosenja());
			try {
				dr.setDatumPodnosenja(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
			} catch (DatatypeConfigurationException e) {
				e.printStackTrace();
			}
			p.getDetaljiRanijePrijave().add(dr);
		}
		zp.setPriznanjePravaPrvenstvaIzRanijihPrijava(p);
	}
	
	public void setDopunaDTO(ZahtevZaPriznanjePatenta zp, Zahtev dto) {
		Dopuna dopuna = new Dopuna();
		dopuna.setDopunskaPrijava(dto.getDopuna().isDopunskaPrijava());
		dopuna.setIzdvojenaPrijava(dto.getDopuna().isIzdvojenaPrijava());
		if (dopuna.isDopunskaPrijava() || dopuna.isIzdvojenaPrijava()) {
			dopuna.setBrojOsnovnePrijave(dto.getDopuna().getBrojOsnovnePrijave());
			
			GregorianCalendar gc = convertDTOTime(dto.getDopuna().getDatumOsnovnePrijave());
			try {
				dopuna.setDatumOsnovnePrijave(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
			} catch (DatatypeConfigurationException e) {
				e.printStackTrace();
			}
		}
		zp.setDopuna(dopuna);
	}
	
	public GregorianCalendar convertDTOTime(String dtoTime) {
		String dateString = "2023-01-31T21:00:00.000Z";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		Date date = new Date();
		try {
			date = format.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		return calendar;
	}
	
	public void setNacinDostavljanjaDTO(ZahtevZaPriznanjePatenta zp, Zahtev dto) {
		NacinDostavljanja nd = new NacinDostavljanja();
		nd.setElektronski(dto.getNacinDostavljanja().isElektronski());
		nd.setPapirno(dto.getNacinDostavljanja().isPapirno());
		zp.setNacinDostavljanja(nd);
	}
	
	public void setAdresaDostavaDTO(ZahtevZaPriznanjePatenta zp, Zahtev dto) {
		if(!dto.getAdresaZaDostavljanje().getUlica().equals("")) {
			TAdresa adr = new TAdresa();
			setAdresa(adr, dto.getAdresaZaDostavljanje());
			zp.setAdresaZaDostavljanje(adr);
		}
	}
	
	public void setPunomocnikDTO(ZahtevZaPriznanjePatenta zp, Zahtev dto) {
		Punomocnik punomocnik = new Punomocnik();
		Funkcija f = new Funkcija();
		f.setPunomocnikPrijemPismena(dto.getPunomocnik().getFunkcija().isPunomocnikPrijemPismena());
		f.setPunomocnikZastupanje(dto.getPunomocnik().getFunkcija().isPunomocnikZastupanje());
		f.setZajednickiPredstavnik(dto.getPunomocnik().getFunkcija().isZajednickiPredstavnik());
		
		punomocnik.setFunkcija(f);
		
		boolean punomocnikNaveden = f.isPunomocnikPrijemPismena() || f.isPunomocnikZastupanje() || f.isZajednickiPredstavnik();
		if(punomocnikNaveden) {
			if (dto.getPunomocnik().getTipPodnosioca().equals("fizicko-lice")) {
				TFizickoLice fl = new TFizickoLice();
				fl.setIme(dto.getPunomocnik().getFizickoLice().getIme());
				fl.setPrezime(dto.getPunomocnik().getFizickoLice().getPrezime());
				punomocnik.setFizickoLice(fl);
			} else {
				punomocnik.setPoslovnoIme(dto.getPunomocnik().getPoslovnoIme());
			}
			
			TAdresa adr = new TAdresa();
			setAdresa(adr, dto.getPunomocnik().getLokacija());
			punomocnik.setAdresa(adr);
			
			TKontakt kt = new TKontakt();
			setKontakt(kt, dto.getPunomocnik().getKontakt());
			punomocnik.setKontakt(kt);
		}
		zp.setPunomocnik(punomocnik);
	}
	
	public void setPronalazacDTO(ZahtevZaPriznanjePatenta zp, Zahtev dto) {
		PodaciOPronalazacu pp = new PodaciOPronalazacu();
		pp.setPronalazacNaveden(dto.getPodaciOPronalazacu().isPronalazacNaveden());
		
		if(!dto.getPodnosilacPrijave().isPodnosilacPronalazac() && pp.isPronalazacNaveden()) {
			Pronalazac p = new Pronalazac();
			
			TFizickoLice fl = new TFizickoLice();
			fl.setIme(dto.getPodaciOPronalazacu().getPronalazac().getFizickoLice().getIme());
			fl.setPrezime(dto.getPodaciOPronalazacu().getPronalazac().getFizickoLice().getPrezime());
			p.setFizickoLice(fl);
			
			TAdresaDrzava adr = new TAdresaDrzava();
			setLokacija(adr, dto.getPodaciOPronalazacu().getPronalazac().getLokacija());
			p.setLokacija(adr);
			
			TKontaktFaks kf = new TKontaktFaks();
			setKontaktFaks(kf, dto.getPodaciOPronalazacu().getPronalazac().getKontakt());
			p.setKontakt(kf);
			
			pp.setPronalazac(p);
			
			zp.setPodaciOPronalazacu(pp);
		}
	}
	
	public void setPodnosilacDTO(ZahtevZaPriznanjePatenta zp, Zahtev dto) {
		PodnosilacPrijave podnosilac = new PodnosilacPrijave();
		
		if (dto.getPodnosilacPrijave().getTipPodnosioca().equals("fizicko-lice")) {
			TFizickoLice fl = new TFizickoLice();
			fl.setIme(dto.getPodnosilacPrijave().getFizickoLice().getIme());
			fl.setPrezime(dto.getPodnosilacPrijave().getFizickoLice().getPrezime());
			podnosilac.setFizickoLice(fl);
			podnosilac.setDrzavljanstvno(dto.getPodnosilacPrijave().getFizickoLice().getDrzavljanstvo());
		} else {
			podnosilac.setPoslovnoIme(dto.getPodnosilacPrijave().getPoslovnoIme());
		}
		
		TAdresaDrzava adr = new TAdresaDrzava();
		setLokacija(adr, dto.getPodnosilacPrijave().getLokacija());
		podnosilac.setLokacija(adr);
		
		TKontaktFaks kf = new TKontaktFaks();
		setKontaktFaks(kf, dto.getPodnosilacPrijave().getKontakt());
		podnosilac.setKontakt(kf);
		
		podnosilac.setPodnosilacPronalazac(dto.getPodnosilacPrijave().isPodnosilacPronalazac());
		zp.setPodnosilacPrijave(podnosilac);
	
	}
	
	public void setAdresa(TAdresa adresa, Adresa dto) {
		adresa.setBroj(new BigInteger(dto.getBroj()));
		adresa.setMesto(dto.getMesto());
		adresa.setPostanskiBroj(dto.getPostanskiBroj());
		adresa.setUlica(dto.getUlica());
	}
	
	public void setLokacija(TAdresaDrzava adresa, Adresa dto) {
		adresa.setBroj(new BigInteger(dto.getBroj()));
		adresa.setMesto(dto.getMesto());
		adresa.setPostanskiBroj(dto.getPostanskiBroj());
		adresa.setUlica(dto.getUlica());
		adresa.setDrzava(dto.getDrzava());
	}
	
	public void setKontakt(TKontakt kontakt, Kontakt dto) {
		kontakt.setBrojTelefona(dto.getBrojTelefona());
		kontakt.setEMail(dto.getEmail());
	}
	
	public void setKontaktFaks(TKontaktFaks kontakt, Kontakt dto) {
		kontakt.setBrojFaksa(dto.getBrojFaksa());
		kontakt.setBrojTelefona(dto.getBrojTelefona());
		kontakt.setEMail(dto.getEmail());
	}
}
