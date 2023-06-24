package bib.main.pm;

import java.io.IOException;

import bib.main.entities.Artikel;

public class DBPersistenceManager implements PersistenceManager {

	@Override
	public boolean close() {
		return false;
	}

	@Override
	public Artikel ladeArtikel() throws IOException {
		return null;
	}

	@Override
	public void openForReading(String datenquelle) throws IOException {

	}

	@Override
	public void openForWriting(String datenquelle) throws IOException {

	}

	@Override
	public boolean speichereArtikel(Artikel a) throws IOException {
		return false;
	}

}
