package bib.main.pm;

import java.io.IOException;

import bib.main.entities.Artikel;

public interface PersistenceManager {

	public void openForReading(String datenquelle) throws IOException;
	
	public void openForWriting(String datenquelle) throws IOException;
	
	public boolean close();

	public Artikel ladeArtikel() throws IOException;


	public boolean speichereArtikel(Artikel a) throws IOException;

}
