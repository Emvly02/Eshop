package bib.main.ui.CUI.gui;

import bib.main.domain.Shop;
import bib.main.entities.Artikel;
import bib.main.ui.CUI.gui.panels.AddArtikelPanel;
import bib.main.ui.CUI.gui.panels.ArtikelTablePanel;
import bib.main.ui.CUI.gui.panels.SearchArtikelPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

public class EshopGuiMitKomponenten extends JFrame
		implements AddArtikelPanel.AddArtikelListener,
		SearchArtikelPanel.SearchResultListener {

	private Shop eshop;

	private SearchArtikelPanel searchPanel;
	private AddArtikelPanel addPanel;
//	private BooksListPanel booksPanel;
	private ArtikelTablePanel artikelPanel;

	private JTextField nummerTextFeld = null;
	private JTextField titelTextFeld = null;
	private JTextField suchTextfeld = null;
	private JButton suchButton = null;
	private JButton hinzufuegenButton = null;
	private JList buecherListe = null;
	private JTable buecherTabelle = null;

	public EshopGuiMitKomponenten(String titel) {
		super(titel);

		try {
			eshop = new Shop("ESHOP");

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
		this.addWindowListener(new WindowCloser());

		// Layout des Frames: BorderLayout
		this.setLayout(new BorderLayout());

		// North
		searchPanel = new SearchArtikelPanel(eshop, this);

		// West
		addPanel = new AddArtikelPanel(eshop, this);

		// Center
		List<Artikel> artikel = eshop.gibAlleArtikel();
		// (wahlweise Anzeige als Liste oder Tabelle)
//		booksPanel = new BooksListPanel(buecher);
		artikelPanel = new ArtikelTablePanel(artikel);
		JScrollPane scrollPane = new JScrollPane(artikelPanel);
		scrollPane.setBorder(BorderFactory.createTitledBorder("Bücher"));

		// "Zusammenbau" in BorderLayout des Frames
		getContentPane().add(searchPanel, BorderLayout.NORTH);
		add(addPanel, BorderLayout.WEST);
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
				EshopGuiMitKomponenten gui = new EshopGuiMitKomponenten("Bibliothek");
			}
		});
		
//		// Start der Anwendung (per Lambda-Expression)
//		SwingUtilities.invokeLater(() -> { BibGuiAusVL gui = new BibGuiAusVL("Bibliothek"); });
	}

	/*
	 * (non-Javadoc)
	 *
	 * Listener, der Benachrichtungen erhält, wenn im AddBookPanel ein Buch eingefügt wurde.
	 * (Als Reaktion soll die Bücherliste aktualisiert werden.)
	 * @see bib.local.ui.gui.panels.AddBookPanel.AddBookListener#onBookAdded(bib.local.entities.Buch)
	 */
	@Override
	public void onArtikelAdded(Artikel artikel) {
		// Ich lade hier einfach alle Bücher neu und lasse sie anzeigen
		List<Artikel> artikels = eshop.gibAlleArtikel();
		artikelPanel.updateArtikelList(artikels);
	}

	/*
	 * (non-Javadoc)
	 *
	 * Listener, der Benachrichtungen erhält, wenn das SearchBooksPanel ein Suchergebnis bereitstellen möchte.
	 * (Als Reaktion soll die Bücherliste aktualisiert werden.)
	 * @see bib.local.ui.gui.swing.panels.SearchBooksPanel.SearchResultListener#onSearchResult(java.util.List)
	 */
	@Override
	public void onSearchResult(List<Artikel> artikels) {
		artikelPanel.updateArtikelList(artikels);
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
						eshop.schreibeArtikel();
					} catch (IOException ex) {
						throw new RuntimeException(ex);
					}
					break;
				case "Quit":
					// Nur "this" ginge nicht, weil "this" auf das FileMenu-Objekt zeigt.
					// "BibGuiAusVL.this" zeigt auf das dieses (innere) FileMenu-Objekt
					// umgebende Objekt der Klasse BibGuiAusVL.
					EshopGuiMitKomponenten.this.setVisible(false);
					EshopGuiMitKomponenten.this.dispose();
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