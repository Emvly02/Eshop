package bib.main.ui.CUI.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ButtonPanelKundenmenue extends JPanel{
    private JButton btnArtikelSuche;
    private JButton btnArtikelInWarenkorb;
    private JButton btnWarenkorbBearbeiten;
    private JButton btnZurueck;

    private ActionListener buttonClickListener;

    public ButtonPanelKundenmenue(ActionListener listener) {
        setLayout(new BorderLayout());
        buttonClickListener = listener;
        initComponents();



    }

    private void initComponents() {
        setLayout(new GridLayout(5, 1));

        btnArtikelSuche = new JButton("Artikel suchen");
        btnArtikelSuche.addActionListener(buttonClickListener);
        add(btnArtikelSuche);

        btnArtikelInWarenkorb = new JButton("Einen Artikel in den Warenkorb legen");
        btnArtikelInWarenkorb.addActionListener(buttonClickListener);
        add(btnArtikelInWarenkorb);

        btnWarenkorbBearbeiten = new JButton("Warenkorb bearbeiten");
        btnWarenkorbBearbeiten.addActionListener(buttonClickListener);
        add(btnWarenkorbBearbeiten);

        btnZurueck = new JButton("Zurueck");
        btnZurueck.addActionListener(buttonClickListener);
        add(btnZurueck);

    }

    public JButton getBtnArtikelSuche() {
        return btnArtikelSuche;
    }

    public JButton getBtnArtikelInWarenkorb() {
        return btnArtikelInWarenkorb;
    }

    public JButton getBtnWarenkorbBearbeiten() {
        return btnWarenkorbBearbeiten;
    }

    public JButton getBtnZurueck() {
        return btnZurueck;
    }
    public void anzeigen() {
        setVisible(true);
    }
}
