package bib.main.ui.CUI.GUI;
import bib.main.entities.Artikel;
import bib.main.entities.MassengutArtikel;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class ArtikelSuchePanel extends JPanel{
    private JTextArea textArea;
    private JFrame frame;

    public ArtikelSuchePanel() {
        setLayout(new BorderLayout());

        frame = new JFrame("Artikelliste");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);
        frame.add(scrollPane);
    }

    public void displayArtikelSuche(List<Artikel> artikelListe, List<MassengutArtikel> massengutArtikelListe, String bezeichnung) throws IOException {
        textArea.setText(""); // Leert den Inhalt des Textfeldes

        textArea.append("Artikel:\n");
        for (Artikel artikel : artikelListe) {
            if(artikel.getBezeichnung().equals(bezeichnung)) {
                textArea.append(artikel.toString() + "\n");
            }
        }

        for (MassengutArtikel mArtikel : massengutArtikelListe) {
            if(mArtikel.getBezeichnung().equals(bezeichnung)) {
                textArea.append(mArtikel.toString() + "\n");
            }
        }

        frame.setVisible(true);
    }
}
