package bib.main.ui.CUI.gui;

import bib.local.domain.Bibliothek;
import bib.local.domain.exceptions.BuchExistiertBereitsException;
import bib.local.entities.Buch;
import bib.local.ui.cui.BibClientCUI;
import bib.local.ui.gui.models.BuecherTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;

public class BibGuiAusVL extends JFrame {

	private Bibliothek bib;

	private JTextField nummerTextFeld = null;
	private JTextField titelTextFeld = null;
	private JTextField suchTextfeld = null;
	private JButton suchButton = null;
	private JButton hinzufuegenButton = null;
	private JList buecherListe = null;
	private JTable buecherTabelle = null;

	public BibGuiAusVL(String titel) {
		super(titel);

		try {
			bib = new Bibliothek("BIB");

//			// Code für Umschaltung des Look-and-Feels:
//			// (Einfach mal ausprobieren!)
//			try {
//				UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
////				UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
////				UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
//				SwingUtilities.updateComponentTreeUI(this);
//			} catch (ClassNotFoundException e) {
//				e.printStackTrace();
//			} catch (InstantiationException e) {
//				e.printStackTrace();
//			} catch (IllegalAccessException e) {
//				e.printStackTrace();
//			} catch (UnsupportedLookAndFeelException e) {
//				e.printStackTrace();
//			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		initialize();
	}

	private void initialize() {

		// Menü initialisieren
		setupMenu();

		// Klick auf Kreuz / roten Kreis (Fenster schließen) behandeln lassen:
		// A) Mittels Default Close Operation
//		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		// B) Mittels WindowAdapter (für Sicherheitsabfrage)
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowCloser());

		// Layout des JFrames: BorderLayout
		setLayout(new BorderLayout());

		//
		// NORTH
		//

		// Version 1: Einfach Variante mit FlowLayout aus Vorlesung
//		JPanel suchPanel = new JPanel();
//		suchPanel.setLayout(new FlowLayout());
//		JLabel suchLabel = new JLabel("Suchbegriff: ");
//		JTextField suchTextFeld = new JTextField();
//		suchTextFeld.setPreferredSize(new Dimension(300, 20));
//		JButton suchButton = new JButton("Such!");
//		suchPanel.add(suchLabel);
//		suchPanel.add(suchTextFeld);
//		suchPanel.add(suchButton);

		// Version 2: Komplexere Variante mit GridBagLayout
		// (Hinweis: Das ist schon ein komplexerer LayoutManager, der mehr kann als hier gezeigt.
		//  Hervorzuheben ist hier die Idee, explizit Constraints (also Nebenbedingungen) für
		//  die Positionierung / Ausrichtung / Größe von GUI-Komponenten anzugeben.)
		JPanel suchPanel = new JPanel();
		suchPanel.setBorder(BorderFactory.createTitledBorder("Suche"));

		GridBagLayout gridBagLayout = new GridBagLayout();
		suchPanel.setLayout(gridBagLayout);
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.WEST;	// "Westliche" (linksbündige) Ausrichtung
		c.fill = GridBagConstraints.HORIZONTAL; // Horizontal füllen
		c.gridy = 0;	// Zeile 0

		JLabel suchLabel = new JLabel("Suchbegriff:");
		c.gridx = 0;	// Spalte 0
		c.weightx = 0.2;	// 20% der gesamten Breite
		gridBagLayout.setConstraints(suchLabel, c);
		suchPanel.add(suchLabel);

		suchTextfeld = new JTextField();
		suchTextfeld.setToolTipText("Hier Suchbegriff eingeben.");
		c.gridx = 1;	// Spalte 1
		c.weightx = 0.6;	// 60% der gesamten Breite
		gridBagLayout.setConstraints(suchTextfeld, c);
		suchPanel.add(suchTextfeld);

		suchButton = new JButton("Such!");

		c.gridx = 2;	// Spalte 2
		c.weightx = 0.2;	// 20% der gesamten Breite
		gridBagLayout.setConstraints(suchButton, c);
		suchPanel.add(suchButton);

		suchButton.addActionListener(new SuchActionListener());


		//
		// CENTER: nur eine Liste/Tabelle, die mit dem Inhalt einer util-List gefüllt wird
		//

		// Alternative 1: Swing-Liste
		List<Buch> buecher = bib.gibAlleBuecher();
			// Hinweis: Es gibt die List-Schnittstelle aus dem Paket java.util
			//          und die List-Klasse aus dem Paket java.awt.
			//          Da die IDE bereits das gesamte AWT-Paket importiert hat
			//          (siehe import-Anweisung oben), spreche ich hier die
			//          List-Schnittstelle explizit durch Angabe des util-Pakets an.
		// A) Mit ListModel
//		// ListModel als "Datencontainer" anlegen:
//		DefaultListModel<Buch> listModel = new DefaultListModel<>();
//		listModel.addAll(buecher);
//		// JList mit ListModel initialisieren
//		buecherListe = new JList(listModel);

//		// B) (Bisherige) Variante ohne ListModel
//		buecherListe = new JList<Buch>(new Vector<>(buecher));

//		// Alternative 2: Swing-Tabelle
//		// Eigenes TableModel als "Datencontainer" anlegen:
		BuecherTableModel tableModel = new BuecherTableModel(buecher);
//		// JList mit ListModel initialisieren
		buecherTabelle = new JTable(tableModel);

//		JScrollPane scrollPane = new JScrollPane(buecherListe);
		JScrollPane scrollPane = new JScrollPane(buecherTabelle);
		scrollPane.setBorder(BorderFactory.createTitledBorder("Bücher"));


		//
		// WEST: BoxLayout (vertikal)
		//

		JPanel einfuegePanel = new JPanel();
		einfuegePanel.setBorder(BorderFactory.createTitledBorder("Einfügen"));
		einfuegePanel.setLayout(new BoxLayout(einfuegePanel, BoxLayout.PAGE_AXIS));

		// Abstandhalter ("Filler") zwischen Rand und erstem Element
		Dimension borderMinSize = new Dimension(5, 10);
		Dimension borderPrefSize = new Dimension(5, 10);
		Dimension borderMaxSize = new Dimension(5, 10);
		einfuegePanel.add(new Box.Filler(borderMinSize, borderPrefSize, borderMaxSize));

		nummerTextFeld = new JTextField();
		titelTextFeld = new JTextField();
		einfuegePanel.add(new JLabel("Nummer:"));
		einfuegePanel.add(nummerTextFeld);
		einfuegePanel.add(new JLabel("Titel:"));
		einfuegePanel.add(titelTextFeld);

		// Abstandhalter ("Filler") zwischen letztem Eingabefeld und Add-Button
		Dimension fillerMinSize = new Dimension(5, 20);
		Dimension fillerPrefSize = new Dimension(5, Short.MAX_VALUE);
		Dimension fillerMaxSize = new Dimension(5, Short.MAX_VALUE);
		Box.Filler filler = new Box.Filler(fillerMinSize, fillerPrefSize, fillerMaxSize);
		einfuegePanel.add(filler);

		hinzufuegenButton = new JButton("Hinzufuegen");

		// Verhalten des Hinzufügen-Buttons
		// 1.) ActionListener über anonyme Klasse
//		hinzufuegenButton.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				fuegeBuchEin();
//			}
//		});
		// 2.) ActionListener über Lambda Expression
		hinzufuegenButton.addActionListener(e -> fuegeBuchEin());

		// Alternative: Eigene Klasse AlternativeAddBookListener als ActionListener
		//	Hier müssen relevante GUI-Komponenten im Konstruktor übergeben werden.
		//	Dazu müssen alle GUI-Komponenten wie z.B. die JList bereits initialisiert sein.
//		hinzufuegenButton.addActionListener(
//				new AlternativeAddBookListener(bib, nummerTextFeld, titelTextFeld, buecherListe));

		einfuegePanel.add(hinzufuegenButton);

		// Abstandhalter ("Filler") zwischen letztem Element und Rand
		einfuegePanel.add(new Box.Filler(borderMinSize, borderPrefSize, borderMaxSize));


		// Kombination der drei Panels im JFrame:
		getContentPane().add(suchPanel, BorderLayout.NORTH);
		add(einfuegePanel, BorderLayout.WEST);
		add(scrollPane, BorderLayout.CENTER);
		// Hinweis zu ContentPane oben:
		// Komponenten müssen in Swing der ContentPane hinzugefügt werden (siehe oben).
		// this.add() oder add() können aber auch direkt auf einem Container-Objekt
		// aufgerufen werden. Die Komponenten werden dann per Default der ContentPane
		// hinzugefügt.

		this.setSize(640, 480);
		this.setVisible(true);
	}


	public static void main(String[] args) {
		// Start der Anwendung (per anonymer Klasse)
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				BibGuiAusVL gui = new BibGuiAusVL("Bibliothek");
			}
		});
		
//		// Start der Anwendung (per Lambda-Expression)
//		SwingUtilities.invokeLater(() -> { BibGuiAusVL gui = new BibGuiAusVL("Bibliothek"); });
	}

	private void fuegeBuchEin() {
		try {
			int nummer = Integer.parseInt(nummerTextFeld.getText());
			nummerTextFeld.setText("");
			String titel = titelTextFeld.getText();
			titelTextFeld.setText("");
			bib.fuegeBuchEin(titel, nummer);
			List<Buch> buecher = bib.gibAlleBuecher();
			aktualisiereBuecherListe(buecher);
		} catch (BuchExistiertBereitsException | NumberFormatException ex) {
			System.err.println("Fehler: " + ex.getMessage());
		}
	}

	private void aktualisiereBuecherListe(List<Buch> buecher) {
		// Sortierung per anonymer Klasse
//		Collections.sort(liste, new Comparator<Buch>() {
//			@Override
//			public int compare(Buch buch1, Buch buch2) {
//				return buch1.getNummer()-buch2.getNummer();
//			}
//		});
		// Sortierung nach Nummer per Lambda Expression:
		Collections.sort(buecher, (buch1, buch2) -> buch1.getNummer()-buch2.getNummer());
//		// Sortierung per Methodenreferenz:
//		Collections.sort(liste, Comparator.comparingInt(Buch::getNummer));

		// Sortierung nach Titel per Lambda Expression:
//		Collections.sort(liste, (buch1, buch2) -> buch1.getTitel().compareTo(buch2.getTitel()));
		// Methodenreferenz:
//		Collections.sort(liste, Comparator.comparing(Buch::getTitel));

		// Darstellung der Daten:

		// Alternative 1: Swing-Liste

		// A) Mit ListModel:
		//    JList aktualisieren heißt, das ListModel zu aktualisieren
//		DefaultListModel<Buch> listModel = (DefaultListModel<Buch>) buecherListe.getModel();
//		listModel.removeAllElements();
//		listModel.addAll(buecher);

//		// B) Ohne ListModel
//		buecherListe.setListData(new Vector(buecher));

//		// Alternative 2: Swing-Tabelle
//		//		JTable aktualisieren heißt, das TableModel zu aktualisieren
		BuecherTableModel tableModel = (BuecherTableModel) buecherTabelle.getModel();
		tableModel.setBooks(buecher);
	}


	/*
	 * (non-Javadoc)
	 *
	 * Mitgliedsklasse für Such-Button
	 *
	 */	public class SuchActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(suchButton)) {
				String suchBegriff = suchTextfeld.getText();
				List<Buch> suchErgebnis;
				if (suchBegriff.isEmpty()) {
					suchErgebnis = bib.gibAlleBuecher();
				} else {
					suchErgebnis = bib.sucheNachTitel(suchBegriff);
				}
				aktualisiereBuecherListe(suchErgebnis);
			}
		}
	}

	private void setupMenu() {
		// Menüleiste anlegen ...
		JMenuBar mBar = new JMenuBar();

		JMenu fileMenu = new FileMenu();
		mBar.add(fileMenu);

		JMenu helpMenu = new HelpMenu();
		mBar.add(helpMenu);

		// ... und beim Fenster anmelden
		this.setJMenuBar(mBar);
	}

	/*
	 * (non-Javadoc)
	 *
	 * Mitgliedsklasse für File-Menü
	 *
	 */
	class FileMenu extends JMenu implements ActionListener {

		public FileMenu() {
			super("File");

			JMenuItem saveItem = new JMenuItem("Save");
			saveItem.addActionListener(this);
			add(saveItem);

			addSeparator();

			JMenuItem quitItem = new JMenuItem("Quit");
			quitItem.addActionListener(this);
			add(quitItem);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Klick auf MenuItem " + e.getActionCommand());

			switch (e.getActionCommand()) {
				case "Save":
					try {
						bib.schreibeBuecher();
					} catch (IOException ex) {
						throw new RuntimeException(ex);
					}
					break;
				case "Quit":
					// Nur "this" ginge nicht, weil "this" auf das FileMenu-Objekt zeigt.
					// "BibGuiAusVL.this" zeigt auf das dieses (innere) FileMenu-Objekt
					// umgebende Objekt der Klasse BibGuiAusVL.
					BibGuiAusVL.this.setVisible(false);
					BibGuiAusVL.this.dispose();
					System.exit(0);

			}
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * Mitgliedsklasse für Help-Menü
	 *
	 */
	class HelpMenu extends JMenu implements ActionListener {

		public HelpMenu() {
			super("Help");

			// Nur zu Testzwecken: Menü mit Untermenü
			JMenu m = new JMenu("About");
			JMenuItem mi = new JMenuItem("Programmers");
			mi.addActionListener(this);
			m.add(mi);
			mi = new JMenuItem("Stuff");
			mi.addActionListener(this);
			m.add(mi);
			this.add(m);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Klick auf Menü '" + e.getActionCommand() + "'.");
		}
	}

}
