package bib.main.domain;

import bib.main.entities.Kunde;

import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class KundenVerwaltung extends BenutzerVerwaltung {
    public void registrieren(Kunde einKunde){
        if (!Kundenbestand.contains(einKunde)) {
            Kundenbestand.add(einKunde);
            try{
                List<String> Stammdaten = Files.readAllLines(Paths.get(String.format("%s\\Kunde", System.getProperty("user.dir"))));
                Stammdaten.add(String.format("%s,%s,%s,%s,%s,%s", einKunde.getVorname(), einKunde.getNachname(), einKunde.getId(), einKunde.getPasswort(), einKunde.getStrasse(), einKunde.getHausnr(), einKunde.getOrt(), einKunde.getPlz(), einKunde.getLand()));

                FileWriter fileWriter = new FileWriter(String.format("%s\\Kunde", System.getProperty("user.dir")));
                for(String aktuelleZeile : Stammdaten){
                    fileWriter.write(String.format("%s\n", aktuelleZeile));
                }
                fileWriter.close();
            }
            catch(Exception Ausnahme){
            }
        }
    }












}
