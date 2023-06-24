package bib.main.ui.CUI.gui;

import bib.main.domain.Shop;
import bib.main.domain.exceptions.ArtikelExistiertSchonException;
import bib.main.entities.Artikel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class AlternativeAddArtikelListener implements ActionListener {

    private Shop eshop;
    private JTextField artikelnummerTextFeld = null;
    private JTextField bezeichnungTextFeld = null;
    private JTextField preisTextFeld = null;
    private JList artikelListe = null;

    public AlternativeAddArtikelListener(Shop eshop, JTextField artikelnummerTextFeld, JTextField bezeichnungTextFeld, JTextField preisTextFeld, JList artikelListe) {
        this.eshop = eshop;
        this.artikelnummerTextFeld = artikelnummerTextFeld;
        this.bezeichnungTextFeld = bezeichnungTextFeld;
        this.preisTextFeld = preisTextFeld;
        this.artikelListe = artikelListe;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            int artikelnummer = Integer.parseInt(artikelnummerTextFeld.getText());
            String bezeichnung = bezeichnungTextFeld.getText();
            float preis = Float.parseFloat(preisTextFeld.getText());
            eshop.fuegeArtikelEin(bezeichnung, artikelnummer, preis);
            java.util.List<Artikel> artikel = eshop.gibAlleArtikel();
            artikelListe.setListData(new Vector(artikel));
        } catch (ArtikelExistiertSchonException | NumberFormatException ex) {
            System.err.println("Fehler: " + ex.getMessage());
        }
    }
}
