package bib.main.ui.CUI.gui.panels;

import bib.main.entities.Artikel;

import javax.swing.*;
import java.util.Collections;

// Wichtig: Das ArtikelListPanel _ist eine_ JList und damit eine Component;
// es kann daher in das Layout eines anderen Containers
// (in unserer Anwendung des Frames) eingefügt werden.
public class ArtikelListPanel extends JList<Artikel> {

	public ArtikelListPanel(java.util.List<Artikel> artikel) {
		super();

		// ListModel erzeugen ...
		DefaultListModel<Artikel> listModel = new DefaultListModel<>();
		// ... bei JList "anmelden" und ...
		setModel(listModel);
		// ... Daten an Model übergeben
		updateBooksList(artikel);
	}
	
	public void updateBooksList(java.util.List<Artikel> artikel) {

		// Sortierung (mit Lambda-Expression)
//		Collections.sort(buecher, (b1, b2) -> b1.getTitel().compareTo(b2.getTitel()));	// Sortierung nach Titel
		Collections.sort(artikel, (a1, a2) -> a1.getArtikelNr() - a2.getArtikelNr());	// Sortierung nach Nummer

		// ListModel von JList holen und ...
		DefaultListModel<Artikel> listModel = (DefaultListModel<Artikel>) getModel();
		// ... Inhalt aktualisieren
		listModel.clear();
		listModel.addAll(artikel);
	}
}
