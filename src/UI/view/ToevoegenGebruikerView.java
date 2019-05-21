package UI.view;

import UI.controller.ToevoegenGebruikerController;
import logic.Administrator;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe die de view opzet van het toevoegenGebruiker scherm.
 */
public class ToevoegenGebruikerView extends JPanel {
    private ToevoegenGebruikerController toevoegenGebruikerController;
    private int clmVoornaam;
    private JTextField txtVoornaam;
    private String textVoornaam;
    private int clmAchternaam;
    private JTextField txtAchternaam;
    private String textAchternaam;
    private int clmGebruikersNaam;
    private JTextField txtGebruikersNaam;
    private String textGebruikersNaam;
    private int clmPassword;
    private JTextField txtPassword;
    private String textPassword;
    private String lablMedewerkerSoorten;
    private JLabel lblSysteemMelding;
    private String labelSysteemMelding;
    private JComboBox cmbMedewerker;
    private JLabel lblVerplichteVelden;
    private String labelVerplichteVelden;
    private String[] medewerkerSoorten;
    private String buttonNieuweGebruiker;
    private String buttonGebruikersBeheer;

    /**
     * Constructor om deze view te creêeren.
     * @param toevoegenGebruikerController ToevoegenGebruikerController
     * @param medewerkerSoorten String[]
     * @param lblVerplichteVelden String
     * @param clmVoornaam int
     * @param txtVoornaam String
     * @param clmAchternaam int
     * @param txtAchternaam String
     * @param clmGebruikersNaam int
     * @param txtGebruikersNaam String
     * @param clmPassword int
     * @param txtPassword String
     * @param lblMedewerkerSoorten String
     * @param lblSysteemMelding String
     * @param btnNieuweGebruiker String
     * @param btnGebruikerBeheer String
     */
    public ToevoegenGebruikerView(ToevoegenGebruikerController toevoegenGebruikerController, String[] medewerkerSoorten,
                                  String lblVerplichteVelden, int clmVoornaam, String txtVoornaam, int clmAchternaam, String txtAchternaam,
                                  int clmGebruikersNaam, String txtGebruikersNaam, int clmPassword, String txtPassword, String lblMedewerkerSoorten,
                                  String lblSysteemMelding, String btnNieuweGebruiker, String btnGebruikerBeheer) {
        this.toevoegenGebruikerController = toevoegenGebruikerController;
        this.medewerkerSoorten = medewerkerSoorten;
        this.labelVerplichteVelden = lblVerplichteVelden;
        this.clmVoornaam = clmVoornaam;
        this.textVoornaam = txtVoornaam;
        this.clmAchternaam = clmAchternaam;
        this.textAchternaam = txtAchternaam;
        this.clmGebruikersNaam = clmGebruikersNaam;
        this.textGebruikersNaam = txtGebruikersNaam;
        this.clmPassword = clmPassword;
        this.textPassword = txtPassword;
        this.lablMedewerkerSoorten = lblMedewerkerSoorten;
        this.labelSysteemMelding = lblSysteemMelding;
        this.buttonNieuweGebruiker = btnNieuweGebruiker;
        this.buttonGebruikersBeheer = btnGebruikerBeheer;
    }

    /**
     * Methode om een panel te creëren voor de MenuView.
     * @return JPanel
     */
    public JPanel createToevoegenGebruikerPanel() {

        ClickListener listener = new ClickListener();
        lblVerplichteVelden = new JLabel(labelVerplichteVelden);
        txtVoornaam = new JTextField(clmVoornaam);
        JLabel lblVoornaam = new JLabel(textVoornaam);
        txtAchternaam = new JTextField(clmAchternaam);
        JLabel lblAchternaam = new JLabel(textAchternaam);
        txtGebruikersNaam = new JTextField(clmGebruikersNaam);
        JLabel lblGebruikersnaam = new JLabel(textGebruikersNaam);
        txtPassword = new JTextField(clmPassword);
        JLabel lblPassword = new JLabel(textPassword);
        cmbMedewerker = new JComboBox<>(medewerkerSoorten);
        cmbMedewerker.addActionListener(listener);
        JLabel lblMedwerkerSoort = new JLabel(lablMedewerkerSoorten);
        lblSysteemMelding = new JLabel(labelSysteemMelding);

        JButton btnNieuweGebruiker = new JButton(buttonNieuweGebruiker);
        btnNieuweGebruiker.addActionListener(listener);
        JButton btnGebruikersBeheer = new JButton(buttonGebruikersBeheer);
        btnGebruikersBeheer.addActionListener(listener);

        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        add(lblVerplichteVelden,c);
        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(10,0,0,0);
        add(lblVoornaam,c);
        c.gridx = 1;
        c.gridy = 1;
        c.insets = new Insets(10,10,0,0);
        add(txtVoornaam,c);
        c.gridx = 0;
        c.gridy = 2;
        c.insets = new Insets(10,0,0,0);
        add(lblAchternaam,c);
        c.gridx = 1;
        c.gridy = 2;
        c.insets = new Insets(10,10,0,0);
        add(txtAchternaam,c);
        c.gridx = 0;
        c.gridy = 3;
        c.insets = new Insets(10,0,0,0);
        add(lblGebruikersnaam,c);
        c.gridx = 1;
        c.gridy = 3;
        c.insets = new Insets(10,10,0,0);
        add(txtGebruikersNaam,c);
        c.gridx = 0;
        c.gridy = 4;
        c.insets = new Insets(10,0,0,0);
        add(lblPassword,c);
        c.gridx = 1;
        c.gridy = 4;
        c.insets = new Insets(10,10,0,0);
        add(txtPassword,c);
        c.gridx = 0;
        c.gridy = 5;
        c.insets = new Insets(10,0,0,0);
        add(lblMedwerkerSoort,c);
        c.gridx = 1;
        c.gridy = 5;
        c.insets = new Insets(10,10,0,0);
        add(cmbMedewerker,c);
        c.gridx = 1;
        c.gridy = 6;
        c.insets = new Insets(10,10,10,0);
        add(btnNieuweGebruiker,c);
        c.gridx = 0;
        c.gridy = 6;
        c.insets = new Insets(10,10,0,0);
        add(lblSysteemMelding,c);
        c.gridx = 1;
        c.gridy = 7;
        c.insets = new Insets(10,10,0,0);
        add(btnGebruikersBeheer,c);
        txtVoornaam.grabFocus();
        return this;
    }

    /**
     * Methode om een systeemmelding te creēeren.
     * @param labelSysteemMelding String
     */
    public void setLabelSysteemMelding(String labelSysteemMelding) {
        this.lblSysteemMelding.setText(labelSysteemMelding);
        txtVoornaam.setText("");
        txtVoornaam.grabFocus();
        txtAchternaam.setText("");
        txtGebruikersNaam.setText("");
        txtPassword.setText("");
    }

    /**
     * Methode om de tekstkleur van een systeemmelding in te stellen.
     * @param color Color
     */
    public void setLabelSysteemMeldingColor(Color color) {
        this.lblSysteemMelding.setForeground(color);
    }

    /**
     * Actionlistener voor het opvangen van clicks op menu buttons.
     */
    class ClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()) {
                case "Gebruiker opslaan":
                    checkValues();
                    break;
                case "Gebruikers beheer":
                    toevoegenGebruikerController.hideToevoegenGebruikerPanel();
                    toevoegenGebruikerController.showGebruikersBeheer();
                    break;
            }
        }
    }

    /**
     * Methode om te controleren of de verplichte velden allemaal ingevuld zijn.
     */
    private void checkValues() {
        if (txtVoornaam.getText().equals("") || txtAchternaam.getText().equals("") ||
                txtGebruikersNaam.getText().equals("") || txtPassword.getText().equals("")) {
            lblVerplichteVelden.setForeground(Color.red);
        } else if (!toevoegenGebruikerController.checkUserName(txtGebruikersNaam.getText())) {
            int toegang;
            if (cmbMedewerker.getSelectedItem().toString().equals(medewerkerSoorten[0])) {
                toegang = 4;
            } else {
                toegang = 3;
            }
            toevoegenGebruikerController.toevoegenGebruiker(new Administrator(
                    txtGebruikersNaam.getText(),
                    txtVoornaam.getText(),
                    txtAchternaam.getText(),
                    toegang,
                    txtPassword.getText(),
                    null,
                    0));
        }
    }
}
