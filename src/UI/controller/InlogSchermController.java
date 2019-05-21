package UI.controller;

import UI.Frame;
import logic.*;
import UI.model.InlogSchermModel;
import UI.view.InlogSchermView;
import java.util.Arrays;

/**
 * Classe die dient als de verbinding tussen de view en het model van de GUI in het MVC model
 */
public class InlogSchermController {
    private InlogSchermModel inlogSchermModel;
    private InlogSchermView inlogSchermView;
    private Bedrijf bedrijf;
    private Frame frame;
    private int inlogPoging;
    private Medewerker medewerker;

    /**
     * Constructor
     * @param bedrijf Bedrijf
     * @param frame Frame
     */
    public InlogSchermController(Bedrijf bedrijf, Frame frame) {
        this.frame = frame;
        this.bedrijf = bedrijf;
        this.inlogSchermModel = new InlogSchermModel();
        this.inlogSchermView = new InlogSchermView(this,inlogSchermModel.getLblSysteemMelding(),
                inlogSchermModel.getTxtFieldWidth(),inlogSchermModel.getPasswordFieldWidth(),inlogSchermModel.getButtonInloggen(),
                inlogSchermModel.getButtonAnnuleer(),inlogSchermModel.getLabelGebruikersNaam(),inlogSchermModel.getLabelWachtWoord());
        createInlogPanel();
    }

    /**
     * Methode om de repo naar de db te schrijven en de applicatie te stoppen.
     */
    public void systemClose() {
        try {
            bedrijf.updateRepositoryToDb();
        }
        catch (PersistenceLayerException e) {
            System.out.println("Fout bij het maken van een verbinding met de db: " + e);
        }
        System.exit(0);
    }

    /**
     * Methode voor het creëren van het inlog panel en het toevoegen van dit panel aan het frame, ook de titel van het frame
     * wordt aangepast en de focus van de cursor komt in het eerste veld te staan.
     */
    private void createInlogPanel() {
        frame.add(inlogSchermView.createInlogPanel());
        frame.setTitle(inlogSchermModel.getTitle());
        setFocus();
    }

    /**
     * Methode om de lege tekst van de systeemmelding op te halen uit het model van het inlogscherm
     * @return String
     */
    public String getLblSysteemMelding() {return inlogSchermModel.getLblSysteemMelding();}

    /**
     * Methode om de focus van de cursor in het eerste inlogveld te zetten.
     */
    private void setFocus() {inlogSchermView.setFocus();}

    /**
     * Package private methode om het inlogscherm vanuit het geheugen weer te tonen.
     */
    void showInlogScherm() {inlogSchermView.setVisible(true); setFocus();frame.setTitle(inlogSchermModel.getTitle());}

    /**
     * Methode om het inlogscherm te verbergen.
     */
    private void hideInlogScherm() {inlogSchermView.setVisible(false);}

    /**
     * Methode om de inloggegevens van de gebruiker te controleren.
     * @param gebruikersNaam String
     * @param wachtWoord char[]
     */
    public void checkLogin(String gebruikersNaam, char[] wachtWoord) {
        try {
            // controleren of de gebruiker bekend is in de repo / db
            medewerker = bedrijf.setMedewerker(gebruikersNaam);
            // indien de gebruiker niet bekend is dit melden in de view
            if (medewerker == null) {
                inlogSchermView.setLblSysteemMelding(inlogSchermModel.gebruikerOnbekend());
            } else {
                // Als de gebruiker bekend is aan de hand van zijn toegangsniveau de situatie afhandelen.
                switch (medewerker.getToegangsNiveau()) {
                    case 1:
                        // gelocked account
                        inlogSchermView.setLblSysteemMelding(inlogSchermModel.accountGelocked());
                    case 2:
                        // geen toegang (meer)
                        inlogSchermView.setLblSysteemMelding(inlogSchermModel.geenToegang());
                        break;
                    case 3:
                    case 4:
                        // Gebruiker bekend en met Admin of Verkoopmedewerker rechten, nu opgegeven wachtwoord controleren.
                        if (checkWachtwoord(wachtWoord)) {
                            showMenu();
                        }
                        break;
                    // De default zou niet voor mogen komen in de db, maar als hij toch vuurt dan goed afhandelen.
                    default:
                        inlogSchermView.setLblSysteemMelding(inlogSchermModel.gebruikerOnbekend());
                        break;
                }
            }
        } catch (PersistenceLayerException e) {
            // Log naar console
            System.out.println("PersistenceLayerException: = " + e);
            inlogSchermView.setLblSysteemMelding(inlogSchermModel.geenConnectieDb());
        }
        // opruimen van de view zodat hij weer als nieuw eruitziet als hij weer getoond wordt.
        inlogSchermView.eraseTextfield();
        inlogSchermView.erasePasswordField();
        inlogSchermView.setFocus();
    }

    /**
     * Als de gebruiker met de juiste gegevens inlogt dan wordt het voor hem correcte menu getoond.
     * Dit hangt af van zijn toegangsniveau.
     */
    private void showMenu() {
        MenuController menuController = new MenuController(bedrijf.getMedewerker(), bedrijf,
                this, frame);
        menuController.createMenuPanel();
        hideInlogScherm();
    }

    /**
     * Methode om het wachtwoord in de repo / db te controleren
     * @param wachtWoord String
     * @return geeft een boolean terug zodat de methode checkLogin kan zien of het wachtwoord correct is.
     */
    private Boolean checkWachtwoord(char[] wachtWoord) {
        boolean correctWachtwoord;
        // Na 3 mislukte pogingen wordt het account gelocked.
        if (medewerker.getInlogPoging() < 3) {
            // Omdat er gebruik wordt gemaakt van een JPasswordField kan ik het wachtwoord alleen controleren
            // Door twee char array's met elkaar te vergelijken. Dus eerst het ingevoerde wachtwoord in een char[]
            // zetten.
            // Array voor het ww uit de db.
            char[] correctWw = new char[medewerker.getWachtwoord().length()];
            // Ww uit db in array plaatsen.
            for (int i = 0; i < medewerker.getWachtwoord().length(); i++) {
                correctWw[i] = medewerker.getWachtwoord().charAt(i);
            }
            // Vergelijken van beiden wachtwoord array's.
            if(Arrays.equals(correctWw,wachtWoord)) {
                // Return waarde updaten
                correctWachtwoord = true;
                // na een juiste inlog het aantal pogingen weer op  0 zetten.
                inlogPoging = 0;
            } else {
                // Vegelijking array's met ww mislukt, verkeerde inlog poging.
                // Return waarde updaten
                correctWachtwoord = false;
                // Label op inlogscherm aanpassen.
                inlogSchermView.setLblSysteemMelding(inlogSchermModel.wachtwoordverkeerd());
                // Aantal pogingen updaten.
                inlogPoging += 1;
            }
            // een kopie maken van de medewerker voordat we deze updaten
            Medewerker medewerkerUpdated = medewerker;
            medewerkerUpdated.setInlogPoging(inlogPoging);
            // via bedrijf kunnen we bij de repo om de medewerker te updaten, hiervoor de oude instance en de nieuwe instance nodig.
            bedrijf.update(medewerker, medewerkerUpdated);
        } else {
            // Test op inlogpoginen mislukt, account is gelocked.
            correctWachtwoord = false;
            Medewerker medewerkerUpdated = medewerker;
            medewerkerUpdated.setToegangsNiveau(1);
            bedrijf.update(medewerker, medewerkerUpdated);
            inlogSchermView.setLblSysteemMelding(inlogSchermModel.accountGelocked());
        }
        return correctWachtwoord;
    }
}
