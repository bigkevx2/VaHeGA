package UI.controller;

import UI.Frame;
import logic.Bedrijf;
import logic.Medewerker;
import UI.model.GebruikersBeheerModel;
import UI.view.GebruikersBeheerView;

/**
 * Classe die dient als de verbinding tussen de view en het model van de GUI in het MVC model
 */
public class GebruikersBeheerController {
    private GebruikersBeheerView gebruikersBeheerView;
    private GebruikersBeheerModel gebruikersBeheerModel;
    private MenuController menuController;
    private Bedrijf bedrijf;
    private Frame frame;
    private Medewerker medewerker;

    /**
     * Package private constructor
     * @param bedrijf Bedrijf
     * @param medewerker Medewerker
     * @param menuController MenuController
     * @param frame Frame
     */
    GebruikersBeheerController(Bedrijf bedrijf, Medewerker medewerker, MenuController menuController, Frame frame) {
        this.menuController = menuController;
        this.frame = frame;
        this.medewerker = medewerker;
        this.gebruikersBeheerModel = new GebruikersBeheerModel();
        this.gebruikersBeheerView = new GebruikersBeheerView(this,gebruikersBeheerModel.getBtnGebruikerToevoegen(),
                gebruikersBeheerModel.getBtnGebruikersBeheren(),gebruikersBeheerModel.getBtnMenu());
        this.bedrijf = bedrijf;
    }

    /**
     * Package private methode om het GebruikersBeheerPanel toe te voegen aan het Frame en de titel van het Frame aan te passen
     */
    void createGebruikersBeheerPanel() {
        frame.add(gebruikersBeheerView.createGebruikersBeheerPanel());
        frame.setTitle(gebruikersBeheerModel.getTitle());
    }

    /**
     * Methode om het menu weer te tonen
     */
    public void showMenu() {
        menuController.showMenuPanel();
    }

    /**
     * Methode om het GebruikersBeheerPanel te verbergen
     */
    public void hideGebruikersBeheerPanel() {gebruikersBeheerView.setVisible(false);}

    /**
     * Package private methode om het GebruikersBeheerPanel weer te tonen nadat deze eerder al is gemaakt.
     */
    void showGebruikersBeheerPanel() {gebruikersBeheerView.setVisible(true);
        frame.setTitle(gebruikersBeheerModel.getTitle());
    }

    /**
     * Methode om vanuit dit panel het panel te creëren voor het toevoegen van een gebruiker.
     */
    public void showToevoegenGebruiker() {
        ToevoegenGebruikerController toevoegenGebruikerController = new ToevoegenGebruikerController(medewerker,bedrijf,
                this, frame);
        toevoegenGebruikerController.createToevoegenGebruikerPanel();
    }

    /**
     * Methode om vanuit dit panel het panel te creëren voor het beheeren van gebruikers.
     */
    public void showBeheerGebruikers() {
        BeheerGebruikersController beheerGebruikersController = new BeheerGebruikersController(medewerker,bedrijf,
                this,frame);
        beheerGebruikersController.createBeheerGebruikersPanel();
    }
}


