package bib.main.domain.exceptions;

import bib.main.entities.Artikel;

public class ArtikelNichtImWarenkorbEnthalten extends Exception{
    public ArtikelNichtImWarenkorbEnthalten(Artikel artikel) {
        super("Der Artikel: " + artikel.getBezeichnung() + "kann nicht in ihrem Warenkorb gefunden werden");
    }
}
