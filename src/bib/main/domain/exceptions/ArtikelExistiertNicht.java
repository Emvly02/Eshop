package bib.main.domain.exceptions;

import bib.main.entities.Artikel;

public class ArtikelExistiertNicht extends Exception{
    public ArtikelExistiertNicht(Artikel artikel) {
        super("Der gesuchte Artikel: " + artikel.getBezeichnung() + "kann nicht in unserem Bestand gefunden werden");
    }
}
