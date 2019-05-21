package UI.view;

import UI.controller.MenuController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe die de view opzet van het menu scherm.
 */
public class MenuView extends JPanel {
    private MenuController menuController;
    private String buttonImporteer;
    private String buttonNieuwProduct;
    private String buttonGebruikersBeheer;
    private String buttonNieuweKlant;
    private String buttonDigitaleOrder;
    private String buttonHandmatigeOrder;
    private String buttonRapportages;
    private String buttonUitloggen;

    /**
     * Constructor om deze view te creêeren.
     * @param menuController MenuController
     * @param btnImporteer String
     * @param buttonNieuwProduct String
     * @param buttonGebruikersBeheer String
     * @param buttonNieuweKlant String
     * @param buttonDigitaleOrder String
     * @param buttonHandmatigeOrder String
     * @param buttonRapportages String
     * @param buttonUitloggen String
     */
    public MenuView(MenuController menuController, String btnImporteer, String buttonNieuwProduct, String buttonGebruikersBeheer,
        String buttonNieuweKlant, String buttonDigitaleOrder, String buttonHandmatigeOrder, String buttonRapportages,
                    String buttonUitloggen) {
        this.menuController = menuController;
        this.buttonImporteer = btnImporteer;
        this.buttonNieuwProduct = buttonNieuwProduct;
        this.buttonGebruikersBeheer = buttonGebruikersBeheer;
        this.buttonNieuweKlant = buttonNieuweKlant;
        this.buttonDigitaleOrder = buttonDigitaleOrder;
        this.buttonHandmatigeOrder = buttonHandmatigeOrder;
        this.buttonRapportages = buttonRapportages;
        this.buttonUitloggen = buttonUitloggen;
    }

    /**
     * Methode om een panel te creëren voor de MenuView.
     * @return JPanel
     */
    public JPanel createMenuPanel() {
        ClickListener listener = new ClickListener();
        JButton btnImporteer = new JButton(buttonImporteer);
        btnImporteer.addActionListener(listener);
        JButton btnNweProduct = new JButton(buttonNieuwProduct);
        btnNweProduct.addActionListener(listener);
        JButton btnGebruikersBeheer = new JButton(buttonGebruikersBeheer);
        btnGebruikersBeheer.addActionListener(listener);
        JButton btnNweKlant = new JButton(buttonNieuweKlant);
        btnNweKlant.addActionListener(listener);
        JButton btnDigitaleOrder = new JButton(buttonDigitaleOrder);
        btnDigitaleOrder.addActionListener(listener);
        JButton btnHandmatigeOrder = new JButton(buttonHandmatigeOrder);
        btnHandmatigeOrder.addActionListener(listener);
        JButton btnRapportages = new JButton(buttonRapportages);
        btnRapportages.addActionListener(listener);
        JButton btnUitloggen = new JButton(buttonUitloggen);
        btnUitloggen.addActionListener(listener);


        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        // Afhanelijk van de gebruiker die inlogt de bijbehorende menubuttons tonen.
        if (menuController.medewerkerToegangsNiveau() == 3) {
            c.gridy = 0;
            c.insets = new Insets(0,0,10,0);
            add(btnImporteer, c);
            c.gridy = 1;
            c.insets = new Insets(0,0,10,0);
            add(btnNweProduct, c);
            c.gridy = 2;
            c.insets = new Insets(0,0,0,0);
            add(btnGebruikersBeheer, c);
            c.gridy = 3;
            c.insets = new Insets(30,0,0,0);
            add(btnUitloggen, c);
        } else {
            c.gridy = 0;
            c.insets = new Insets(0,0,10,0);
            add(btnNweKlant, c);
            c.gridy = 1;
            c.insets = new Insets(0,0,10,0);
            add(btnDigitaleOrder, c);
            c.gridy = 2;
            c.insets = new Insets(0,0,10,0);
            add(btnHandmatigeOrder, c);
            c.gridy = 3;
            c.insets = new Insets(0,0,10,0);
            add(btnRapportages, c);
            c.gridy = 4;
            c.insets = new Insets(30,0,0,0);
            add(btnUitloggen, c);
        }
        return this;
    }

    /**
     * Methode om een pop up scherm te tonen met een mededeling aan de gebruiker.
     */
    private void showMessage() {
        JOptionPane.showMessageDialog(null,
                "Deze menu optie hoort niet bij het aantonen van leerdoelen voor dit semester.\n" +
                        "De leerdoelen zijn allemaal verwerkt in de menu optie 'Gebruikers beheer'.\n" +
                        "'Gebruikers beheer' is beschikbaar als je inlogt als Administrator.",
                "Leerdoel versie", JOptionPane.INFORMATION_MESSAGE);
        menuController.showMenuPanel();
    }

    /**
     * Actionlistener voor het opvangen van clicks op menu buttons.
     */
    class ClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            menuController.hideMenuPanel();
            switch (e.getActionCommand()) {
                case "Importeer":
                    showMessage();
                    break;
                case "Nieuw Product":
                    showMessage();
                    break;
                case "Gebruikers beheer":
                    menuController.createGebruikersBeheerPanel();
                    break;
                case "Nieuwe klant":
                    showMessage();
                    break;
                case "Digitale order invoeren":
                    showMessage();
                    break;
                case "Handmatig order invoeren":
                    showMessage();
                    break;
                case "Rapportages":
                    showMessage();
                    break;
                case "Uitloggen":
                    menuController.showInlogScherm();
                    break;
                default:
                    break;
            }
        }
    }
}


