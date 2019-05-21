package logic;

import javax.swing.table.DefaultTableModel;

/**
 * Subclass van logic.Medewerker met specialisatie voor een administrator
 */
public class Administrator extends Medewerker {
    // De Administrator heeft meer rechten dan een verkoopmedewerker, daarom kan hij ook communiceren met de
    // MedewerkerRepository.
    private MedewerkerRepository medewerkerRepository;

    /**
     * Constructor voor een Administrator
     * @param gebruikersNaam, String
     * @param voorNaam, String
     * @param achterNaam, String
     * @param toegangsNiveau, int
     * @param wachtwoord, String
     * @param medewerkerID, String
     * @param inlogPoging, int
     */
    public Administrator(String gebruikersNaam, String voorNaam, String achterNaam, int toegangsNiveau, String wachtwoord,
                         String medewerkerID, int inlogPoging) {
        super(gebruikersNaam, voorNaam, achterNaam, toegangsNiveau, wachtwoord, medewerkerID,inlogPoging);
    }

    /**
     * Methode voor het ophalen van alle gebruikers uit de db om deze in een overzicht te zetten in de GebruikersBeheerView
     * @return deze methode geeft een data type DefaultTableModel terug.
     * @throws PersistenceLayerException, bij het maken kan een PersistenceLayerException optreden,
     * deze wordt afgehandeld door de vragende view controller
     */
    public DefaultTableModel getGebruikers() throws PersistenceLayerException {
        // Opbouwen van de header van de tabel met daarin alle gebruikers
        String[] columns = {"ID", "Voornaam", "Achternaam", "Gebruikersnaam", "Wachtwoord", "Rol"};
        DefaultTableModel tableModel = new DefaultTableModel(columns,0);
        // Alle medewerkers uit de repository halen en ze een uitgeschreven niveau meegeven, in de db worden hiervoor int's gebruikt.
            for (Medewerker gebruiker : medewerkerRepository.getAll()) {
                String toegangsNiveau;
                if (gebruiker.getToegangsNiveau() == 3) {
                    toegangsNiveau = "Administrator";
                } else if (gebruiker.getToegangsNiveau() == 4) {
                    toegangsNiveau = "Verkoopmedewerker";
                } else {
                    toegangsNiveau = "Gedeactiveerd";
                }
                // opbouwen van de array met daarin alle rijen van de tabel.
                Object[] temp ={gebruiker.getMedewerkerID(), gebruiker.getVoorNaam(),
                        gebruiker.getAchterNaam(), gebruiker.getGebruikersNaam(), gebruiker.getWachtwoord(),
                        toegangsNiveau};
                tableModel.addRow(temp);
                }
//        Testrapport: Administrator#1
//        Correct aanmaken van een DefaultTableModel met minimaal 1 rij als inhoud
        if (tableModel.getRowCount() > 0) {
            System.out.println("Administrator#1: Pass");
        } else {
            System.out.println("Administrator#1: Fail");
        }
        return tableModel;
    }

    /**
     * Methode om gewijzigde gebruikersgegevens te updaten in de repo
     * @param updatedMedewerker, een nieuw aangemaakt medewerker object ter vervanging van de oude in de repo.
     */
    public void buildUpdate(Medewerker updatedMedewerker) {
        // check of de medewerker ID in de set zit (zou moeten) en deze verwijderen, dan de geupdated medewerker toevoegen
        // via de update methode.
        Medewerker medewerker = null;
        for (Medewerker m : medewerkerRepository.getMedewerkers()) {
            if (m.getGebruikersNaam().equals(updatedMedewerker.getGebruikersNaam())) {
                medewerker = m;
//                Testrapport Administrator#2
//                Zoeken naar een te updaten medewerker object in de repository en deze toewijzen aan medewerker.
//                Het gebruikersnaam van beiden moet gelijk zijn om te slagen.
                System.out.println("Administrator#2: Pass");
            } else {
                System.out.println("Administrator#2: Fail");
            }
        }
        // Het oude object van de medewerker wordt verwijdert uit de repo en de nieuwe erin gezet.
        medewerkerRepository.update(medewerker, updatedMedewerker);
    }

    /**
     * Methode om de repo te updaten in de db.
     * @throws PersistenceLayerException, deze exceptie wordt afgevangen in de view.
     */
    public void updateGebruikers() throws PersistenceLayerException{
//        Wordt in MedewerkerRepository getest.
        medewerkerRepository.updateDb();
    }

    /**
     * Methode voor het toevoegen van een gebruiker aan de applicatie, de repo en de db.
     * @param medewerker, het object dat toegevoegd moet worden
     * @throws PersistenceLayerException, deze exceptie wordt afgevangen in de view.
     */
    public void toevoegenGebebruiker(Medewerker medewerker) throws PersistenceLayerException{
//        Wordt in MedewerkerRepository getest.
        medewerkerRepository.add(medewerker);
    }

    /**
     * Package private methode voor het ophalen van de lokaal opgeslagen medewerkers in de repo.
     * @param medewerkerRepository, de medewerkerRepository.
     */
     void setMedewerkerRepository(MedewerkerRepository medewerkerRepository) {
        this.medewerkerRepository = medewerkerRepository;
    }

//    /**
//     * Methode nog niet uitgeschreven, behoort niet tot de applicatie voor het aantonen van leerdoelen.
//     */
//    public void toevoegenProduct() {
//
//    }

//    /**
//     * Methode nog niet uitgeschreven, behoort niet tot de applicatie voor het aantonen van leerdoelen.
//     */
//    public void importeerData() {
//
//    }
}
