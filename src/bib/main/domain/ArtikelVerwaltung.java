package bib.main.domain;

import bib.main.domain.exceptions.ArtikelExistiertSchonException;
import bib.main.entities.Artikel;
import bib.main.pm.FilePersistenceManager;
import bib.main.pm.PersistenceManager;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ArtikelVerwaltung {

	private List<Artikel> artikelBestand = new ArrayList<>();
	private PersistenceManager pm = new FilePersistenceManager();
	
	public ArtikelVerwaltung() {
		artikelBestand.add(new Artikel("Banane", 1, 1.99f, 10));
		artikelBestand.add(new Artikel("Apfel", 2, 1.99f, 99));
		artikelBestand.add(new Artikel("Birne", 3, 1.99f, 20));
		try{
			List<String> Stammdaten = Files.readAllLines(Paths.get(String.format("%s\\Artikel", System.getProperty("user.dir"))));
			for(int i=0; i<Stammdaten.size(); i++){
				String[] Arg = Stammdaten.get(i).split(",");
				artikelBestand.add(new Artikel(Arg[0], Integer.parseInt(Arg[1]), Float.parseFloat(Arg[2]), Integer.parseInt(Arg[3])));
			}
		}
		catch(Exception Ausnahme){
		}
	}


	public void liesDaten(String datei) throws IOException {
		pm.openForReading(datei);

		Artikel einArtikel;
		do {
			einArtikel = pm.ladeArtikel();
			if (einArtikel != null) {
				try {
					einfuegen(einArtikel);
				} catch (ArtikelExistiertSchonException e1) {
					//nichts
				}
			}
		} while (einArtikel != null);

		pm.close();
	}


	public void einfuegen(Artikel einArtikel) throws ArtikelExistiertSchonException {
		if (artikelBestand.contains(einArtikel)) {
			throw new ArtikelExistiertSchonException(einArtikel, " - in 'einfuegen()'");

		}

		artikelBestand.add(einArtikel);
		try{
			List<String> Stammdaten = Files.readAllLines(Paths.get(String.format("%s\\Artikel", System.getProperty("user.dir"))));
			Stammdaten.add(String.format("%s,%s,%s,%s", einArtikel.getBezeichnung(), einArtikel.getArtikelNr(), einArtikel.getPreis(), einArtikel.getBestand()));

			FileWriter fileWriter = new FileWriter(String.format("%s\\Artikel", System.getProperty("user.dir")));
			for(String aktuelleZeile : Stammdaten){
				fileWriter.write(String.format("%s\n", aktuelleZeile));
			}
			fileWriter.close();
		}
		catch(Exception Ausnahme){
		}
	}


	public void loeschen(Artikel einArtikel) {
		for(Artikel artikel : artikelBestand){
			if(artikel == einArtikel){
				artikelBestand.remove(einArtikel);
			}
		}
	}

	public List<Artikel> sucheArtikel(String bezeichnung) {
		List<Artikel> suchErg = new ArrayList<>();
		for (Artikel aktArtikel : artikelBestand) {
			if (aktArtikel.getBezeichnung().equals(bezeichnung)) {
				suchErg.add(aktArtikel);
			}
		}
		return suchErg;
	}

	public Artikel findeBestimmtenArtikel(String bezeichnung){
		for(Artikel aktArtikel : artikelBestand){
			if(aktArtikel.getBezeichnung().equals(bezeichnung)){
				return aktArtikel;
			}
		}
		return null;
	}

	public List<Artikel> getArtikelBestand() {
		return new ArrayList<>(artikelBestand);
	}
	
//hello
}
