package bib.main.ui.CUI.MyGUI;
import bib.main.entities.Artikel;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.List;


public class ArtikellistePanel extends JPanel{
    private JTextArea textArea;

    public ArtikellistePanel(){
        setLayout(new BorderLayout());

        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);
    }


    public void displayArtikelliste(List<Artikel> artikelList) throws IOException {
        textArea.setText("");
        for (Artikel article : artikelList) {
            textArea.append(article.toString() + "\n");
        }
    }
}
