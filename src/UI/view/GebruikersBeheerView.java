package UI.view;

import UI.controller.GebruikersBeheerController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe die de view opzet van het gebruikersBeheer scherm.
 */
public class GebruikersBeheerView extends JPanel{
    private GebruikersBeheerController gebruikersBeheerController;
    private String buttonGebruikerToevoegen;
    private String buttonGebruikersBeheren;
    private String buttonMenu;

    /**
     * Constructor om deze view te creêeren
     * @param gebruikersBeheerController GebruikersBeheerController.
     * @param btnGebruikerToevoegen String
     * @param btnGebruikersBeheren String
     * @param btnMenu String
     */
    public GebruikersBeheerView(GebruikersBeheerController gebruikersBeheerController,String btnGebruikerToevoegen,
            String btnGebruikersBeheren, String btnMenu) {
        this.gebruikersBeheerController = gebruikersBeheerController;
        buttonGebruikersBeheren = btnGebruikersBeheren;
        buttonGebruikerToevoegen = btnGebruikerToevoegen;
        buttonMenu = btnMenu;
    }

    /**
     * Methode om een panel te creëren voor de GebruikersBeheerView.
     * @return JPanel
     */
    public JPanel createGebruikersBeheerPanel() {
        ClickListener listener = new ClickListener();
        JButton btnGebruikerToevoegen = new JButton(buttonGebruikerToevoegen);
        btnGebruikerToevoegen.addActionListener(listener);
        JButton btnGebruikerDeactiveren = new JButton(buttonGebruikersBeheren);
        btnGebruikerDeactiveren.addActionListener(listener);
        JButton btnMenu = new JButton(buttonMenu);
        btnMenu.addActionListener(listener);

        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 0;
        c.insets = new Insets(0,0,10,0);
        add(btnGebruikerToevoegen,c);
        c.gridy = 1;
        c.insets = new Insets(0,0,10,0);
        add(btnGebruikerDeactiveren,c);
        c.gridy = 2;
        c.insets = new Insets(20,0,0,0);
        add(btnMenu,c);

        return this;
    }


    /**
     * Actionlistener voor het opvangen van clicks op menu buttons.
     */
    class ClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            gebruikersBeheerController.hideGebruikersBeheerPanel();
            switch (e.getActionCommand()) {
                case "Gebruiker toevoegen":
                    gebruikersBeheerController.showToevoegenGebruiker();
                    break;
                case "Gebruikers beheren":
                    gebruikersBeheerController.showBeheerGebruikers();
                    break;
                case "Menu":
                    gebruikersBeheerController.showMenu();
                    break;
            }
        }
    }
}
