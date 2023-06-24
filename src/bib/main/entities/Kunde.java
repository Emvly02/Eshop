package bib.main.entities;

import java.util.HashMap;
import java.util.Map;

public class Kunde extends Benutzer {

	 /*
        ATTRIBUTE: (Name, Nummer), Adresse
        Können sich über Benutzerkennung und Passwort einloggen
        Können sich selbst als Kunde registrieren
        Können mehrere! Artikel in ihren Warenkorb legen
        Können stückzahl der Artikel im Warenkorb ändern
        Können den Warenkorb leeren

        Können Artikel im Warenkorb kaufen
        -> Danach wird Warenkorb geleert & Artikel werden aus dem Bestand genommen
        -> Dann wird eine Rechnung erzeugt und auf dem Bildschirm ausgegeben (nicht hier oder)
     */

	private float gesamtpreis;

	private Warenkorb korb = new Warenkorb();
	private Map<Kunde, Warenkorb> rechnung = new HashMap<Kunde, Warenkorb>();


	public Kunde(String vorname, String nachname, int ID, String passwort, String strasse, int hausnr, String ort, int plz, String land) {
		super(vorname, nachname, passwort, ID, strasse, hausnr, ort, plz, land);
	}

	public Warenkorb getKorb() {
		return korb;
	}


	public Map<Kunde, Warenkorb> getRechnung() {
		return rechnung;
	}

	public float getGesamtpreis() {
		return gesamtpreis;
	}

	public void setGesamtpreis(float gesamtpreis) {
		this.gesamtpreis = gesamtpreis;
	}
}

