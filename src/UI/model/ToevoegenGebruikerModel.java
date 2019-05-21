package UI.model;

/**
 * Classe die dient als model van de ToevoegenGebruikerView en de ToevoegenGebruikerViewController van de GUI in het MVC model
 */
public class ToevoegenGebruikerModel {
    private String title = "Toevoegen gebruiker";
    private String lblVerplichteVelden = "Velden met een * zijn verplicht";
    private int clmVoorNaam = 10;
    private String txtVoorNaam = "*Voornaam:";
    private int clmAchterNaam = 10;
    private String txtAchterNaam = "*Achternaam:";
    private int clmGebruikersMaam = 10;
    private String txtGebruikersNaam = "*Gebruikersnaam:";
    private int clmPassword = 10;
    private String txtPassword = "*Password:";
    private String[] medewerkerSoorten = {"Verkoop medewerker", "Administrator"};
    private String lblMedewerkerSoorten = "Soort Medewerker:";
    private String lblGebruikerToegevoegd = "";
    private String btnNieuweGebruiker = "Gebruiker opslaan";
    private String btnGebruikersBeheer = "Gebruikers beheer";
    private String lblGeenDbConnectie = "Er is een probleem met de database connectie";
    private String lblSuccesGebruikerToegevoegd = "Gebruiker toegevoegd";
    private String lblGebruikersnaamBezet = "Gebruikersnaam al in gebruik, kies een andere naam";

    /**
     * Methode om de labeltekst van systeem melding label op te halen.
     * @return String
     */
    public String getLblSuccesGebruikerToegevoegd() {return lblSuccesGebruikerToegevoegd;}

    /**
     * Methode om de labeltekst van systeem melding label op te halen.
     * @return String
     */
    public String getLblGeenDbConnectie() {return lblGeenDbConnectie;}

    /**
     * Methode om de labeltekst van systeem melding label op te halen.
     * @return String
     */
    public String getLblGebruikersnaamBezet() {return lblGebruikersnaamBezet;}

    /**
     * Methode om de nieuwe gebruiker button tekst op te halen.
     * @return String
     */
    public String getBtnNieuweGebruiker() {return btnNieuweGebruiker;}

    /**
     * Methode om de gebruikers beheer button tekst op te halen.
     * @return String
     */
    public String getBtnGebruikersBeheer() {return btnGebruikersBeheer;}

    /**
     * Methode om de titel van de view op te halen.
     * @return String
     */
    public String getTitle() {return title;}

    /**
     * Methode om de labeltekst voor het verplichte velden label op te halen.
     * @return String
     */
    public String getLblVerplichteVelden() {return lblVerplichteVelden;}

    /**
     * Methode om breedte van het voornaam invoer veld op te halen.
     * @return int
     */
    public int getClmVoorNaam() {return clmVoorNaam;}

    /**
     * Methode om de labeltekst van het voornaam label op te halen.
     * @return String
     */
    public String getTxtVoorNaam() {return txtVoorNaam;}

    /**
     * Methode om breedte van het achternaam invoer veld op te halen.
     * @return int
     */
    public int getClmAchterNaam() {return clmAchterNaam;}

    /**
     * Methode om de labeltekst van het achternaam label op te halen.
     * @return String
     */
    public String getTxtAchterNaam() {return txtAchterNaam;}

    /**
     * Methode om breedte van het gebruikersnaam invoer veld op te halen.
     * @return int
     */
    public int getClmGebruikersMaam() {return clmGebruikersMaam;}

    /**
     * Methode om de labeltekst van het gebruikersnaam label op te halen.
     * @return String
     */
    public String getTxtGebruikersNaam() {return txtGebruikersNaam;}

    /**
     * Methode om breedte van het wachtwoord invoer veld op te halen.
     * @return int
     */
    public int getClmPassword() {return clmPassword;}

    /**
     * Methode om de labeltekst van het wachtwoord label op te halen.
     * @return String
     */
    public String getTxtPassword() {return txtPassword;}

    /**
     * Methode om de inhoud van het dropdown menu op te halen.
     * @return String[]
     */
    public String[] getMedewerkerSoorten() {return medewerkerSoorten;}

    /**
     * Methode om de labeltekst van het medewerkersoorten label op te halen.
     * @return String
     */
    public String getLblMedewerkerSoorten() {return lblMedewerkerSoorten;}

    /**
     * Methode om de labeltekst van systeem melding label op te halen.
     * @return String
     */
    public String getLblGebruikerToegevoegd() {return lblGebruikerToegevoegd;}
}
