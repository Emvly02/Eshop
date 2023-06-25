package bib.main.domain;

import bib.main.entities.Mitarbeiter;
import bib.main.pm.FilePersistenceManager;
import bib.main.pm.PersistenceManager;

import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class MitarbeiterVerwaltung extends BenutzerVerwaltung {

    private PersistenceManager pm = new FilePersistenceManager();

    public void einfuegen(Mitarbeiter einMitarbeiter){
        try{
            if (!mitarbeiterListe.contains(einMitarbeiter)) {
                mitarbeiterListe.add(einMitarbeiter);
                try{
                    List<String> Stammdaten = Files.readAllLines(Paths.get(String.format("%s\\Mitarbeiter", System.getProperty("user.dir"))));
                    Stammdaten.add(String.format("%s,%s,%s,%s,%s,%s", einMitarbeiter.getVorname(), einMitarbeiter.getNachname(), einMitarbeiter.getId(), einMitarbeiter.getPasswort(), einMitarbeiter.getStrasse(), einMitarbeiter.getHausnr(), einMitarbeiter.getOrt(), einMitarbeiter.getPlz(), einMitarbeiter.getLand()));

                    FileWriter fileWriter = new FileWriter(String.format("%s\\Mitarbeiter", System.getProperty("user.dir")));
                    for(String aktuelleZeile : Stammdaten){
                        fileWriter.write(String.format("%s\n", aktuelleZeile));
                    }
                    fileWriter.close();
                }
                catch (Exception Ausnahme){

                }
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




