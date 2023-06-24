package bib.main.domain.exceptions;

import bib.main.entities.Artikel;
public class ArtikelExistiertSchonException extends Exception {

	private Artikel artikel;

	public ArtikelExistiertSchonException(Artikel artikel, String zusatzMsg) {
		super("Artikel mit Bezeichnung " + artikel.getBezeichnung() + " und Artikelnummer " + artikel.getArtikelNr()
				+ " existiert bereits" + zusatzMsg);
		this.artikel = artikel;
	}

	public Artikel getBuch() {
		return artikel;
	}
}
