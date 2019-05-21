package UI.view;

import UI.controller.InlogSchermController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe die de view opzet van het inlog scherm.
 */
public class InlogSchermView extends JPanel{
    private InlogSchermController inlogSchermController;
    private String labelSysteemMelding;
    private JLabel lblSysteemMelding;
    private JTextField txtFieldGebruikersNaam;
    private int txtFieldWidth;
    private int passwordFieldWidth;
    private JPasswordField passwordField;
    private String buttonInloggen;
    private String buttonAnnuleer;
    private String labelGebruikersNaam;
    private String labelWachtWoord;

    /**
     * Constructor om deze view te creêeren.
     * @param inlogSchermController InlogschermController
     * @param labelSysteemMelding String
     * @param txtFieldWidth int
     * @param passwordFieldWidth int
     * @param buttonInloggen String
     * @param buttonAnnuleer String
     * @param labelGebruikersNaam String
     * @param labelWachtWoord String
     */
    public InlogSchermView(InlogSchermController inlogSchermController, String labelSysteemMelding,
                           int txtFieldWidth, int passwordFieldWidth, String buttonInloggen, String buttonAnnuleer,
                           String labelGebruikersNaam, String labelWachtWoord) {
        this.inlogSchermController = inlogSchermController;
        this.labelSysteemMelding = labelSysteemMelding;
        this.txtFieldWidth = txtFieldWidth;
        this.passwordFieldWidth = passwordFieldWidth;
        this.buttonInloggen = buttonInloggen;
        this.buttonAnnuleer = buttonAnnuleer;
        this.labelGebruikersNaam = labelGebruikersNaam;
        this.labelWachtWoord = labelWachtWoord;
    }

    /**
     * Methode om een panel te creëren voor de GebruikersBeheerView.
     * @return Jpanel
     */
    public JPanel createInlogPanel() {
        JLabel lblGebruikersNaam= new JLabel(labelGebruikersNaam);
        JLabel lblWachtWoord = new JLabel(labelWachtWoord);
        lblSysteemMelding = new JLabel(labelSysteemMelding);
        lblSysteemMelding.setForeground(Color.red);
        txtFieldGebruikersNaam = new JTextField(txtFieldWidth);
        passwordField = new JPasswordField(passwordFieldWidth);
        ActionListener listener = new Clicklistener();
        JButton btnInloggen = new JButton(buttonInloggen);
        JButton btnAnnuleer = new JButton(buttonAnnuleer);
        btnInloggen.addActionListener(listener);
        btnAnnuleer.addActionListener(listener);
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(10,0,0,0);
        this.add(lblGebruikersNaam, c);
        c.gridx = 1;
        c.gridy = 0;
        c.insets = new Insets(10,10,0,0);
        this.add(txtFieldGebruikersNaam, c);
        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(10,0,0,0);
        this.add(lblWachtWoord, c);
        c.gridx = 1;
        c.gridy = 1;
        c.insets = new Insets(10,10,0,0);
        this.add(passwordField, c);
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 2;
        c.insets = new Insets(0,0,0,0);
        this.add(lblSysteemMelding, c);
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 1;
        c.insets = new Insets(10,0,0,0);
        this.add(btnInloggen, c);
        c.gridx = 1;
        c.gridy = 3;
        c.insets = new Insets(10,10,0,0);
        this.add(btnAnnuleer, c);
        return this;
    }

    /**
     * Actionlistener voor het opvangen van clicks op menu buttons.
     */
    class Clicklistener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals(buttonInloggen)) {
                lblSysteemMelding.setText(inlogSchermController.getLblSysteemMelding());
                inlogSchermController.checkLogin(txtFieldGebruikersNaam.getText(),passwordField.getPassword());
            } else if (e.getActionCommand().equals(buttonAnnuleer)) {
                inlogSchermController.systemClose();
            }
        }
    }

    /**
     * Methode om een systeemmelding te creēeren.
     * @param text String
     */
    public void setLblSysteemMelding(String text) {
        this.lblSysteemMelding.setText(text);
    }

    /**
     * Methode om het gebruikersnaam textfield te wissen.
     */
    public void eraseTextfield() {txtFieldGebruikersNaam.setText(inlogSchermController.getLblSysteemMelding());}

    /**
     * Methode om het wachtwoord veld te wissen.
     */
    public void erasePasswordField() {passwordField.setText(inlogSchermController.getLblSysteemMelding());}

    /**
     * Methode om de cursor te plaatsen in het gebruikersnaam textfield te plaatsen.
     */
    public void setFocus() {txtFieldGebruikersNaam.grabFocus();}

}
