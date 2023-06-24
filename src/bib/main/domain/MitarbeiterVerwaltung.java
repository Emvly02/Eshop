package bib.main.domain;

import bib.main.entities.Mitarbeiter;
import bib.main.pm.FilePersistenceManager;
import bib.main.pm.PersistenceManager;

import java.util.ArrayList;

public class MitarbeiterVerwaltung extends BenutzerVerwaltung {

    private PersistenceManager pm = new FilePersistenceManager();

    public void einfuegen(Mitarbeiter einMitarbeiter){
        try{
            if (!mitarbeiterListe.contains(einMitarbeiter)) {
                mitarbeiterListe.add(einMitarbeiter);
            }
        }
        catch(Exception e){ //Exception Registrierung nicht möglich
            System.out.println("Fehler bei Mitarbeiter-Registrierung");
        }

    }

    public void mitarbeiterRegistrieren(Mitarbeiter einMitarbeiter){
        if (!mitarbeiterListe.contains(einMitarbeiter)) {
            mitarbeiterListe.add(einMitarbeiter);
        }
    }


    public ArrayList getMitarbeiterListe() {
        return mitarbeiterListe;
    }

}




