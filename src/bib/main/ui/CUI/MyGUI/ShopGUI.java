package bib.main.ui.CUI.MyGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

import bib.main.domain.*;
import bib.main.domain.exceptions.LoginFehlgeschlagen;
import bib.main.domain.exceptions.RegistrierungNichtMoeglich;
import bib.main.entities.*;
import bib.main.ui.CUI.gui.panels.ArtikelListPanel;


public class ShopGUI extends JFrame implements ActionListener {
    private Shop eshop;
    private BufferedReader in;
    private ButtonPanel buttonPanel;
    private ArtikellistePanel artikellistPanel;
    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> {
            try {
                ShopGUI shopGUI = new ShopGUI("GUI");
                shopGUI.run();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
    public ShopGUI(String datei) throws IOException {
        eshop = new Shop(datei);
        in = new BufferedReader(new InputStreamReader(System.in));

        buttonPanel = new ButtonPanel(this);
        add(buttonPanel);

        artikellistPanel = new ArtikellistePanel();
        add(artikellistPanel, BorderLayout.EAST);

        setTitle("E-Shop");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonPanel.getBtnArtikelliste()) {
            try {
                List<Artikel> artikelListe = eshop.gibAlleArtikel();
               // gibArtikellisteAus(artikelListe);
                List<MassengutArtikel> massengutArtikelListe = eshop.gibAlleMArtikel();
               // gibMassengutArtikellisteAus(massengutArtikelListe);
                artikellistPanel.displayArtikelliste(artikelListe);

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == buttonPanel.getBtnRegistrieren()) {
            try {
                eshop.registrieren(registrieren());
                JOptionPane.showMessageDialog(this, "Willkommen!");
            }
            catch(RegistrierungNichtMoeglich r){
                JOptionPane.showMessageDialog(this, r.getMessage());
            }
        } else if (e.getSource() == buttonPanel.getBtnLogin()) {
            String benutzername = JOptionPane.showInputDialog(this, "Benutzername:");
            String passwort = JOptionPane.showInputDialog(this, "Passwort:");
            try {
                eshop.Benutzereinloggen(benutzername, passwort);
                JOptionPane.showMessageDialog(this, "Login erfolgreich");
            }
            catch(LoginFehlgeschlagen l){
                JOptionPane.showMessageDialog(this, l.getMessage());
            }

        } else if (e.getSource() == buttonPanel.getBtnExit()) {
            System.exit(0);
        }
    }

    private void gibArtikellisteAus(List<Artikel> liste) throws IOException {
        for (Artikel artikel : liste) {
            System.out.println(artikel);
        }
    }

    private void gibMassengutArtikellisteAus(List<MassengutArtikel> liste) throws IOException{
        for (MassengutArtikel mArtikel : liste) {
            System.out.println(mArtikel);
        }
    }

    private Kunde registrieren(){
        String vorname = JOptionPane.showInputDialog(this, "Vorname: ");
        String nachname = JOptionPane.showInputDialog(this, "Nachname:");
        int idNr = Integer.parseInt(JOptionPane.showInputDialog(this, "Waehlen sie eine ID-Nummer:"));
        String passwort = JOptionPane.showInputDialog(this, "Passwort:");;
        String strasse = JOptionPane.showInputDialog(this, "Strasse:");;
        int hausnr = Integer.parseInt(JOptionPane.showInputDialog(this, "Hausnummer:"));
        String ort = JOptionPane.showInputDialog(this, "Ort:");;
        int plz = Integer.parseInt(JOptionPane.showInputDialog(this, "PLZ:"));
        String land = JOptionPane.showInputDialog(this, "Land:");;
        return new Kunde(vorname, nachname, idNr, passwort, strasse, hausnr, ort, plz, land);
    }

    /*public void zeigeArtikelliste(List<Artikel> articleList) {
        artikellistPanel.displayArtikelliste(articleList);
    }

     */

    public void run() {
        setVisible(true);
    }
}

