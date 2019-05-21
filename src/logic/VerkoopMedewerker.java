package logic;

import logic.Medewerker;

/**
 * Subclass van logic.Medewerker, met specialisatie voor verkoopmedewerker
 * Class nog niet in gebruik bij het aantonen van de leerdoelen.
 */
public class VerkoopMedewerker extends Medewerker {
    public VerkoopMedewerker(String gebruikersNaam, String voorNaam, String achterNaam, int toegangsNiveau, String wachtwoord) {
        super(gebruikersNaam, voorNaam, achterNaam, toegangsNiveau,wachtwoord);
    }

    public void genereerRapportage() {

    }
}
