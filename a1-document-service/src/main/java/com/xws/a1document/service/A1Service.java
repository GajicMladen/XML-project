package main.java.com.xws.a1document.service;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.java.com.xws.a1document.dto.ObrazacA1DTO;
import main.java.com.xws.a1document.repository.A1Repository;
import main.java.com.xws.a1document.xml.model.ObrazacA1;
import main.java.com.xws.a1document.xml.model.ObrazacA1.Zahtev;
import main.java.com.xws.a1document.xml.model.ObrazacA1.Zahtev.AutorskoDeloStvorenoRadnimOdnosom;
import main.java.com.xws.a1document.xml.model.ObrazacA1.Zahtev.BrojPrijave;
import main.java.com.xws.a1document.xml.model.ObrazacA1.Zahtev.DatumPodnosenja;
import main.java.com.xws.a1document.xml.model.ObrazacA1.Zahtev.FormaZapisaAutorskogDela;
import main.java.com.xws.a1document.xml.model.ObrazacA1.Zahtev.NacinKoriscenja;
import main.java.com.xws.a1document.xml.model.ObrazacA1.Zahtev.NaslovAutorskogDela;
import main.java.com.xws.a1document.xml.model.ObrazacA1.Zahtev.NaslovAutorskogDela.AlternativniNaslov;
import main.java.com.xws.a1document.xml.model.ObrazacA1.Zahtev.NaslovAutorskogDela.Naslov;
import main.java.com.xws.a1document.xml.model.ObrazacA1.Zahtev.NaslovDeloPrerade;
import main.java.com.xws.a1document.xml.model.ObrazacA1.Zahtev.PodaciOAutoruNepodnosilac;
import main.java.com.xws.a1document.xml.model.ObrazacA1.Zahtev.Podnosilac;
import main.java.com.xws.a1document.xml.model.ObrazacA1.Zahtev.Prilozi;
import main.java.com.xws.a1document.xml.model.ObrazacA1.Zahtev.PseudonimZnakAutora;
import main.java.com.xws.a1document.xml.model.ObrazacA1.Zahtev.Punomocnik;
import main.java.com.xws.a1document.xml.model.ObrazacA1.Zahtev.VrstaAutorskogDela;
import main.java.com.xws.a1document.xml.model.ObrazacA1.Zavod;
import main.java.com.xws.a1document.xml.model.TAutorZiv;
import main.java.com.xws.a1document.xml.model.TFizickoLice;


@Service
public class A1Service {
	
	@Autowired
	private A1Repository a1Repository;
	
	@Autowired
	private RdfService rdfService;
	
	public ObrazacA1 getObrazacA1(ObrazacA1DTO o) {
		ObrazacA1 obrazac = new ObrazacA1();
		Zavod zavod = new Zavod();
		zavod.setNaziv(o.getZavod().getNaziv());
		zavod.setAdresa(o.getZavod().getAdresa());
		obrazac.setZavod(zavod);
		Zahtev zahtev = new Zahtev();
		zahtev.setPodnosilac(getPodnosilac(o));
		if (zahtev.getPodnosilac().getLice() instanceof TFizickoLice) {
			TFizickoLice lice = (TFizickoLice) zahtev.getPodnosilac().getLice();
			System.out.println(lice.getIme());
		}
		zahtev.setPseudonimZnakAutora(getPseudonim(o));
		zahtev.setAutorskoDeloStvorenoRadnimOdnosom(getRadniOdnos(o));
		zahtev.setDatumPodnosenja(getDatumPodnosenja(o));
		zahtev.setFormaZapisaAutorskogDela(getFormaZapisa(o));
		zahtev.setBrojPrijave(getBrojPrijave());
		zahtev.setNacinKoriscenja(getNacinKorscenja(o));
		zahtev.setNaslov("Zahtev za unosenje u evidenciju i deponovanje autorskih dela");
		zahtev.setNaslovAutorskogDela(getNaslovDela(o));
		zahtev.setNaslovDeloPrerade(getDeloPrerade(o));
		zahtev.setPodaciOAutoruNepodnosilac(getAutorPodaci(o));
		zahtev.setPrilozi(getPrilozi(o));
		zahtev.setPunomocnik(getPunomocnik(o));
		zahtev.setVrstaAutorskogDela(getVrsta(o));
		obrazac.setZahtev(zahtev);
		return obrazac;
	}
	
	private VrstaAutorskogDela getVrsta(ObrazacA1DTO o) {
		VrstaAutorskogDela vrsta = new VrstaAutorskogDela();
		vrsta.setValue(o.getZahtev().getVrstaAutorskogDela().getValue());
		System.out.println(vrsta.getValue());
		return vrsta;
	}

	private Punomocnik getPunomocnik(ObrazacA1DTO o) {
		Punomocnik punomocnik = new Punomocnik();
		punomocnik.setIme(o.getZahtev().getPunomocnik().getIme());
		punomocnik.setPrezime(o.getZahtev().getPunomocnik().getPrezime());
		punomocnik.setAdresa(o.getZahtev().getPunomocnik().getAdresa());
		return punomocnik;
	}

	private Prilozi getPrilozi(ObrazacA1DTO o) {
		Prilozi prilozi = new Prilozi();
		prilozi.setOpisAutorskogDela(o.getZahtev().getPrilozi().getOpisAutorskogDela());
		prilozi.setPrimerAutorskogDela(o.getZahtev().getPrilozi().getPrimerAutorskogDela());
		System.out.println(prilozi.getOpisAutorskogDela());
		System.out.println(prilozi.getPrimerAutorskogDela());
		return prilozi;
	}

	private PodaciOAutoruNepodnosilac getAutorPodaci(ObrazacA1DTO o) {
		PodaciOAutoruNepodnosilac podaci = new PodaciOAutoruNepodnosilac();
		if (!o.getZahtev().getPodnosilacAutor().getIme().equals("")) {
			podaci.setAutor(o.getZahtev().getPodnosilacAutor());
		} else if (!o.getZahtev().getAutorAnoniman().getIme().equals("")) {
			podaci.setAutor(o.getZahtev().getAutorAnoniman());
		} else if (!o.getZahtev().getAutorZiv().getIme().equals("")) {
			podaci.setAutor(o.getZahtev().getAutorZiv());
		} else {
			podaci.setAutor(o.getZahtev().getAutorPreminuo());
		}
		if (podaci.getAutor() instanceof TAutorZiv) {
			TAutorZiv ziv = (TAutorZiv) podaci.getAutor();
			System.out.println(ziv.getIme());
			System.out.println(ziv.getAdresa().getUlica());
		}
		return podaci;
	}

	private NaslovDeloPrerade getDeloPrerade(ObrazacA1DTO o) {
		NaslovDeloPrerade prerade = new NaslovDeloPrerade();
		prerade.setAutor(o.getZahtev().getNaslovDeloPrerade().getAutor());
		prerade.setNaslov(o.getZahtev().getNaslovDeloPrerade().getNaslov());
		System.out.println(prerade.getAutor().getIme());
		System.out.println(prerade.getNaslov());
		return prerade;
	}

	private NaslovAutorskogDela getNaslovDela(ObrazacA1DTO o) {
		NaslovAutorskogDela naslovDela = new NaslovAutorskogDela();
		Naslov naslov = new Naslov();
		naslov.setValue(o.getZahtev().getNaslovAutorskogDela().getNaslov().getValue());
		naslovDela.setNaslov(naslov);
		System.out.println(naslovDela.getNaslov().getValue());
		AlternativniNaslov altNaslov = new AlternativniNaslov();
		altNaslov.setValue(o.getZahtev().getNaslovAutorskogDela().getAlternativniNaslov().getValue());
		naslovDela.setAlternativniNaslov(altNaslov);
		System.out.println(naslovDela.getAlternativniNaslov().getValue());
		return naslovDela;
	}

	private NacinKoriscenja getNacinKorscenja(ObrazacA1DTO o) {
		NacinKoriscenja koriscenje = new NacinKoriscenja();
		koriscenje.setValue(o.getZahtev().getNacinKoriscenja().getValue());
		System.out.println(koriscenje.getValue());
		return koriscenje;
	}

	private BrojPrijave getBrojPrijave() {
		BrojPrijave br = new BrojPrijave();
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		String formattedDate = now.format(formatter);
		StringBuilder sb = new StringBuilder();
        sb.append("A-");
        sb.append(formattedDate);
        br.setValue(sb.toString());
        System.out.println(br.getValue());
		return br;
	}
	
	private FormaZapisaAutorskogDela getFormaZapisa(ObrazacA1DTO o) {
		FormaZapisaAutorskogDela forma = new FormaZapisaAutorskogDela();
		forma.setValue(o.getZahtev().getFormaZapisaAutorskogDela().getValue());
		System.out.println(forma.getValue());
		return forma;
	}

	private DatumPodnosenja getDatumPodnosenja(ObrazacA1DTO o) {
		DatumPodnosenja datum = new DatumPodnosenja();
		datum.setValue(o.getZahtev().getDatumPodnosenja().getValue());
		System.out.println(datum.getValue());
		return datum;
	}

	private AutorskoDeloStvorenoRadnimOdnosom getRadniOdnos(ObrazacA1DTO o) {
		AutorskoDeloStvorenoRadnimOdnosom radni = new AutorskoDeloStvorenoRadnimOdnosom();
		radni.setValue(o.getZahtev().getAutorskoDeloStvorenoRadnimOdnosom().getValue());
		System.out.println(radni.getValue());
		return radni;
	}

	private Podnosilac getPodnosilac(ObrazacA1DTO o) {
		Podnosilac podnosilac = new Podnosilac();
		if (o.getZahtev().getFizickoLice().getIme().equals("")) {
			podnosilac.setLice(o.getZahtev().getPravnoLice());
		} else {
			podnosilac.setLice(o.getZahtev().getFizickoLice());
		}
		return podnosilac;
	}
	
	private PseudonimZnakAutora getPseudonim(ObrazacA1DTO o) {
		PseudonimZnakAutora pseudonim = new PseudonimZnakAutora();
		pseudonim.setValue(o.getZahtev().getPseudonimZnakAutora().getValue());
		System.out.println(pseudonim.getValue());
		return pseudonim;
	}

	public void save(ObrazacA1 obrazac) throws Exception {
		OutputStream os = new ByteArrayOutputStream();
        os = marshalling(obrazac, os);
        a1Repository.save(obrazac.getZahtev().getBrojPrijave().getValue(), os);
        rdfService.extractMetadata(obrazac);
	}
	
	public OutputStream marshalling(ObrazacA1 obrazac, OutputStream outputStream) {
        try{

            JAXBContext context = JAXBContext.newInstance("main.java.com.xws.a1document.xml.model");
            Marshaller marshaller = context.createMarshaller();            
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);            
            marshaller.marshal(obrazac, outputStream);
            return outputStream;
        } catch (JAXBException e) {
            throw new RuntimeException("JAXB marshalling exception");
        }
    }

	public ObrazacA1 getByBrojPrijave(String id) throws Exception {
		return a1Repository.getByBrojPrijave(id);
	}

	public List<ObrazacA1> getAllZahtevi() throws Exception {
		return a1Repository.getAllZahtevi();		
	}
	
	
}
