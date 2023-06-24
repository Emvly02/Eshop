package bib.main.ui.CUI.gui.panels;

import bib.local.entities.Buch;
import bib.local.ui.gui.models.BuecherTableModel;

import javax.swing.*;
import java.util.Collections;

public class BooksTablePanel extends JTable {

	public BooksTablePanel(java.util.List<Buch> buecher) {
		super();

		// TableModel erzeugen ...
		BuecherTableModel tableModel = new BuecherTableModel(buecher);
		// ... bei JTable "anmelden" und ...
		setModel(tableModel);
		// ... Daten an Model übergeben (für Sortierung o.ä.)
		updateBooksList(buecher);
	}
	
	public void updateBooksList(java.util.List<Buch> buecher) {

		// Sortierung (mit Lambda-Expression)
//		Collections.sort(buecher, (b1, b2) -> b1.getTitel().compareTo(b2.getTitel()));	// Sortierung nach Titel
		Collections.sort(buecher, (b1, b2) -> b1.getNummer() - b2.getNummer());	// Sortierung nach Nummer

		// TableModel von JTable holen und ...
		BuecherTableModel tableModel = (BuecherTableModel) getModel();
		// ... Inhalt aktualisieren
		tableModel.setBooks(buecher);
	}
}
