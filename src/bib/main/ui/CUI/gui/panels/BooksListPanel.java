package bib.main.ui.CUI.gui.panels;

import bib.local.entities.Buch;

import javax.swing.*;
import java.util.Collections;

// Wichtig: Das BooksListPanel _ist eine_ JList und damit eine Component;
// es kann daher in das Layout eines anderen Containers
// (in unserer Anwendung des Frames) eingefügt werden.
public class BooksListPanel extends JList<Buch> {

	public BooksListPanel(java.util.List<Buch> buecher) {
		super();

		// ListModel erzeugen ...
		DefaultListModel<Buch> listModel = new DefaultListModel<>();
		// ... bei JList "anmelden" und ...
		setModel(listModel);
		// ... Daten an Model übergeben
		updateBooksList(buecher);
	}
	
	public void updateBooksList(java.util.List<Buch> buecher) {

		// Sortierung (mit Lambda-Expression)
//		Collections.sort(buecher, (b1, b2) -> b1.getTitel().compareTo(b2.getTitel()));	// Sortierung nach Titel
		Collections.sort(buecher, (b1, b2) -> b1.getNummer() - b2.getNummer());	// Sortierung nach Nummer

		// ListModel von JList holen und ...
		DefaultListModel<Buch> listModel = (DefaultListModel<Buch>) getModel();
		// ... Inhalt aktualisieren
		listModel.clear();
		listModel.addAll(buecher);
	}
}
