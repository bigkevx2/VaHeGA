package UI.model;

/**
 * Classe die dient als model van de GebruikersBeheerView en de GebruikersBeheerController van de GUI in het MVC model
 */
public class InlogSchermModel {
    private String title = "VaHeGa Login";
    private String lblSysteemMelding = "";
    private int txtFieldWidth = 10;
    private int passwordFieldWidth = 10;
    private String labelGebruikersNaam = "Gebruikersnaam: ";
    private String labelWachtWoord  = "Wachtwoord: ";
    private String buttonInloggen = "Inloggen";
    private String buttonAnnuleer = "Annuleer";

    /**
     * Methode om de titel van de view op te halen.
     * @return String
     */
    public String getTitle() {return title;}

    /**
     * Methode om de labeltekst van het gebruikersnaam invoerveld op te halen.
     * @return String
     */
    public String getLabelGebruikersNaam() {return labelGebruikersNaam;}

    /**
     * Methode om de labeltekst van het wachtwoord invoerveld op te halen.
     * @return String
     */
    public String getLabelWachtWoord() {return labelWachtWoord;}

    /**
     * Methode om de inlog button tekst op te halen.
     * @return String
     */
    public String getButtonInloggen() {return buttonInloggen;}

    /**
     * Methode om de annuleer button tekst op te halen.
     * @return String
     */
    public String getButtonAnnuleer() {return buttonAnnuleer;}

    /**
     * Methode om breedte van het wachtwoord veld op te halen.
     * @return int
     */
    public int getPasswordFieldWidth() {return passwordFieldWidth;}

    /**
     * Methode om een leeg label of invoerveld tekst op te halen.
     * @return String
     */
    public String getLblSysteemMelding() {return lblSysteemMelding;}

    /**
     * Methode om breedte van het gebruikersnaam invoer veld op te halen.
     * @return int
     */
    public int getTxtFieldWidth() {return txtFieldWidth;}

    // Helper methodes voor diverse systeem meldingen

    /**
     * Methode om een systeemmelding te tonen bij een onbekende gebruiker.
     * @return String
     */
    public String gebruikerOnbekend() {return "Gebruikersnaam onbekend";}

    /**
     * Methode om een systeemmelding te tonen bij een verkeerd wachtwoord.
     * @return String
     */
    public String wachtwoordverkeerd() {return "Onjuist wachtwoord";}

    /**
     * Methode om een systeemmelding te tonen bij een gelocked account.
     * @return String
     */
    public String accountGelocked() {return "Account gelocked. Neem contact op met uw system administrator.";}

    /**
     * Methode om een systeemmelding te tonen bij geen toegang.
     * @return String
     */
    public String geenToegang() {return "Toegang geweigerd";}

    /**
     * Methode om een systeemmelding te tonen bij een probleem met de db connectie.
     * @return String
     */
    public String geenConnectieDb() {return "Geen verbinding met de database, controleer je setup";}

}


