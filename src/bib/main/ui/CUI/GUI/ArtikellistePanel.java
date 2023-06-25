package bib.main.ui.CUI.GUI;
import bib.main.entities.Artikel;
import bib.main.entities.MassengutArtikel;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.List;


public class ArtikellistePanel extends JPanel{
    private JTextArea textArea;
    private JFrame frame;

    public ArtikellistePanel(){
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


    public void displayArtikelliste(List<Artikel> artikelList) throws IOException {
        textArea.setText("");
        for (Artikel artikel : artikelList) {
            textArea.append(artikel.toString() + "\n");
        }
        frame.setVisible(true);
    }

    public void displayMassenArtikelliste(List<MassengutArtikel> mArtikelList) throws IOException {
        textArea.setText("");
        for (MassengutArtikel mArtikel : mArtikelList) {
            textArea.append(mArtikel.toString() + "\n");
        }
        frame.setVisible(true);
    }

    public void displayArtikellisten(List<Artikel> artikelListe, List<MassengutArtikel> massengutArtikelListe) throws IOException {
        textArea.setText(""); // Leert den Inhalt des Textfeldes

        textArea.append("Artikel:\n");
        for (Artikel artikel : artikelListe) {
            textArea.append(artikel.toString() + "\n");
        }

        textArea.append("\nMassengutartikel:\n");
        for (MassengutArtikel mArtikel : massengutArtikelListe) {
            textArea.append(mArtikel.toString() + "\n");
        }

        frame.setVisible(true);
    }

}
