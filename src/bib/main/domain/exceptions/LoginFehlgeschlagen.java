package bib.main.domain.exceptions;

import bib.main.entities.Benutzer;

public class LoginFehlgeschlagen extends Exception{

    public LoginFehlgeschlagen(Benutzer benutzer) {
        super("Benutzer mit Benutzernamen: " + benutzer.getBenutzerName() + " gibt es nicht");
    }
}
