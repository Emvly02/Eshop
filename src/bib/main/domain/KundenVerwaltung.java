package bib.main.domain;

import bib.main.entities.Kunde;

public class KundenVerwaltung extends BenutzerVerwaltung {
    public void registrieren(Kunde einKunde){
        if (!Kundenbestand.contains(einKunde)) {
            Kundenbestand.add(einKunde);
        }
    }












}
