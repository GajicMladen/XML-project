package com.example.patentservice.utils;

import java.math.BigInteger;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import com.example.patentservice.beans.TAdresa;
import com.example.patentservice.beans.TDetaljiRanijePrijave;
import com.example.patentservice.beans.TFizickoLice;
import com.example.patentservice.beans.TKontakt;
import com.example.patentservice.beans.ZahtevZaPriznanjePatenta;

public class ZahtevModifier {
	
	public static void modify(ZahtevZaPriznanjePatenta zahtev) throws DatatypeConfigurationException {
		zahtev.getPodnosilacPrijave().setFizickoLice(null);
		zahtev.getPodnosilacPrijave().setPoslovnoIme("FIRMA DOO");
		zahtev.getPodnosilacPrijave().setDrzavljanstvno(null);
		
		zahtev.getPunomocnik().getFunkcija().setZajednickiPredstavnik(true);
		
		zahtev.getPunomocnik().setAdresa(new TAdresa());
		zahtev.getPunomocnik().getAdresa().setUlica("Sime Šolaje");
		zahtev.getPunomocnik().getAdresa().setBroj(new BigInteger("22"));
		zahtev.getPunomocnik().getAdresa().setPostanskiBroj("11000");
		zahtev.getPunomocnik().getAdresa().setMesto("Beograd");
		
		zahtev.getPunomocnik().setFizickoLice(new TFizickoLice());
		zahtev.getPunomocnik().getFizickoLice().setIme("Nikola");
		zahtev.getPunomocnik().getFizickoLice().setPrezime("MARKOVIĆ");
		
		zahtev.getPunomocnik().setKontakt(new TKontakt());
		zahtev.getPunomocnik().getKontakt().setBrojTelefona("060111111");
		zahtev.getPunomocnik().getKontakt().setEMail("nikola@mail.com");
		
		zahtev.getDopuna().setDopunskaPrijava(true);
		zahtev.getDopuna().setBrojOsnovnePrijave("P4258");
		
		XMLGregorianCalendar date = DatatypeFactory.newInstance()
			    .newXMLGregorianCalendar("2022-12-22");
		zahtev.getDopuna().setDatumOsnovnePrijave(date);
		
		TDetaljiRanijePrijave drp1 = new TDetaljiRanijePrijave();
		drp1.setBrojPrijave("P0001");
		drp1.setDatumPodnosenja(date);
		drp1.setDvoslovnaOznaka("NL");
		
		TDetaljiRanijePrijave drp2 = new TDetaljiRanijePrijave();
		drp2.setBrojPrijave("P0002");
		drp2.setDatumPodnosenja(date);
		drp2.setDvoslovnaOznaka("IS");
		
		zahtev.getPriznanjePravaPrvenstvaIzRanijihPrijava().getDetaljiRanijePrijave().add(drp1);
		zahtev.getPriznanjePravaPrvenstvaIzRanijihPrijava().getDetaljiRanijePrijave().add(drp2);
	}
	

	public static void modify2(ZahtevZaPriznanjePatenta zahtev) {
		zahtev.getPodnosilacPrijave().setPoslovnoIme(null);
		TFizickoLice fl = new TFizickoLice();
		fl.setIme("Marko");
		fl.setPrezime("Markovic");
		zahtev.getPodnosilacPrijave().setFizickoLice(fl);
		zahtev.getPodnosilacPrijave().setDrzavljanstvno("Vojvodjanin");
		
		zahtev.getPunomocnik().getFunkcija().setZajednickiPredstavnik(true);
		zahtev.getPunomocnik().setPoslovnoIme("SMAJSER DOO");
		
		TAdresa ad = new TAdresa();
		ad.setBroj(BigInteger.valueOf(22L));
		ad.setMesto("Beograd");
		ad.setPostanskiBroj("11000");
		ad.setUlica("Cara Dušana");
		
		TKontakt kt = new TKontakt();
		kt.setBrojTelefona("0607222222");
		kt.setEMail("test@yahoo.com");
		
		zahtev.getPunomocnik().setKontakt(kt);
		zahtev.getPunomocnik().setAdresa(ad);
	}
}
