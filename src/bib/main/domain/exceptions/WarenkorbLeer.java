package bib.main.domain.exceptions;

public class WarenkorbLeer extends Exception{
    public WarenkorbLeer() {
        super("Ihr Warenkorb ist leer");
    }
}
