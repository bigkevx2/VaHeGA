package UI.controller;

import UI.Frame;
import logic.Bedrijf;
import logic.Medewerker;
import UI.model.MenuModel;
import UI.view.MenuView;

/**
 * Classe die dient als de verbinding tussen de view en het model van de GUI in het MVC model
 */
public class MenuController {
    private MenuView menuView;
    private MenuModel menuModel;
    private InlogSchermController inlogSchermController;
    private Bedrijf bedrijf;
    private Frame frame;
    private Medewerker medewerker;

    /**
     * Package private constructor
     * @param medewerker Medewerker
     * @param bedrijf Bedrijf
     * @param inlogSchermController InlogSchermController
     * @param frame Frame
     */
    MenuController(Medewerker medewerker, Bedrijf bedrijf, InlogSchermController inlogSchermController, Frame frame) {
        this.frame = frame;
        this.inlogSchermController = inlogSchermController;
        this.medewerker = medewerker;
        this.menuModel = new MenuModel(medewerker);
        this.menuView = new MenuView(this,menuModel.getBtnImporteer(),menuModel.getBtnNieuwProduct(),
                menuModel.getBtnGebruikersBeheer(),menuModel.getBtnNieuweKlant(),menuModel.getBtnDigitaleOrder(),
                menuModel.getBtnHandmatigeOrder(),menuModel.getBtnRapportages(),menuModel.getBtnUitloggen());
        this.bedrijf = bedrijf;
    }

    /**
     * Package private methode voor het creëren van het menu panel, deze toe te voegen aan het frame en de titel aan te passen
     */
    void createMenuPanel() {
        frame.add(menuView.createMenuPanel());
        frame.setTitle(menuModel.getTitle());
    }

    /**
     * Methode om het menu panel te tonen nadat deze al eerder is gecreëerd.
     */
    public void showMenuPanel() {
        menuView.setVisible(true);
        frame.setTitle(menuModel.getTitle());
    }

    /**
     * Methode om het menu panel te verbergen.
     */
    public void hideMenuPanel() {menuView.setVisible(false);}

    /**
     * Methode om het toegangsniveau van de gebruiker op te halen uit het model van het menu
     * @return int
     */
    public int medewerkerToegangsNiveau() {return menuModel.getMedewerkerToegangsNiveau();}

    /**
     * Methode om het inlog scherm (weer) te tonen.
     */
    public void showInlogScherm() {inlogSchermController.showInlogScherm();}

    /**
     * Methode om het GebruikersBeheerPanel aan te maken en te tonen.
     */
    public void createGebruikersBeheerPanel() {
        GebruikersBeheerController gebruikersBeheerController = new GebruikersBeheerController(bedrijf,
                medewerker,this, frame);
        gebruikersBeheerController.createGebruikersBeheerPanel();
        hideMenuPanel();
    }
}
