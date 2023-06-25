package bib.main.ui.CUI.MyGUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ButtonPanel extends JPanel {
    private JButton btnRegistrieren;
    private JButton btnLogin;
    private JButton btnArtikelliste;
    private JButton btnExit;



    private ActionListener buttonClickListener;

    public ButtonPanel(ActionListener listener) {
        buttonClickListener = listener;
        initComponents();
    }

    private void initComponents() {
        setLayout(new GridLayout(5, 1));

        btnArtikelliste = new JButton("Alle Artikel");
        btnArtikelliste.addActionListener(buttonClickListener);
        add(btnArtikelliste);

        btnRegistrieren = new JButton("Registrieren");
        btnRegistrieren.addActionListener(buttonClickListener);
        add(btnRegistrieren);

        btnLogin = new JButton("Login Kunde");
        btnLogin.addActionListener(buttonClickListener);
        add(btnLogin);

        btnExit = new JButton("Exit");
        btnExit.addActionListener(buttonClickListener);
        add(btnExit);
    }

    public JButton getBtnRegistrieren() {
        return btnRegistrieren;
    }

    public JButton getBtnLogin() {
        return btnLogin;
    }

    public JButton getBtnArtikelliste() {
        return btnArtikelliste;
    }

    public JButton getBtnExit() {
        return btnExit;
    }
}
