package bib.main.ui.CUI.gui;

import bib.local.domain.Bibliothek;
import bib.local.domain.exceptions.BuchExistiertBereitsException;
import bib.local.entities.Buch;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class AlternativeAddBookListener implements ActionListener {

    private Bibliothek bib;
    private JTextField nummerTextFeld = null;
    private JTextField titelTextFeld = null;
    private JList buecherListe = null;

    public AlternativeAddBookListener(Bibliothek bib, JTextField nummerTextFeld, JTextField titelTextFeld, JList buecherListe) {
        this.bib = bib;
        this.nummerTextFeld = nummerTextFeld;
        this.titelTextFeld = titelTextFeld;
        this.buecherListe = buecherListe;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            int nummer = Integer.parseInt(nummerTextFeld.getText());
            String titel = titelTextFeld.getText();
            bib.fuegeBuchEin(titel, nummer);
            java.util.List<Buch> buecher = bib.gibAlleBuecher();
            buecherListe.setListData(new Vector(buecher));
        } catch (BuchExistiertBereitsException | NumberFormatException ex) {
            System.err.println("Fehler: " + ex.getMessage());
        }
    }
}
