package UI.controller;

import UI.Frame;
import logic.Administrator;
import logic.Bedrijf;
import UI.model.ToevoegenGebruikerModel;
import UI.view.ToevoegenGebruikerView;
import logic.Medewerker;
import logic.PersistenceLayerException;
import java.awt.*;

/**
 * Classe die dient als de verbinding tussen de view en het model van de GUI in het MVC model
 */
public class ToevoegenGebruikerController {
    private ToevoegenGebruikerView toevoegenGebruikerView;
    private ToevoegenGebruikerModel toevoegenGebruikerModel;
    private GebruikersBeheerController gebruikersBeheerController;
    private Administrator medewerker;
    private Bedrijf bedrijf;
    private Frame frame;

    /**
     * Package private constructo
     * @param medewerker Medewerker
     * @param bedrijf Bedrijf
     * @param gebruikersBeheerController GebruikersBeheerController
     * @param frame Frame
     */
    ToevoegenGebruikerController(Medewerker medewerker, Bedrijf bedrijf,
                                        GebruikersBeheerController gebruikersBeheerController, Frame frame) {
        this.gebruikersBeheerController = gebruikersBeheerController;
        toevoegenGebruikerModel = new ToevoegenGebruikerModel();
        this.medewerker = (Administrator) medewerker;
        this.frame = frame;
        this.bedrijf = bedrijf;
        toevoegenGebruikerView = new ToevoegenGebruikerView(this,
                toevoegenGebruikerModel.getMedewerkerSoorten(),
                toevoegenGebruikerModel.getLblVerplichteVelden(),
                toevoegenGebruikerModel.getClmVoorNaam(), toevoegenGebruikerModel.getTxtVoorNaam(),
                toevoegenGebruikerModel.getClmAchterNaam(),toevoegenGebruikerModel.getTxtAchterNaam(),
                toevoegenGebruikerModel.getClmGebruikersMaam(), toevoegenGebruikerModel.getTxtGebruikersNaam(),
                toevoegenGebruikerModel.getClmPassword(),toevoegenGebruikerModel.getTxtPassword(),
                toevoegenGebruikerModel.getLblMedewerkerSoorten(),
                toevoegenGebruikerModel.getLblGebruikerToegevoegd(),
                toevoegenGebruikerModel.getBtnNieuweGebruiker(),toevoegenGebruikerModel.getBtnGebruikersBeheer());

    }

    /**
     * Package private methode voor het creëren van het menu panel, deze toe te voegen aan het frame en de titel aan te passen
     */
    void createToevoegenGebruikerPanel() {
        frame.add(toevoegenGebruikerView.createToevoegenGebruikerPanel());
        frame.setTitle(toevoegenGebruikerModel.getTitle());
    }

    /**
     * Methode om te kijken of de gebruikersnaam van de nieuwe gebruiker al niet voorkomt in de applicatie, indien dit het geval
     * is wordt deze nieuwe gebruikersnaam geweigerd.
     * @param gebruikersNaam String
     * @return boolean
     */
    public boolean checkUserName(String gebruikersNaam) {
        boolean userNameTaken = true;
        try {
            if (bedrijf.setMedewerker(gebruikersNaam) == null) {
                userNameTaken = false;
            } else {
                toevoegenGebruikerView.setLabelSysteemMelding(toevoegenGebruikerModel.getLblGebruikersnaamBezet());
                toevoegenGebruikerView.setLabelSysteemMeldingColor(Color.red);
            }
        }
        catch (PersistenceLayerException e) {
            toevoegenGebruikerView.setLabelSysteemMelding(toevoegenGebruikerModel.getLblGeenDbConnectie());
            toevoegenGebruikerView.setLabelSysteemMeldingColor(Color.red);
        }
        return userNameTaken;
    }

    /**
     * Methode om terug te keren naar het menu GebruikersBeheer, dit panel is hiervoor altijd al gecreëerd.
     */
    public void showGebruikersBeheer() {
        gebruikersBeheerController.showGebruikersBeheerPanel();
    }

    /**
     * Methode om het ToevoegenGebruikerPanel te verbergen.
     */
    public void hideToevoegenGebruikerPanel() {toevoegenGebruikerView.setVisible(false);}

    /**
     * Methode om het toevoegen van een gebruiker (via een Administrator object) in gang te zetten.
     * @param medewerker Medewerker
     */
    public void toevoegenGebruiker(Medewerker medewerker) {
        try {
            this.medewerker.toevoegenGebebruiker(medewerker);
            toevoegenGebruikerView.setLabelSysteemMelding(toevoegenGebruikerModel.getLblSuccesGebruikerToegevoegd());
            toevoegenGebruikerView.setLabelSysteemMeldingColor(Color.green);
        }
        catch (PersistenceLayerException e){
            // Console log
            System.out.println("Fout bij het maken van een verbinding met de persistentielaag, fout = " + e);
            toevoegenGebruikerView.setLabelSysteemMelding(toevoegenGebruikerModel.getLblGeenDbConnectie());
            toevoegenGebruikerView.setLabelSysteemMeldingColor(Color.red);
        }
    }
}
