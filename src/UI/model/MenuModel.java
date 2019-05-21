package UI.model;

import logic.Medewerker;

/**
 * Classe die dient als model van de MenuView en de MenuController van de GUI in het MVC model
 */
public class MenuModel {
    private Medewerker medewerker;
    private String title = "VaHeGa Menu voor gebruiker: ";
    private String btnUitloggen = "Uitloggen";
    private String btnImporteer = "Importeer";
    private String btnNieuwProduct = "Nieuw Product";
    private String btnGebruikersBeheer = "Gebruikers beheer";
    private String btnNieuweKlant = "Nieuwe klant";
    private String btnDigitaleOrder = "Digitale order invoeren";
    private String btnHandmatigeOrder = "Handmatig order invoeren";
    private String btnRapportages = "Rapportages";

    /**
     * Constructor, bij de creatie van een object van deze Classe is een medewerker instantie nodig om te bepalen welk menu getoond moet worden, admin of verkoopmedewerker.
     * @param medewerker Medewerker
     */
    public MenuModel(Medewerker medewerker) {
        this.medewerker = medewerker;
        setTitle();
    }

    /**
     * Methode om de titel van de view op te halen.
     * @return String
     */
    public String getTitle() {return title;}

    /**
     * Methode om de titel samen te stellen afhankelijk van welke gebruiker ingelogd is.
     */
    private void setTitle() {title += medewerker.getVoorNaam() + " " + medewerker.getAchterNaam();}

    /**
     * Methode om het toegangsniveau van de medewerker terug te geven.
     * @return Int
     */
    public int getMedewerkerToegangsNiveau() {return medewerker.getToegangsNiveau();}

    /**
     * Methode om de uitlog button tekst op te halen.
     * @return String
     */
    public String getBtnUitloggen() {return btnUitloggen;}

    /**
     * Methode om de importeer button tekst op te halen.
     * @return String
     */
    public String getBtnImporteer() {
        return btnImporteer;
    }

    /**
     * Methode om de nieuw product button tekst op te halen.
     * @return String
     */
    public String getBtnNieuwProduct() {
        return btnNieuwProduct;
    }

    /**
     * Methode om de gebruikers beheer button tekst op te halen.
     * @return String
     */
    public String getBtnGebruikersBeheer() {
        return btnGebruikersBeheer;
    }

    /**
     * Methode om de nieuwe klant button tekst op te halen.
     * @return String
     */
    public String getBtnNieuweKlant() {
        return btnNieuweKlant;
    }

    /**
     * Methode om de digitale order button tekst op te halen.
     * @return String
     */
    public String getBtnDigitaleOrder() {
        return btnDigitaleOrder;
    }

    /**
     * Methode om de handmatige order button tekst op te halen.
     * @return String
     */
    public String getBtnHandmatigeOrder() {
        return btnHandmatigeOrder;
    }

    /**
     * Methode om de rapportage button tekst op te halen.
     * @return String
     */
    public String getBtnRapportages() {
        return btnRapportages;
    }
}


