package bib.main.entities;

public class Artikel {

	private String bezeichnung;
	private int artikelNr;
	private boolean verfuegbar;
	private int bestand;
	private float preis;

	public Artikel() {
	}

	public Artikel(String bezeichnung, int nr, float preis, int bestand) {
		this.bezeichnung = bezeichnung;
		this.artikelNr = nr;
		this.preis = preis;
		this.verfuegbar = true;
		this.bestand = bestand;
	}
	public Artikel(String bezeichnung, int nr) {
		this.bezeichnung = bezeichnung;
		this.artikelNr = nr;
		this.verfuegbar = true;
		this.bestand = 0;
	}



	public String toString() {
		String verfuegbarkeit = verfuegbar ? "verfuegbar" : "ausverkauft";
		return ("ArtikelNr: " + artikelNr + " / Bezeichnung: " + bezeichnung + " / " + verfuegbarkeit + " = " + bestand);
	}

	public boolean equals(Object andererArtikel) {
		if (andererArtikel instanceof Artikel)
			return ((this.artikelNr == ((Artikel) andererArtikel).artikelNr)
					&& (this.bezeichnung.equals(((Artikel) andererArtikel).bezeichnung)));
		else
			return false;
	}

	

	
	public int getArtikelNr() {
		return artikelNr;
	}

	public String getBezeichnung() {
		return bezeichnung;
	}

	public boolean istVerfuegbar() {
		return verfuegbar;
	}

	public float getPreis() {
		return preis;
	}

	public int getBestand() {
		return bestand;
	}

	public void setBestand(int bestand) {
		this.bestand = bestand;
	}
}
