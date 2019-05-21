package UI.controller;

import UI.Frame;
import logic.Administrator;
import logic.Bedrijf;
import UI.model.BeheerGebruikersModel;
import UI.view.BeheerGebruikersView;
import logic.Medewerker;
import logic.PersistenceLayerException;
import java.awt.*;

/**
 * Classe die dient als de verbinding tussen de view en het model van de GUI in het MVC model
 */
public class BeheerGebruikersController {
    private BeheerGebruikersView beheerGebruikersView;
    private BeheerGebruikersModel beheerGebruikersModel;
    private GebruikersBeheerController gebruikersBeheerController;
    private Administrator administrator;
    private Bedrijf bedrijf;
    private Frame frame;

    /**
     * Package private Constructor
     * @param medewerker Medewerker
     * @param bedrijf Bedrijf
     * @param gebruikersBeheerController GebruikersBeheerController
     * @param frame Frame
     */
    BeheerGebruikersController(Medewerker medewerker, Bedrijf bedrijf, GebruikersBeheerController gebruikersBeheerController,
                               Frame frame) {
        beheerGebruikersModel = new BeheerGebruikersModel();
        this.gebruikersBeheerController = gebruikersBeheerController;
        this.frame = frame;
        this.bedrijf = bedrijf;
        this.administrator = (Administrator) medewerker;
        beheerGebruikersView = new BeheerGebruikersView(this,beheerGebruikersModel.getBtnGebruikersBeheer());

    }

    /**
     * Package private methode voor het creëren van het BeheerGebruikersPanel
     */
    void createBeheerGebruikersPanel() {
        // DefaultTableModel creëert de tabel met alle gebruikers
        setDefaultTableModel();
        // Panel toevoegen aan het Frame
        frame.add(beheerGebruikersView.createBeheerGebruikersPanel(beheerGebruikersModel.getBtnOpslaan()));
        // Titel van het Frame aanpassen aan de getoonde panel
        frame.setTitle(beheerGebruikersModel.getTitle());
    }

    /**
     * Methode om dit panel te verbergen, wordt gebruikt om een ander panel te kunnen tonen.
     */
    public void hideBeheerGebruikersPanel() {beheerGebruikersView.setVisible(false);}

    /**
     * Methode om vanuit dit panel het GebruikersBeheer panel te tonen
     */
    public void startGebruikersBeheer() {gebruikersBeheerController.showGebruikersBeheerPanel();}

    /**
     * Methode voor het opbouwen van de tabel met alle gebruikers om te tonen in de view.
     */
    private void setDefaultTableModel() {
        try {
            beheerGebruikersView.setDefaultTableModel(administrator.getGebruikers());
        }
        catch (PersistenceLayerException e) {
            beheerGebruikersView.setLblSysteemMelding(beheerGebruikersModel.geenDbConnectie());
            beheerGebruikersView.setLblTextColor(Color.red);
        }
    }

    /**
     * Methode om wijzigingen die in de view worden gemaakt op gebruikers door te geven aan de instance van administrator
     * zodat deze vanuit daar in de repo worden gezet.
     * @param medewerker Medewerker
     */
    public void buildUpdate(Medewerker medewerker) {administrator.buildUpdate(medewerker);}

    /**
     * Methode om de wijzigingen in de repo door te zetten naar de db
     */
    public void updateGebruikers() {
        try {
            administrator.updateGebruikers();
            beheerGebruikersView.setLblSysteemMelding(beheerGebruikersModel.wijzigingenOpgeslagen());
            beheerGebruikersView.setLblTextColor(Color.green);
        }
        catch (PersistenceLayerException e) {
            // Console log
            System.out.println("Fout bij het maken met een verbinding met de persistentielaag, fout = " + e);
            beheerGebruikersView.setLblSysteemMelding(beheerGebruikersModel.geenDbConnectie());
            beheerGebruikersView.setLblTextColor(Color.red);
        }
    }

    /**
     * Methode om de view te voorzien van een melding voor het tonen in een label van de view vanuit het model van deze view
     * @return String met melding.
     */
    public String setSysteemMelding() {return (beheerGebruikersModel.geenDbConnectie());}
}
