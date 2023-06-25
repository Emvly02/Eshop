package bib.main.ui.CUI.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import bib.main.domain.*;
import bib.main.domain.exceptions.ArtikelExistiertNicht;
import bib.main.domain.exceptions.LoginFehlgeschlagen;
import bib.main.domain.exceptions.MassengutArtikelExistiertNicht;
import bib.main.domain.exceptions.RegistrierungNichtMoeglich;
import bib.main.entities.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ShopGUI extends JFrame implements ActionListener {
    private Shop eshop;
    private BufferedReader in;
    private ButtonPanelHauptmenue buttonPanelHauptmenue;
    private ArtikellistePanel artikellistPanel;
    private ArtikelSuchePanel artikelSuchePanel;
    private ButtonPanelKundenmenue kundenmenuePanel;

    public static void main(String[] args) {
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

        buttonPanelHauptmenue = new ButtonPanelHauptmenue(this);
        add(buttonPanelHauptmenue, BorderLayout.CENTER);

        artikellistPanel = new ArtikellistePanel();
        add(artikellistPanel, BorderLayout.EAST);

        artikelSuchePanel = new ArtikelSuchePanel();
        add(artikelSuchePanel, BorderLayout.SOUTH);

        setTitle("E-Shop");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonPanelHauptmenue.getBtnArtikelliste()) {
            try {
                List<Artikel> artikelListe = eshop.gibAlleArtikel();
                List<MassengutArtikel> massengutArtikelListe = eshop.gibAlleMArtikel();
                artikellistPanel.displayArtikellisten(artikelListe, massengutArtikelListe);

            } catch (IOException ex) {
                ex.printStackTrace();
            }

        } else if (e.getSource() == buttonPanelHauptmenue.getBtnRegistrieren()) {
            try {
                eshop.registrieren(registrieren());
                JOptionPane.showMessageDialog(this, "Willkommen!");
            } catch (RegistrierungNichtMoeglich r) {
                JOptionPane.showMessageDialog(this, r.getMessage());
            }

        } else if (e.getSource() == buttonPanelHauptmenue.getBtnLogin()) {
            String benutzername = JOptionPane.showInputDialog(this, "Benutzername:");
            String passwort = JOptionPane.showInputDialog(this, "Passwort:");
            try {
                // KUNDENLOGIN UND MENUE
                if (eshop.Benutzereinloggen(benutzername, passwort) instanceof Kunde) {
                    Kunde eingeloggterKunde = (Kunde) eshop.getBenutzer();
                    JOptionPane.showMessageDialog(this, "Kunden-Login erfolgreich");
                    anzeigeKundenmenue();
                }
                // MITARBEITERLOGIN UND MENUE
                else if (eshop.Benutzereinloggen(benutzername, passwort) instanceof Mitarbeiter) {
                    Mitarbeiter eingeloggterMitarbeiter = (Mitarbeiter) eshop.getBenutzer();
                    JOptionPane.showMessageDialog(this, "Mitarbeiter-Login erfolgreich");
                    // Mitarbeitermenu
                }
            } catch (LoginFehlgeschlagen l) {
                JOptionPane.showMessageDialog(this, l.getMessage());
            }

        } else if (e.getSource() == buttonPanelHauptmenue.getBtnExit()) {
            System.exit(0);
        } else if (e.getSource() == kundenmenuePanel.getBtnArtikelSuche()) {
            try {
                String artikel = JOptionPane.showInputDialog(this, "gesuchter Artikel:");
                eshop.sucheNachArtikel(artikel);
                eshop.sucheNachMassengutArtikel(artikel);
                List<Artikel> artikelListe = eshop.gibAlleArtikel();
                List<MassengutArtikel> massengutArtikelListe = eshop.gibAlleMArtikel();
                artikelSuchePanel.displayArtikelSuche(artikelListe, massengutArtikelListe, artikel);
            } catch (ArtikelExistiertNicht n) {
                JOptionPane.showMessageDialog(this, n.getMessage());
            } catch (MassengutArtikelExistiertNicht mn) {
                JOptionPane.showMessageDialog(this, mn.getMessage());
            } catch (IOException o) {
                o.printStackTrace();
            }
        } else if (e.getSource() == kundenmenuePanel.getBtnArtikelInWarenkorb()) {
            // Logik für Artikel in den Warenkorb legen
        } else if (e.getSource() == kundenmenuePanel.getBtnWarenkorbBearbeiten()) {
            // Logik für Warenkorb bearbeiten
        } else if (e.getSource() == kundenmenuePanel.getBtnZurueck()) {
            // Logik für Zurück-Button im Kundenmenü
        }
    }

    private Kunde registrieren() {
        String vorname = JOptionPane.showInputDialog(this, "Vorname: ");
        String nachname = JOptionPane.showInputDialog(this, "Nachname:");
        int idNr = Integer.parseInt(JOptionPane.showInputDialog(this, "Waehlen sie eine ID-Nummer:"));
        String passwort = JOptionPane.showInputDialog(this, "Passwort:");
        String strasse = JOptionPane.showInputDialog(this, "Strasse:");
        int hausnr = Integer.parseInt(JOptionPane.showInputDialog(this, "Hausnummer:"));
        String ort = JOptionPane.showInputDialog(this, "Ort:");
        int plz = Integer.parseInt(JOptionPane.showInputDialog(this, "PLZ:"));
        String land = JOptionPane.showInputDialog(this, "Land:");
        return new Kunde(vorname, nachname, idNr, passwort, strasse, hausnr, ort, plz, land);
    }

    public void anzeigeKundenmenue() {
        remove(buttonPanelHauptmenue);
        remove(artikellistPanel);
        remove(artikelSuchePanel);
        kundenmenuePanel = new ButtonPanelKundenmenue(this);
        add(kundenmenuePanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    public void run() {
        setVisible(true);
    }
}
