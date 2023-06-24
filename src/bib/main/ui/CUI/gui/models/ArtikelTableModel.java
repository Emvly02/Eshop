package bib.main.ui.CUI.gui.models;

import bib.main.entities.Artikel;

import javax.swing.table.AbstractTableModel;
import java.util.List;
import java.util.Vector;

public class ArtikelTableModel extends AbstractTableModel  {

    private List<Artikel> artikel;
    private String[] spaltenNamen = { "Artikelnummer","Bezeichnung", "verfügbar" };

    
    public ArtikelTableModel(List<Artikel> aktuelleArtikel) {
    	super(); 
    	// Ich erstelle eine Kopie der Bücherliste,
    	// damit beim Aktualisieren (siehe Methode setBooks())
    	// keine unerwarteten Seiteneffekte entstehen.
    	artikel = new Vector<Artikel>();
    	artikel.addAll(aktuelleArtikel);
    }

    public void setArtikel(List<Artikel> aktuelleArtikel){
        artikel.clear();
        artikel.addAll(aktuelleArtikel);
        fireTableDataChanged();
    }

    /*
     * Ab hier überschriebene Methoden mit Informationen, 
     * die eine JTable von jedem TableModel erwartet:
     * - Anzahl der Zeilen
     * - Anzahl der Spalten
     * - Benennung der Spalten
     * - Wert einer Zelle in Zeile / Spalte
     */
    
    @Override
    public int getRowCount() {
        return artikel.size();
    }

    @Override
    public int getColumnCount() {
        return spaltenNamen.length;
    }

    @Override
    public String getColumnName(int col) {
        return spaltenNamen[col];
    }
    
    @Override
    public Object getValueAt(int row, int col) {
        Artikel gewaehlterArtikel = artikel.get(row);
        switch (col) {
            case 0:
                return gewaehlterArtikel.getArtikelNr();
            case 1:
                return gewaehlterArtikel.getBezeichnung();
            case 2:
                return gewaehlterArtikel.istVerfuegbar();
            default:
                return null;
        }
    }
}
