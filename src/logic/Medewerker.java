package logic;

/**
 * Abstracte class voor het creëren van diverse medewerker subclasses
 */
public abstract class Medewerker {
    private String gebruikersNaam;
    private String voorNaam;
    private String achterNaam;
    private int toegangsNiveau;
    private String wachtwoord;
    private String medewerkerID;
    private int inlogPoging;

    /**
     * Constructor 1, zonder inlogpoging en medewerker_ID
     * @param gebruikersNaam, String
     * @param voorNaam, String
     * @param achterNaam, String
     * @param toegangsNiveau, int
     * @param wachtwoord, String
     */
    public Medewerker(String gebruikersNaam, String voorNaam, String achterNaam, int toegangsNiveau, String wachtwoord) {
        this.gebruikersNaam = gebruikersNaam;
        this.voorNaam = voorNaam;
        this.achterNaam = achterNaam;
        this.toegangsNiveau = toegangsNiveau;
        this.wachtwoord = wachtwoord;
    }

    /**
     * Constructor 2, met inlogpoging en medewerker_ID
     * @param gebruikersNaam, String
     * @param voorNaam, String
     * @param achterNaam, String
     * @param toegangsNiveau, int
     * @param wachtwoord, String
     * @param medewerkerID, String
     * @param inlogPoging, int
     */
    public Medewerker(String gebruikersNaam, String voorNaam, String achterNaam, int toegangsNiveau, String wachtwoord,
                      String medewerkerID, int inlogPoging) {
        this.gebruikersNaam = gebruikersNaam;
        this.voorNaam = voorNaam;
        this.achterNaam = achterNaam;
        this.toegangsNiveau = toegangsNiveau;
        this.wachtwoord = wachtwoord;
        this.medewerkerID = medewerkerID;
        this.inlogPoging = inlogPoging;
    }

    /**
     * Getter voor Wachtwoord.
     * @return String
     */
    public String getWachtwoord() {
        return wachtwoord;
    }

    /**
     * Getter voor GebruikersNaam
     * @return String
     */
    String getGebruikersNaam() {
        return gebruikersNaam;
    }

    /**
     * Getter voor InlogPoging
     * @return int
     */
    public int getInlogPoging() {
        return inlogPoging;
    }

    /**
     * Getter voor VoorNaam
     * @return String
     */
    public String getVoorNaam() {
        return voorNaam;
    }

    /**
     * Getter voor AchterNaam
     * @return String
     */
    public String getAchterNaam() {
        return achterNaam;
    }

    /**
     * Getter voor ToegangsNiveau
     * @return int
     */
    public int getToegangsNiveau() {
        return toegangsNiveau;
    }

    /**
     * Getter voor MedewerkerID
     * @return String
     */
    String getMedewerkerID() {
        return medewerkerID;
    }

    /**
     * Setter voor InlogPoging(
     * @param inlogPoging int
     */
    public void setInlogPoging(int inlogPoging) {
        this.inlogPoging = inlogPoging;
    }

    /**
     * Setter voor ToegangsNiveau
     * @param toegangsNiveau int
     */
    public void setToegangsNiveau(int toegangsNiveau) {
        this.toegangsNiveau = toegangsNiveau;
    }
}
