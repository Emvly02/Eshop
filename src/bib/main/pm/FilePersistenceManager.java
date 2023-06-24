package bib.main.pm;

import bib.main.entities.Artikel;

import java.io.*;

public class FilePersistenceManager implements PersistenceManager {

	private BufferedReader reader = null;
	private PrintWriter writer = null;
	
	public void openForReading(String datei) throws FileNotFoundException {
		reader = new BufferedReader(new FileReader(datei));
	}

	public void openForWriting(String datei) throws IOException {
		writer = new PrintWriter(new BufferedWriter(new FileWriter(datei)));
	}

	public boolean close() {
		if (writer != null)
			writer.close();
		
		if (reader != null) {
			try {
				reader.close();
			} catch (IOException e) {

				e.printStackTrace();
				
				return false;
			}
		}
		return true;
	}

	public Artikel ladeArtikel() throws IOException {
		String bezeichnung = liesZeile();
		if (bezeichnung == null) {
			return null;
		}
		String artikelNrString = liesZeile();
		int artikelNr = Integer.parseInt(artikelNrString);
		
		// Artikel ausverkauft?
		String verfuegbarCode = liesZeile();
		boolean verfuegbar = verfuegbarCode.equals("t") ? true : false;

		return new Artikel(bezeichnung, artikelNr);
	}

	public boolean speichereArtikel(Artikel a) throws IOException {
		schreibeZeile(a.getBezeichnung());
		schreibeZeile(a.getArtikelNr() + "");
		if (a.istVerfuegbar())
			schreibeZeile("t");
		else
			schreibeZeile("f");

		return true;
	}

	
	private String liesZeile() throws IOException {
		if (reader != null)
			return reader.readLine();
		else
			return "";
	}

	private void schreibeZeile(String daten) {
		if (writer != null)
			writer.println(daten);
	}
}
