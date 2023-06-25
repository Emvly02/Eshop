package bib.main.ui.CUI;

import bib.main.domain.Shop;
import bib.main.domain.exceptions.*;
import bib.main.entities.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

public class ShopCUI {

	private Shop eshop;
	private BufferedReader in;
	private Benutzer eingeloggterBenutzer;

	private Mitarbeiter eingeloggterMitarbeiter;
	Scanner scanner = new Scanner(System.in);


	public ShopCUI(String datei) throws IOException {

		eshop = new Shop(datei);
		in = new BufferedReader(new InputStreamReader(System.in));
	}


	private String liesEingabe() throws IOException {
		return in.readLine();
	}


	private void verarbeiteEingabe(String line) throws IOException {
		String bezeichnung;
		int artikelNr;
		int id;
		List<Artikel> artikelListe;
		List<MassengutArtikel> massengutArtikelListe;

		switch (line) {
		case "a": //alle Artikel
			artikelListe = eshop.gibAlleArtikel();
			gibArtikellisteAus(artikelListe);
			massengutArtikelListe = eshop.gibAlleMArtikel();
			gibMArtikellisteAus(massengutArtikelListe);
			break;


			case "r": //registrieren
				System.out.println("Bitte geben sie ihren Vornamen ein:");
				String vorname = liesEingabe();
				System.out.println("Bitte geben sie ihren Nachnamen ein:");
				String nachname = liesEingabe();
				System.out.println("Bitte wählen sie eine ID-Nummer:");
				int idNr = Integer.parseInt(liesEingabe());
				System.out.println("Bitte wählen sie ein Passwort:");
				String passwort = liesEingabe();
				System.out.println("Bitte geben sie die Strasse ein, in der sie wohnen:");
				String strasse = liesEingabe();
				System.out.println("Bitte geben sie ihre Hausnummer ein:");
				int hausnr = Integer.parseInt(liesEingabe());
				System.out.println("Bitte geben sie ihren Wohnort ein:");
				String ort = liesEingabe();
				System.out.println("Bitte geben sie ihre PLZ ein:");
				int plz = Integer.parseInt(liesEingabe());
				System.out.println("Bitte geben sie das Land an, in welchem sie wohnen:");
				String land = liesEingabe();

				try {
					eshop.registrieren(new Kunde(vorname, nachname, idNr, passwort, strasse, hausnr, ort, plz, land));
				}
				catch(RegistrierungNichtMoeglich r){
					System.out.println(r.getMessage());
				}
				break;



			case "lk": //Login Kunde
				System.out.println("Bitte geben sie ihren Benutzernamen ein (VornameNachname)");
				String benutzernameKunde = liesEingabe();
				System.out.println("Bitte geben sie ihr Passwort ein");
				String passwKunde = liesEingabe();
				try {
					eingeloggterBenutzer = eshop.Benutzereinloggen(benutzernameKunde, passwKunde);
					System.out.println("Hallo, Herr / Frau " + eingeloggterBenutzer.getNachname());
				}
				catch(LoginFehlgeschlagen l){
					System.out.println(l.getMessage());
				}
				String schleife = "";
				do {
					String auswahlK = kundenMenue();
					verarbeiteKundenEingabe(auswahlK);
					schleife = auswahlK;
				}
				while(!schleife.equals("exit") );
				break;

			case "lm": //Login Mitarbeiter
				System.out.println("Bitte geben sie ihren Benutzernamen ein (VornameNachname)");
				String benutzernameMitarbeiter = liesEingabe();
				System.out.println("Bitte geben sie ihr Passwort ein");
				String passwMitarbeiter = liesEingabe();
				try {
					eingeloggterBenutzer = eshop.Benutzereinloggen(benutzernameMitarbeiter, passwMitarbeiter);
				}
				catch(LoginFehlgeschlagen l){
					System.out.println(l.getMessage());
				}
				String schleifeM = "";
				do {
					String auswahlM = mitarbeiterMenue();
					verarbeiteMitarbeiterEingabe(auswahlM);
					schleifeM = auswahlM;
				}
				while(!schleifeM.equals("exit"));
				break;

			case "exit":
				System.exit(0);
				System.out.println("Vielen Dank für Ihren Besuch!");
				break;

		}
	}

	private void gibArtikellisteAus(List<Artikel> liste) {
		for(Artikel artikel : liste){
			System.out.println(artikel);
		}
	}

	private void gibMassengutArtikellisteAus(List<MassengutArtikel> liste) {
		for(MassengutArtikel mArtikel : liste){
			System.out.println(mArtikel);
		}
	}

	private void gibMArtikellisteAus(List<MassengutArtikel> liste) {
		for(MassengutArtikel mArtikel : liste){
			System.out.println(mArtikel);
		}
	}

	public void run() {
		String input = ""; 

		do {
			menue();
			try {
				input = liesEingabe();
				verarbeiteEingabe(input);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} while (!input.equals("q"));
	}




	public void menue() {
		System.out.println("Wilkommen in unserem Eshop!");
		System.out.println("Um sich als Kunde zu registrieren, drücken sie r");
		System.out.println("Um sich als Kunde anzumelden, drücken sie lk");
		System.out.println("Um sich als Mitarbeiter anzumelden, drücken sie lm");
		System.out.println("Um alle Artikel zu sehen, drücken sie a");
		System.out.println("Um den Shop zu verlassen, schreiben sie exit");
	}

	public String kundenMenue(){
		System.out.println("Willkommen im Kundenmenue!");
		System.out.println("Um einen Artikel zu finden, drücken sie f");
		System.out.println("Um einen Artikel zu ihrem Warenkorb hinzuzufuegen, drücken sie h");
		System.out.println("Um ihren Warenkorb zu bearbeiten, drücken sie w");
		System.out.println("Um den Shop zu verlassen, schreiben sie exit");
		String auswahl = scanner.nextLine();
		return auswahl;

	}

	public String mitarbeiterMenue(){
		System.out.println("Willkommen im Mitarbeitermenue!");
		System.out.println("Um einen Artikel zu entfernen drücken sie, d");
		System.out.println("Um einen neuen Mitarbeiter zu registrieren, drücken sie m");
		System.out.println("Um einen neuen Artikel hinzuzufügen, drücken sie e");
		System.out.println("Um alle EinzelArtikel zu sehen drücken sie f"); // Habe ich eigentlich auch schon auf Windows
		System.out.println("Um alle MassengutArtikel zu sehen drücken sie h");
		System.out.println("Um den Shop zu verlassen, schreiben sie exit");
		String auswahl = scanner.nextLine();
		return auswahl;

	}

	public void warenkorbMenue(){
		System.out.println("Wenn sie die Artikel in ihrem Warenkorb kaufen möchten, drücken sie k");
		System.out.println("Wenn sie einen Artikel in ihrem Warenkorb entfernen möchten,drücken sie e");
		System.out.println("Wenn sie den Warenkorb leeren möchten, drücken sie c");
		System.out.println("Wenn sie die Anzahl eines Artikels ändern möchten, drücken sie a");
		System.out.println("Um den Shop zu verlassen, schreiben sie exit");

		String antwort = scanner.nextLine();

		switch(antwort){
			case "k":
				try {
					Kunde eingeloggterKunde = (Kunde) eingeloggterBenutzer;
					eshop.warenKorbKaufen(eingeloggterKunde);
					System.out.println(eingeloggterBenutzer.getVorname() + " " + eingeloggterBenutzer.getNachname() + " " + eingeloggterBenutzer.getId());
					System.out.println(eingeloggterKunde.getKorb());
					eshop.gesamtPreisBerechnen(eingeloggterKunde);
					System.out.println(eingeloggterKunde.getGesamtpreis());
				}
				catch(WarenkorbLeer w){
					System.out.println(w.getMessage());
				}
				break;

			case "e":
				System.out.println("Bitte geben sie den Namen des Artikels ein, den sie entfernen möchten: ");
				String input = scanner.nextLine();
				Artikel gesuchterArtikel = null;
				try {
					Kunde eingeloggterKunde = (Kunde) eingeloggterBenutzer;
					gesuchterArtikel = eshop.artikelInKorbFinden(eingeloggterKunde, input);
					eshop.einenArtikelEntfernen(eingeloggterKunde, gesuchterArtikel);
				}
				catch(ArtikelNichtImWarenkorbEnthalten n){
					System.out.println(n.getMessage());
				}
				break;

			case "c":
				try {
					Kunde eingeloggterKunde = (Kunde) eingeloggterBenutzer;
					eshop.warenKorbLeeren(eingeloggterKunde);
				}
				catch(WarenkorbLeer w){
					System.out.println(w.getMessage());
				}
				break;

			case "a":
					System.out.println("Das haben wir noch nicht gemacht?"); // Ist irgendwo in einer Klasse
				break;

			case "exit":
				System.exit(0);
				System.out.println("Vielen Dank für Ihren Besuch!");
				break;

			default:
				System.out.println("Nichts ausgewählt!");
				break;
		}
	}

	public void verarbeiteKundenEingabe(String eingabe){
		String bezeichnung;
		int artikelNr;
		int id;
		List<Artikel> artikelListe;
		List<MassengutArtikel> MassengutArtikelListe;

		switch(eingabe){

			case "f":
				System.out.print("Artikelbezeichnung: ");
				bezeichnung = scanner.nextLine();
				try {
					artikelListe = eshop.sucheNachArtikel(bezeichnung);
					gibArtikellisteAus(artikelListe);
				}
				catch(ArtikelExistiertNicht a){
					System.out.println(a.getMessage());
				}

				try{
					MassengutArtikelListe = eshop.sucheNachMassengutArtikel(bezeichnung);
					gibMassengutArtikellisteAus(MassengutArtikelListe);
				}
				catch(MassengutArtikelExistiertNicht m){
					System.out.println(m.getMessage());
				}

				break;

			case "h": //Artikel zu Warenkorb hinzufügen
				System.out.println("Bitte geben sie den Namen des Artikels ein: ");
				String name = scanner.nextLine();
				Artikel gewuenschterArtikel = null;
				try {
					gewuenschterArtikel = eshop.artikelImBestandFinden(name);
					System.out.println("Wieviele dieser Artikel wollen sie in ihren Warenkorb legen?");
					int anzahl = scanner.nextInt();
					eshop.artikelInWarenKorbTun((Kunde) eingeloggterBenutzer, gewuenschterArtikel, anzahl);
				}
				catch(ArtikelExistiertNicht a){
					System.out.println(a.getMessage());
				}
				break;

			case "w":
				warenkorbMenue();

			case "exit":
				System.exit(0);
				System.out.println("Vielen Dank für Ihren Besuch!");
				break;

			default:
				System.out.println("Nichts ausgewaehlt!");

		}

	}

	public void verarbeiteMitarbeiterEingabe(String eingabe) {
		String bezeichnung;
		int artikelNr;
		int id;
		float preis;
		List<Artikel> artikelListe;
		List<MassengutArtikel> massengutArtikelListe;

		switch(eingabe){
			case "d": //delete
				System.out.print("Artikelbezeichnung: ");
				bezeichnung = scanner.nextLine();

				System.out.println("Artikelnummer: ");
				artikelNr = scanner.nextInt();

				System.out.println("Preis:");
				preis = scanner.nextFloat();

				//Verfuegbarkeit auf false setzen
				try {
					eshop.loescheArtikel(bezeichnung, artikelNr, preis);
				}
				catch(ArtikelExistiertNicht a){
					System.out.println(a.getMessage());
				}
				break;

			case "e": //einfügen
				System.out.println("Möchten sie einen Einzelartikel oder einen Massengutartikel hinzufuegen?");
				String antwort = scanner.nextLine();

				if(antwort.equals("Einzelartikel") || antwort.equals("einzelartikel")) {
					System.out.print("Artikelbezeichnung: ");
					bezeichnung = scanner.nextLine();

					System.out.println("Artikelnummer: ");
					artikelNr = scanner.nextInt();

					System.out.println("Preis: ");
					preis = scanner.nextFloat();

					try {
						eshop.fuegeArtikelEin(bezeichnung, artikelNr, preis);
						System.out.println("Einfügen ok");
						//Verfuegbarkeit auf true setzen, falls false
					} catch (ArtikelExistiertSchonException e) {
						System.out.println("Fehler beim Einfügen");
						e.printStackTrace();
					}
				}

				if(antwort.equals("Massengutartikel") || antwort.equals("massengutartikel")){
					System.out.print("Artikelbezeichnung: ");
					bezeichnung = scanner.nextLine();

					System.out.println("Artikelnummer: ");
					artikelNr = scanner.nextInt();

					System.out.println("Preis: ");
					preis = scanner.nextFloat();

					System.out.println("Bestand: ");
					int bestand = scanner.nextInt();

					System.out.println("Packungsgroesse: ");
					int packungsgroesse = scanner.nextInt();

					try {
						eshop.fuegeMArtikelEin(bezeichnung, artikelNr, preis, bestand, packungsgroesse);
						System.out.println("Einfügen ok");
						//Verfuegbarkeit auf true setzen, falls false
					} catch (MassengutArtikelGibtEsSchon e) {
						System.out.println("Fehler beim Einfügen");
						e.printStackTrace();
					}
				}

				break;

			case "f": // EinzelArtikelliste ausgeben
				artikelListe = eshop.gibAlleArtikel();
				gibArtikellisteAus(artikelListe);
				break;

			case "h": // MassengutArtikelListe ausgeben
				massengutArtikelListe = eshop.gibAlleMArtikel();
				gibMArtikellisteAus(massengutArtikelListe);
				break;


			case "m":
				System.out.println("Bitte geben sie den Vornamen ein:");
				String vorname = scanner.nextLine();
				System.out.println("Bitte geben sie den Nachnamen ein:");
				String nachname = scanner.nextLine();
				System.out.println("Bitte wählen sie eine ID-Nummer:");
				int idNr = scanner.nextInt();
				System.out.println("Bitte wählen sie ein Passwort:");
				String passwort = scanner.nextLine();
				System.out.println("Bitte geben sie die Strasse ein:");
				String strasse = scanner.nextLine();
				System.out.println("Bitte geben sie die Hausnummer ein:");
				int hausnr = scanner.nextInt();
				System.out.println("Bitte geben sie den Wohnort ein:");
				String ort = scanner.nextLine();
				System.out.println("Bitte geben sie die PLZ ein:");
				int plz = scanner.nextInt();
				System.out.println("Bitte geben sie das Land an:");
				String land = scanner.nextLine();
				try{
					eshop.mitarbeiterRegistrieren(new Mitarbeiter(vorname, nachname, idNr, passwort, strasse, hausnr, ort, plz, land));
				}
				catch(RegistrierungNichtMoeglich e){
					System.out.println(e.getMessage());
				}


				break;

			case "exit":
				System.exit(0);
				System.out.println("Vielen Dank für Ihren Besuch!");
				break;

			default:
				System.out.println("Nichts ausgewaehlt!");

		}
	}

	public static void main(String[] args) {
		ShopCUI cui;
		try {
			cui = new ShopCUI("ESHOP");
			cui.run();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
}
