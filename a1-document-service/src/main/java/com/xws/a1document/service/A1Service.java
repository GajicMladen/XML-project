package main.java.com.xws.a1document.service;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.java.com.xws.a1document.dto.ObrazacA1DTO;
import main.java.com.xws.a1document.dto.SedisteDTO;
import main.java.com.xws.a1document.dto.TAdresaDTO;
import main.java.com.xws.a1document.dto.TAutorPreminuoDTO;
import main.java.com.xws.a1document.dto.TAutorZivDTO;
import main.java.com.xws.a1document.dto.TFizickoLiceDTO;
import main.java.com.xws.a1document.dto.TPravnoLiceDTO;
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
import main.java.com.xws.a1document.xml.model.TAdresa;
import main.java.com.xws.a1document.xml.model.TAutorPreminuo;
import main.java.com.xws.a1document.xml.model.TAutorZiv;
import main.java.com.xws.a1document.xml.model.TFizickoLice;
import main.java.com.xws.a1document.xml.model.TPravnoLice;
import main.java.com.xws.a1document.xml.model.TPravnoLice.Sediste;


@Service
public class A1Service {
	
	@Autowired
	private A1Repository a1Repository;
	
	@Autowired
	private RdfService rdfService;
	
	public ObrazacA1 getObrazacA1(ObrazacA1DTO o) {
		ObrazacA1 obrazac = new ObrazacA1();
		
		Zavod zavod = new Zavod();
		zavod.setNaziv("Zavod za intelektualnu svojinu");
		TAdresa zavodAdresa = new TAdresa();
		zavodAdresa.setUlica("Kneginje Ljubice");
		zavodAdresa.setBroj(BigInteger.valueOf(5));
		zavodAdresa.setGrad("Beograd");
		zavodAdresa.setPostanskiKod(21000);
		zavod.setAdresa(zavodAdresa);
		obrazac.setZavod(zavod);
		
		Zahtev zahtev = new Zahtev();
		zahtev.setPodnosilac(getPodnosilac(o));
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
		zahtev.setStatus("PENDING");
		obrazac.setZahtev(zahtev);
		return obrazac;
		
	}
	
	private TAdresa getAdresa(TAdresaDTO adresa) {
		TAdresa a = new TAdresa();
		a.setUlica(adresa.getUlica());
		a.setBroj(BigInteger.valueOf(adresa.getBroj()));
		a.setGrad(adresa.getGrad());
		a.setPostanskiKod(adresa.getPostanski_kod());
		return a;
	}
	
	private TAutorZiv getAutorZiv(TAutorZivDTO autor) {
		TAutorZiv a = new TAutorZiv();
		a.setAdresa(getAdresa(autor.getAdresa()));
		a.setDrzavljanstvo(autor.getDrzavljanstvo());
		a.setIme(autor.getIme());
		a.setPrezime(autor.getPrezime());
		return a;
	}
	
	public TAutorPreminuo getAutorPreminuo(TAutorPreminuoDTO autor) {
		TAutorPreminuo a = new TAutorPreminuo();
		a.setIme(autor.getIme());
		a.setPrezime(autor.getPrezime());
		a.setGodinaSmrti(autor.getGodina_smrti());
		return a;
	}
	
	private VrstaAutorskogDela getVrsta(ObrazacA1DTO o) {
		VrstaAutorskogDela vrsta = new VrstaAutorskogDela();
		vrsta.setValue(o.getVrsta_autorskog_dela());
		System.out.println(vrsta.getValue());
		return vrsta;
	}

	private Punomocnik getPunomocnik(ObrazacA1DTO o) {
		Punomocnik punomocnik = new Punomocnik();
		punomocnik.setIme(o.getPunomocnik().getIme());
		punomocnik.setPrezime(o.getPunomocnik().getPrezime());
		punomocnik.setAdresa(getAdresa(o.getPunomocnik().getAdresa()));
		return punomocnik;
	}

	private Prilozi getPrilozi(ObrazacA1DTO o) {
		Prilozi prilozi = new Prilozi();
		prilozi.setOpisAutorskogDela(o.getPrilozi().getOpis_autorskog_dela());
		prilozi.setPrimerAutorskogDela(o.getPrilozi().getPrimer_autorskog_dela());
		System.out.println(prilozi.getOpisAutorskogDela());
		System.out.println(prilozi.getPrimerAutorskogDela());
		return prilozi;
	}

	private PodaciOAutoruNepodnosilac getAutorPodaci(ObrazacA1DTO o) {
		PodaciOAutoruNepodnosilac podaci = new PodaciOAutoruNepodnosilac();
		if (!o.getPodnosilac_autor().getIme().equals("")) {
			podaci.setAutor(o.getPodnosilac_autor());
		} else if (!o.getAutor_anoniman().getIme().equals("")) {
			podaci.setAutor(o.getAutor_anoniman());
		} else if (!o.getAutor_ziv().getIme().equals("")) {
			podaci.setAutor(getAutorZiv(o.getAutor_ziv()));
		} else {
			podaci.setAutor(getAutorPreminuo(o.getAutor_preminuo()));
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
		prerade.setAutor(o.getNaslov_delo_prerade().getAutor());
		prerade.setNaslov(o.getNaslov_delo_prerade().getNaslov());
		System.out.println(prerade.getAutor().getIme());
		System.out.println(prerade.getNaslov());
		return prerade;
	}

	private NaslovAutorskogDela getNaslovDela(ObrazacA1DTO o) {
		NaslovAutorskogDela naslovDela = new NaslovAutorskogDela();
		Naslov naslov = new Naslov();
		naslov.setValue(o.getNaslov_autorskog_dela().getNaslov());
		naslovDela.setNaslov(naslov);
		System.out.println(naslovDela.getNaslov().getValue());
		AlternativniNaslov altNaslov = new AlternativniNaslov();
		altNaslov.setValue(o.getNaslov_autorskog_dela().getAlternativni_naslov());
		naslovDela.setAlternativniNaslov(altNaslov);
		System.out.println(naslovDela.getAlternativniNaslov().getValue());
		return naslovDela;
	}

	private NacinKoriscenja getNacinKorscenja(ObrazacA1DTO o) {
		NacinKoriscenja koriscenje = new NacinKoriscenja();
		koriscenje.setValue(o.getNacin_koriscenja());
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
		forma.setValue(o.getForma_zapisa_autorskog_dela());
		System.out.println(forma.getValue());
		return forma;
	}

	private DatumPodnosenja getDatumPodnosenja(ObrazacA1DTO o) {
		DatumPodnosenja datum = new DatumPodnosenja();
		datum.setValue(o.getDatum_podnosenja());
		System.out.println(datum.getValue());
		return datum;
	}

	private AutorskoDeloStvorenoRadnimOdnosom getRadniOdnos(ObrazacA1DTO o) {
		AutorskoDeloStvorenoRadnimOdnosom radni = new AutorskoDeloStvorenoRadnimOdnosom();
		radni.setValue(o.getAutorsko_delo_stvoreno_radnim_odnosom());
		System.out.println(radni.getValue());
		return radni;
	}

	private Podnosilac getPodnosilac(ObrazacA1DTO o) {
		Podnosilac podnosilac = new Podnosilac();
		if (o.getFizicko_lice().getIme().equals("")) {
			podnosilac.setLice(getPravnoLice(o.getPravno_lice()));
		} else {
			podnosilac.setLice(getFizickoLice(o.getFizicko_lice()));			
		}
		
		if (podnosilac.getLice() instanceof TFizickoLice) {
			TFizickoLice lice = (TFizickoLice) podnosilac.getLice();
			System.out.println(lice.getIme());
		}
		return podnosilac;
	}
	
	private Sediste getSediste(SedisteDTO sediste) {
		Sediste s = new Sediste();
		s.setAdresa(getAdresa(sediste.getAdresa()));
		return s;
	}
	
	private TPravnoLice getPravnoLice(TPravnoLiceDTO lice) {
		TPravnoLice l = new TPravnoLice();
		l.setEmail(lice.getEmail());
		l.setTelefon(lice.getTelefon());
		l.setPoslovnoIme(lice.getPoslovno_ime());
		l.setSediste(getSediste(lice.getSediste()));
		return l;
	}
	
	private TFizickoLice getFizickoLice(TFizickoLiceDTO lice) {
		TFizickoLice l = new TFizickoLice();
		l.setIme(lice.getIme());
		l.setPrezime(lice.getPrezime());
		l.setTelefon(lice.getTelefon());
		l.setEmail(lice.getEmail());
		l.setDrzavljanstvo(lice.getDrzavljanstvo());
		l.setAdresa(getAdresa(lice.getAdresa()));
		return l;
	}
	
	private PseudonimZnakAutora getPseudonim(ObrazacA1DTO o) {
		PseudonimZnakAutora pseudonim = new PseudonimZnakAutora();
		pseudonim.setValue(o.getPseudonim_znak_autora());
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

	public List<ObrazacA1> getAllPending() throws Exception {
		return a1Repository.getAllZahtevi("PENDING");		
	}
	
	public void approveZahtev(String documentId) throws Exception {
		a1Repository.changeZahtevStatus(documentId, "APPROVED");
	}
	
	public void denyZahtev(String documentId) throws Exception {
		a1Repository.changeZahtevStatus(documentId, "DENIED");
	}
	
	
}
