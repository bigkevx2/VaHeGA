package UI.model;

/**
 * Classe die dient als model van de GebruikersBeheerView en de GebruikersBeheerController van de GUI in het MVC model
 */
public class GebruikersBeheerModel {
    private String title = "Gebruikers beheer";
    private String btnGebruikerToevoegen = "Gebruiker toevoegen";
    private String btnGebruikersBeheren = "Gebruikers beheren";
    private String btnMenu = "Menu";

    /**
     * Methode om de titel van de view op te halen
     * @return String
     */
    public String getTitle() {return title;}

    /**
     * Methode om de Gebruiker toevoegen button tekst op te halen.
     * @return String
     */
    public String getBtnGebruikerToevoegen() {return btnGebruikerToevoegen;}

    /**
     * Methode om de Gebruikers beheren button tekst op te halen.
     * @return String
     */
    public String getBtnGebruikersBeheren() {return btnGebruikersBeheren;}

    /**
     * Methode om de menu button tekst op te halen.
     * @return String
     */
    public String getBtnMenu() {return btnMenu;}
}
