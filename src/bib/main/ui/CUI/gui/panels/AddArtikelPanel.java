package bib.main.ui.CUI.gui.panels;

import bib.main.domain.Shop;
import bib.main.domain.exceptions.ArtikelExistiertSchonException;
import bib.main.entities.Artikel;

import javax.swing.*;
import java.awt.*;

// Wichtig: Das AddBookPanel _ist ein_ Panel und damit auch eine Component; 
// es kann daher in das Layout eines anderen Containers 
// (in unserer Anwendung des Frames) eingefügt werden.
public class AddArtikelPanel extends JPanel {

	// Über dieses Interface übermittelt das AddBookPanel
	// ein neu hinzugefügtes Buch an einen Empfänger.
	// In unserem Fall ist der Empfänger die BibGuiMitKomponenten,
	// die dieses Interface implementiert und auf ein neue hinzugefügtes
	// Buch reagiert, indem sie die Bücherliste aktualisiert.	
	public interface AddArtikelListener {
		public void onArtikelAdded(Artikel artikel);
	}

	
	private Shop eshop = null;
	private AddArtikelListener addArtikelListener = null;

	private JButton hinzufuegenButton;
	private JTextField artikelnummerTextFeld = null;
	private JTextField bezeichnungTextFeld = null;
	private JTextField preisTextFeld = null;

	public AddArtikelPanel(Shop eshop, AddArtikelListener addArtikelListener) {
		this.eshop = eshop;
		this.addArtikelListener = addArtikelListener;

		setupUI();

		setupEvents();
	}

	private void setupUI() {

		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		// Abstandhalter ("Filler") zwischen Rand und erstem Element
		Dimension borderMinSize = new Dimension(5, 10);
		Dimension borderPrefSize = new Dimension(5, 10);
		Dimension borderMaxSize = new Dimension(5, 10);
		Box.Filler filler = new Box.Filler(borderMinSize, borderPrefSize, borderMaxSize);
		add(filler);

		artikelnummerTextFeld = new JTextField();
		bezeichnungTextFeld = new JTextField();
		preisTextFeld = new JTextField();

		add(new JLabel("Artikelnummer:"));
		add(artikelnummerTextFeld);
		add(new JLabel("Bezeichnung:"));
		add(bezeichnungTextFeld);
		add(new JLabel("Preis:"));
		add(preisTextFeld);

		// Abstandhalter ("Filler") zwischen letztem Eingabefeld und Add-Button
		Dimension fillerMinSize = new Dimension(5, 20);
		Dimension fillerPrefSize = new Dimension(5, Short.MAX_VALUE);
		Dimension fillerMaxSize = new Dimension(5, Short.MAX_VALUE);
		filler = new Box.Filler(fillerMinSize, fillerPrefSize, fillerMaxSize);
		add(filler);

		hinzufuegenButton = new JButton("Hinzufuegen");
		add(hinzufuegenButton);

		// Abstandhalter ("Filler") zwischen letztem Element und Rand
		add(new Box.Filler(borderMinSize, borderPrefSize, borderMaxSize));

		// Rahmen definieren
		setBorder(BorderFactory.createTitledBorder("Einfügen"));
	}

	private void setupEvents() {
//		hinzufuegenButton.addActionListener(
//				new ActionListener() {
//					@Override
//					public void actionPerformed(ActionEvent ae) {
//						System.out.println("Event: " + ae.getActionCommand());
//						buchEinfügen();
//					}
//				});
		hinzufuegenButton.addActionListener(e -> buchEinfügen());
	}

	private void buchEinfügen() {
		String artikelnummer = artikelnummerTextFeld.getText();
		String bezeichnung = bezeichnungTextFeld.getText();
		String preis = preisTextFeld.getText();

		if (!artikelnummer.isEmpty() && !bezeichnung.isEmpty()) {
			try {
				int artikelnummerAlsInt = Integer.parseInt(artikelnummer);
				float preisAlsFloat = Float.parseFloat(preis);

					Artikel artikel = eshop.fuegeArtikelEin(bezeichnung, artikelnummerAlsInt, preisAlsFloat);
					artikelnummerTextFeld.setText("");
					bezeichnungTextFeld.setText("");

				// Am Ende Listener, d.h. unseren Frame benachrichtigen:
				addArtikelListener.onArtikelAdded(artikel);
			} catch (NumberFormatException nfe) {
				System.err.println("Bitte eine Zahl eingeben.");
			} catch (ArtikelExistiertSchonException a) {
				System.err.println(a.getMessage());
			} 
		}
	}
}
