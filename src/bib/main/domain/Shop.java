package bib.main.domain;

import bib.main.domain.exceptions.*;
import bib.main.entities.*;
import bib.main.domain.exceptions.*;

import java.util.List;

public class Shop {
	private String datei = "";
	private ArtikelVerwaltung artikelVW;
	private MitarbeiterVerwaltung mitarbeiterVW;
	private KundenVerwaltung kundenVW;
	private BenutzerVerwaltung benutzerVW;
	private ShoppingService shoppingService;
	private MassengutArtikelVerwaltung massengutVW;


	public Shop(String datei) {

		artikelVW = new ArtikelVerwaltung();
		kundenVW = new KundenVerwaltung();
		mitarbeiterVW = new MitarbeiterVerwaltung();
		benutzerVW = new BenutzerVerwaltung();
		shoppingService = new ShoppingService();
		massengutVW = new MassengutArtikelVerwaltung();
	}

	public List<Artikel> gibAlleArtikel() {
		return artikelVW.getArtikelBestand();
	}

	public List<MassengutArtikel> gibAlleMArtikel() {
		return massengutVW.getMassengutBestand();
	}


	public List<Artikel> sucheNachArtikel(String artikel) throws ArtikelExistiertNicht {
		return artikelVW.sucheArtikel(artikel);
	}

	public List<MassengutArtikel> sucheNachMassengutArtikel(String artikel) throws MassengutArtikelGibtEsNicht {
		return massengutVW.sucheMassengutArtikel(artikel);
	}

	public Artikel fuegeArtikelEin(String bezeichnung, int artikelNr, float preis) throws ArtikelExistiertSchonException {
		Artikel a = new Artikel(bezeichnung, artikelNr, preis, 0);
		artikelVW.einfuegen(a);
		return a;
	}

	public MassengutArtikel fuegeMArtikelEin(String bezeichnung, int artikelNr, float preis, int bestand, int packungsgroesse) throws MassengutArtikelGibtEsSchon {
		MassengutArtikel a = new MassengutArtikel(bezeichnung, artikelNr, preis, bestand, packungsgroesse);
		massengutVW.massengutAufnehmen(a);
		return a;
	}


	public void loescheArtikel(String bezeichnung, int artikelNr, float preis) throws ArtikelExistiertNicht{
		Artikel a = new Artikel(bezeichnung, artikelNr, preis, 0);
		artikelVW.loeschen(a);
	}

	public Benutzer Benutzereinloggen(String benutzername, String passw) throws LoginFehlgeschlagen {
		Benutzer benutzer = benutzerVW.einloggen(benutzername, passw);
		return benutzer;
	}

	public void registrieren(Kunde kunde) throws RegistrierungNichtMoeglich {
		kundenVW.registrieren(kunde);
	}

	public void mitarbeiterRegistrieren(Mitarbeiter mitarbeiter) throws RegistrierungNichtMoeglich {
		mitarbeiterVW.mitarbeiterRegistrieren(mitarbeiter);
	}

	public void warenKorbKaufen(Kunde kunde) throws WarenkorbLeer {
		shoppingService.artikelKaufen(kunde);
		for (Object eintrag : shoppingService.warenkorbAuflisten(kunde).entrySet()) {
			Artikel artikel = (Artikel) eintrag;
			artikelVW.loeschen(artikel);
		}
	}

	public void warenKorbLeeren(Kunde kunde) throws WarenkorbLeer{
		shoppingService.warenkorbAusleeren(kunde);
	}

	public void einenArtikelEntfernen(Kunde kunde, Artikel artikel){
		shoppingService.artikelentfernen(kunde, artikel);
	}

	public Artikel artikelInKorbFinden(Kunde kunde, String gesuchterArtikel) throws ArtikelNichtImWarenkorbEnthalten{
		for (Object suche : shoppingService.warenkorbAuflisten(kunde).entrySet()) {
			Artikel artikel = (Artikel) suche;
			if(artikel.equals(((Artikel) suche).getBezeichnung())){
					return artikel;
			}
		}
		return null;
	}

	public void artikelInWarenKorbTun(Kunde kunde, Artikel artikel, int anzahl){
		shoppingService.artikelInWarenkorbLegen(kunde, artikel, anzahl);
	}

	public Artikel artikelImBestandFinden(String bezeichnung) throws ArtikelExistiertNicht{
		Artikel gefundenerArtikel = artikelVW.findeBestimmtenArtikel(bezeichnung);
		return gefundenerArtikel;
	}

	public boolean gibtEsDenKunden(Kunde kunde){
		for(Kunde aktKunde : kundenVW.Kundenbestand){
			if(aktKunde == kunde){
				return true;
			}
		}
		return false;
	}

	public void gesamtPreisBerechnen(Kunde kunde){
		float gesamtPreis = 0;
		for (Object suche : shoppingService.warenkorbAuflisten(kunde).entrySet()) {
			Artikel artikel = (Artikel) suche;
			gesamtPreis += artikel.getPreis();
			kunde.setGesamtpreis(gesamtPreis);
		}
	}

	/*public void massengutAufnehmen(String bezeichnung, int artikelNr){
		MassengutArtikel m = new MassengutArtikel();
		massengutVW.MassengutAufnehmen(mArtikel);
	}

	 */

	//Kunde Rechnung noch machen & Ereignis speichern


}
