package bib.main.domain;

import bib.main.entities.Artikel;
import bib.main.entities.Benutzer;
import bib.main.entities.Kunde;
import bib.main.entities.Mitarbeiter;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class BenutzerVerwaltung {
    protected ArrayList<Mitarbeiter> mitarbeiterListe = new ArrayList<Mitarbeiter>();
    protected ArrayList<Kunde> Kundenbestand = new ArrayList<Kunde>();

    public BenutzerVerwaltung() {
        Kundenbestand.add(new Kunde("Emily", "Rekewitz", 100, "Hallo", "Bremerweg", 12, "Bremen", 28199, "Deutschland"));
        mitarbeiterListe.add(new Mitarbeiter("Max", "Mustermann", 111, "123", "Bremerweg", 14, "Bremen", 28199, "Deutschland"));
        try{
            List<String> Stammdaten = Files.readAllLines(Paths.get(String.format("%s\\Kunde", System.getProperty("user.dir"))));
            for(int i=0; i<Stammdaten.size(); i++){
                String[] Arg = Stammdaten.get(i).split(",");
                Kundenbestand.add(new Kunde(Arg[0], Arg[1], Integer.parseInt(Arg[2]), Arg[3], Arg[4], Integer.parseInt(Arg[5]), Arg[6], Integer.parseInt(Arg[7]), Arg[7]));
            }
        }
        catch(Exception Ausnahme){
        }
        try{
            List<String> Stammdaten = Files.readAllLines(Paths.get(String.format("%s\\Mitarbeiter", System.getProperty("user.dir"))));
            for(int i=0; i<Stammdaten.size(); i++){
                String[] Arg = Stammdaten.get(i).split(",");
                mitarbeiterListe.add(new Mitarbeiter(Arg[0], Arg[1], Integer.parseInt(Arg[2]), Arg[3], Arg[4], Integer.parseInt(Arg[5]), Arg[6], Integer.parseInt(Arg[7]), Arg[7]));
            }
        }
        catch(Exception Ausnahme){
        }
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


