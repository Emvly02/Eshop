package bib.main.domain;

import bib.main.domain.exceptions.ArtikelExistiertSchonException;
import bib.main.entities.Artikel;
import bib.main.pm.FilePersistenceManager;
import bib.main.pm.PersistenceManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ArtikelVerwaltung {

	private List<Artikel> artikelBestand = new ArrayList<>();
	private PersistenceManager pm = new FilePersistenceManager();
	
	public ArtikelVerwaltung() {
		artikelBestand.add(new Artikel("Banane", 1, 1.99f, 10));
		artikelBestand.add(new Artikel("Apfel", 2, 1.99f, 99));
		artikelBestand.add(new Artikel("Birne", 3, 1.99f, 20));
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
