package bib.main.ui.CUI.gui.panels;

import bib.main.entities.Artikel;
import bib.main.ui.CUI.gui.models.ArtikelTableModel;

import javax.swing.*;
import java.util.Collections;

public class ArtikelTablePanel extends JTable {

	public ArtikelTablePanel(java.util.List<Artikel> artikel) {
		super();

		// TableModel erzeugen ...
		ArtikelTableModel tableModel = new ArtikelTableModel(artikel);
		// ... bei JTable "anmelden" und ...
		setModel(tableModel);
		// ... Daten an Model übergeben (für Sortierung o.ä.)
		updateArtikelList(artikel);
	}
	
	public void updateArtikelList(java.util.List<Artikel> artikels) {

		// Sortierung (mit Lambda-Expression)
//		Collections.sort(buecher, (b1, b2) -> b1.getTitel().compareTo(b2.getTitel()));	// Sortierung nach Titel
		Collections.sort(artikels, (a1, a2) -> a1.getArtikelNr() - a2.getArtikelNr());	// Sortierung nach Nummer

		// TableModel von JTable holen und ...
		ArtikelTableModel tableModel = (ArtikelTableModel) getModel();
		// ... Inhalt aktualisieren
		tableModel.setArtikel(artikels);
	}
}
