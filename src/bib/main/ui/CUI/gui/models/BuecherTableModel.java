package bib.main.ui.CUI.gui.models;

import bib.local.entities.Buch;

import javax.swing.table.AbstractTableModel;
import java.util.List;
import java.util.Vector;

public class BuecherTableModel extends AbstractTableModel  {

    private List<Buch> buecher;
    private String[] spaltenNamen = { "Nummer","Titel", "verfügbar" };

    
    public BuecherTableModel(List<Buch> aktuelleBuecher) {
    	super(); 
    	// Ich erstelle eine Kopie der Bücherliste,
    	// damit beim Aktualisieren (siehe Methode setBooks())
    	// keine unerwarteten Seiteneffekte entstehen.
    	buecher = new Vector<Buch>();
    	buecher.addAll(aktuelleBuecher);
    }

    public void setBooks(List<Buch> aktuelleBuecher){
        buecher.clear();
        buecher.addAll(aktuelleBuecher);
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
        return buecher.size();
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
        Buch gewaehltesBuch = buecher.get(row);
        switch (col) {
            case 0:
                return gewaehltesBuch.getNummer();
            case 1:
                return gewaehltesBuch.getTitel();
            case 2:
                return gewaehltesBuch.isVerfuegbar();
            default:
                return null;
        }
    }
}
