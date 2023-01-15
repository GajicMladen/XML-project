package main.java.com.xws.a1document.service;

import org.springframework.stereotype.Service;

import main.java.com.xws.a1document.xml.model.ObrazacA1;
import main.java.com.xws.a1document.xml.model.TAdresa;
import main.java.com.xws.a1document.xml.model.TAutor;
import main.java.com.xws.a1document.xml.model.TAutorPreminuo;
import main.java.com.xws.a1document.xml.model.TAutorZiv;
import main.java.com.xws.a1document.xml.model.TFizickoLice;
import main.java.com.xws.a1document.xml.model.TPravnoLice;

@Service
public class A1Service {
	
	public void printObrazac(ObrazacA1 obrazac) {
		printZavod(obrazac.getZavod());
		
		printZahtev(obrazac.getZahtev());
	}
	
	private void printZavod(ObrazacA1.Zavod zavod) {
		System.out.println("Zavod - " + zavod.getNaziv() + "\n");
		printAdresa(zavod.getAdresa());
	}
	
	private void printAdresa(TAdresa adresa) {
		System.out.println("\tUlica: " + adresa.getUlica());
		System.out.println("\tBroj: " + adresa.getBroj());
		System.out.println("\tGrad: " + adresa.getGrad());
		System.out.println("\tPostanski kod: " + adresa.getPostanskiKod());
	}
	
	private void printZahtev(ObrazacA1.Zahtev zahtev) {
		System.out.println("Zahtev - " + zahtev.getNaslov());
		printPodnosilac(zahtev.getPodnosilac());
		printPseudonim(zahtev.getPseudonimZnakAutora());
		printPunomocnik(zahtev.getPunomocnik());
		printNaslov(zahtev.getNaslovAutorskogDela());
		printDeloPrerade(zahtev.getNaslovDeloPrerade());
		printVrstaDela(zahtev.getVrstaAutorskogDela());
		printFormaZapisa(zahtev.getFormaZapisaAutorskogDela());
		printAutorNepodnosilac(zahtev.getPodaciOAutoruNepodnosilac());
		printRadniOdnos(zahtev.getAutorskoDeloStvorenoRadnimOdnosom());
		printNacinKoriscenja(zahtev.getNacinKoriscenja());
		printBrojPrijave(zahtev.getBrojPrijave());
		printDatumPodnosenja(zahtev.getDatumPodnosenja());
	}
	
	private void printPodnosilac(ObrazacA1.Zahtev.Podnosilac podnosilac) {
		System.out.println("Podnosilac");
		if (podnosilac.getLice() instanceof TFizickoLice) {
			System.out.println("\tFizicko lice");
			TFizickoLice fizickoLice = (TFizickoLice) podnosilac.getLice();
			System.out.println("\tIme: " + fizickoLice.getIme());
			System.out.println("\tPrezime: " + fizickoLice.getPrezime());
			printAdresa(fizickoLice.getAdresa());
			System.out.println("\tDrzavljanstvo: " + fizickoLice.getDrzavljanstvo());
			System.out.println("\tTelefon: " + fizickoLice.getTelefon());
			System.out.println("\tE-mail: " + fizickoLice.getEmail());
			
		} else if (podnosilac.getLice() instanceof TPravnoLice) {
			System.out.println("\tPravno lice");
			TPravnoLice pravnoLice = (TPravnoLice) podnosilac.getLice(); 
			System.out.println("\tPoslovno ime: " + pravnoLice.getPoslovnoIme());
			printAdresa(pravnoLice.getSediste().getAdresa());
			System.out.println("\tTelefon: " + pravnoLice.getTelefon());
			System.out.println("\tE-mail: " + pravnoLice.getEmail());
		}
		
	}
	
	private void printPseudonim(ObrazacA1.Zahtev.PseudonimZnakAutora pseudonim) {
		System.out.println("Pseudonim - " + pseudonim.getValue());
	}
	
	private void printPunomocnik(ObrazacA1.Zahtev.Punomocnik punomocnik) {
		if (punomocnik.getIme().equals("")) {
			System.out.println("Punomocnik - Podnosilac je autor, nema punomocnika.");
		} else {
			System.out.println("Punomocnik");
			System.out.println("\tIme: " + punomocnik.getIme());
			System.out.println("\tPrezime: " + punomocnik.getPrezime());
			printAdresa(punomocnik.getAdresa());
		}
	}
	
	private void printNaslov(ObrazacA1.Zahtev.NaslovAutorskogDela naslov) {
		System.out.println("Naslov");
		System.out.println("\tNaslov: " + naslov.getNaslov().getValue());
		System.out.println("\tAlternativni naslov: " + naslov.getAlternativniNaslov().getValue());
	}
	
	private void printDeloPrerade(ObrazacA1.Zahtev.NaslovDeloPrerade deloPrerade) {
		System.out.println("Delo prerade");
		if (deloPrerade.getNaslov().equals("")) {
			System.out.println("\tNije korisceno delo prerade");
		} else {
			System.out.println("\tNaslov: " + deloPrerade.getNaslov());
			System.out.println("\tIme autora: " + deloPrerade.getAutor().getIme());
			System.out.println("\tIme autora: " + deloPrerade.getAutor().getPrezime());
		}
	}
	
	private void printVrstaDela(ObrazacA1.Zahtev.VrstaAutorskogDela vrsta) {
		System.out.println("Vrsta autorskog dela - " + vrsta.getValue());
	}
	
	private void printFormaZapisa(ObrazacA1.Zahtev.FormaZapisaAutorskogDela forma) {
		System.out.println("Forma zapisa autorskog dela - " + forma.getValue());
	}
	
	private void printAutorNepodnosilac(ObrazacA1.Zahtev.PodaciOAutoruNepodnosilac nepodnosilac) {
		System.out.println("Podaci o autoru koji nije podnosilac zahteva");
		if (nepodnosilac.getAutor() instanceof TAutor) {
			System.out.println("\tAutor je anonimam");
		} else if (nepodnosilac.getAutor() instanceof TAutorZiv) {			
			TAutorZiv autor = (TAutorZiv) nepodnosilac.getAutor();
			System.out.println("\tIme: " + autor.getIme());
			System.out.println("\tPrezime " + autor.getPrezime());
			printAdresa(autor.getAdresa());
			System.out.println("\tDrzavljanstvo " );
		} else if (nepodnosilac.getAutor() instanceof TAutorPreminuo) {
			TAutorPreminuo autor = (TAutorPreminuo) nepodnosilac.getAutor();
			System.out.println("\tIme: " + autor.getIme());
			System.out.println("\tPrezime " + autor.getPrezime());
			System.out.println("\tGodina smrti: " + autor.getGodinaSmrti());
		}
	}
	
	private void printRadniOdnos(ObrazacA1.Zahtev.AutorskoDeloStvorenoRadnimOdnosom radniOdnos) {
		System.out.println("Autorsko delo stvoreno radnim odnosom - " + radniOdnos.getValue());
	}
	
	private void printNacinKoriscenja(ObrazacA1.Zahtev.NacinKoriscenja nacin) {
		System.out.println("Nacin koriscenja - " + nacin.getValue());
	}
	
	private void printBrojPrijave(ObrazacA1.Zahtev.BrojPrijave br) {
		System.out.println("Broj prijave - " + br.getValue());
	}
	
	private void printDatumPodnosenja(ObrazacA1.Zahtev.DatumPodnosenja datum) {
		System.out.println("Datum podnosenja - " + datum.getValue());
	}
}
