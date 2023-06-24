package bib.main.domain;

import bib.main.entities.Benutzer;
import bib.main.entities.Kunde;
import bib.main.entities.Mitarbeiter;

import java.util.ArrayList;


public class BenutzerVerwaltung {
    protected ArrayList<Mitarbeiter> mitarbeiterListe = new ArrayList<Mitarbeiter>();
    protected ArrayList<Kunde> Kundenbestand = new ArrayList<Kunde>();

    public BenutzerVerwaltung() {
        Kundenbestand.add(new Kunde("Emily", "Rekewitz", 100, "Hallo", "Bremerweg", 12, "Bremen", 28199, "Deutschland"));
        mitarbeiterListe.add(new Mitarbeiter("Max", "Mustermann", 111, "123", "Bremerweg", 14, "Bremen", 28199, "Deutschland"));
    }

    public Benutzer einloggen(String benutzername, String passwort) {
        try {
            for (Kunde kunde : Kundenbestand) {
                if (benutzername.equals(kunde.getBenutzerName())) {
                    if (passwort.equals(kunde.getPasswort())) {
                        return kunde;
                    }
                }
            }

            for (Mitarbeiter mitarbeiter : mitarbeiterListe) {
                if (benutzername.equals(mitarbeiter.getBenutzerName())) {
                    if (passwort.equals(mitarbeiter.getPasswort())) {
                        return mitarbeiter;
                    }
                }
            }


        } catch (Exception e) {
            System.out.println("Benutzername oder Passwort falsch"); //Exception BenutzerExistiert nicht
        }
        return null;

    }
}


