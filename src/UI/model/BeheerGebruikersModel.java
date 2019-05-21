package UI.model;

/**
 * Classe die dient als model van de BeheerGebruikersView en de BeheerGebruikersController van de GUI in het MVC model
 */
public class BeheerGebruikersModel {
    private String title = "Gebruikers overzicht";
    private String btnOpslaan = "Opslaan";
    private String btnGebruikersBeheer = "Gebruikers beheer";
    private String wijzigingenOpgeslagen = "Alle wijzigingen zijn opgeslagen";
    private String geenDbConnectie = "Er is een fout opgetreden met de Databas connectie";

    /**
     * Methode om de titel van de view op te halen.
     * @return String
     */
    public String getTitle() {return title;}

    /**
     * Methode om de Gebruikers beheer button tekst op te halen.
     * @return String
     */
    public String getBtnGebruikersBeheer() {return btnGebruikersBeheer;}

    /**
     * Methode om de Opslaan button tekst op te halen.
     * @return String
     */
    public String getBtnOpslaan() {return btnOpslaan;}

    /**
     * Methode om een label tekst op te halen.
     * @return String
     */
    public String wijzigingenOpgeslagen() {return wijzigingenOpgeslagen;}

    /**
     * Methode om een label tekst op te halen.
     * @return String
     */
    public String geenDbConnectie() {return geenDbConnectie;}
}
