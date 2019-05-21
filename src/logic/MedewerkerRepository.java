package logic;
import java.sql.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Class voor het ophalen, updaten, opslaan en lokaal bewaren van Medewerker objecten.
 * Deze class handelt alle SQL exceptions af die voor kunnen komen met het communiceren met de SQL database.
 * Deze class geeft vervolgens aan de GUI een meer algemene exception door die lokaal kan worden afgehandelt.
 */
public class MedewerkerRepository implements IRepository {
    private Set<Medewerker> medewerkers = new HashSet<>();
    private String server = ""; //TODO: set to your local machine
    private String userName = ""; //TODO: set to your mssql username
    private String password = ""; //TODO: set to your db password
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private String query = "";
    private String connectionUrl =
            "jdbc:sqlserver://" + server + ";"
                    + "database=VAHEGA;"
                    + "user=" + userName + ";"
                    + "password=" + password + ";"
                    + "encrypt=true;"
                    + "trustServerCertificate=true;"
                    + "loginTimeout=30;";

    // De repo haalt data op uit de db en maakt hier objecten van die de applicatie begrijpt.
    // Mochten we voor een andere oplossing gaan dan een SQL db dan moeten de getAllDb, getDb en updateDb methodes
    // aangepast worden naar de nieuwe opslag methode.

    /**
     * Methode om alle gebruikers uit de db op te halen.
     * @throws PersistenceLayerException, wordt afgehandelt in de view.
     */
    private void getAllDb() throws PersistenceLayerException {
        query = "select Medewerker.Medewerker_ID, Voornaam, Achternaam, Gebruikersnaam, Wachtwoord, Inlogpoging, Rol.Rol_ID " +
                "from ((Medewerker inner join Medewerker_Rol on Medewerker.Medewerker_ID = Medewerker_Rol.Medewerker_ID) " +
                "inner join Rol on Medewerker_Rol.Rol_ID = Rol.Rol_ID) ";
        try {
            connectAndExecute(2);
            // Controleren of een object van deze medewerker al aanwezig is, speciaal voor deze rede gekozen voor een Set
            // maar helaas krijgt ieder object toch een unieke hashcode mee, dit moet ook eenvoudiger op te lossen zijn maar
            // voor nu even zo.
            while (resultSet.next()) {
                boolean dublicate = false;
                for (Medewerker medewerker : medewerkers) {
                    if (medewerker.getGebruikersNaam().equals(resultSet.getString(4))) {
                        dublicate = true;
//                        MedewerkerRepository#1
//                        Methode heeft een medewerker gevonden die al in de repo staat, hierdoor wordt deze niet meer opnieuw opgenomen.
                        System.out.println("MedewerkerRepository#1: Pass");
                    }
                }
//                Deze if kan niet in de for iteratie omdat dan de Set wordt aangepast terwijl deze in een iteratie zit.
                if (!dublicate) {
                    // Als het object nog niet voorkomt in de repo, deze maken en toevoegen.
                    medewerkers.add(new Administrator(resultSet.getString(4), resultSet.getString(2),
                            resultSet.getString(3), resultSet.getInt(7), resultSet.getString(5),
                            resultSet.getString(1), resultSet.getInt(6)));
//                    MedewerkerRepository#2
//                    Methode heeft een medewerker gevonden die nog niet in de repo staat.
                    System.out.println("MedewerkerRepository#2: Pass");
                }
            }
            }
        catch (SQLException e) {
            throw new PersistenceLayerException(e.getMessage());
        }
    }

    /**
     * Methode om één unieke gebruiker op te halen uit de db.
     * @param gebruikersnaam, String
     * @throws PersistenceLayerException, wordt afgehandelt in de view.
     */
    private void getDb(String gebruikersnaam) throws PersistenceLayerException {
        query = "select Medewerker.Medewerker_ID, Voornaam, Achternaam, Gebruikersnaam, Wachtwoord, Inlogpoging, Rol.Rol_ID " +
                "from ((Medewerker inner join Medewerker_Rol on Medewerker.Medewerker_ID = Medewerker_Rol.Medewerker_ID) " +
                "inner join Rol on Medewerker_Rol.Rol_ID = Rol.Rol_ID) " +
                "where Gebruikersnaam ='" + gebruikersnaam + "'";
        try {
            connectAndExecute(2);
            if (resultSet.next()) {
                // Als het object nog niet voorkomt in de repo, deze maken en toevoegen.
                medewerkers.add(new Administrator(resultSet.getString(4), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getInt(7), resultSet.getString(5),
                        resultSet.getString(1), resultSet.getInt(6)));
//                MedewerkerRepository#3
//                Gegevens voor één medewerker uit db gehaald, object gecreëerd  en toegevoegd aan repo.
                System.out.println("MedewerkerRepository#3: Pass");
            }
        }
        catch (SQLException e) {
            throw new PersistenceLayerException(e.getMessage());
        }
    }

    /**
     * Methode voor het updaten van Medewerker gegevens in de db
     * @throws PersistenceLayerException, wordt afgehandelt in de view.
     */
    private void updateDataBase() throws PersistenceLayerException {
        query = "";
        for (Medewerker medewerker : medewerkers) {
            query += "UPDATE Medewerker SET Voornaam='" + medewerker.getVoorNaam() +
                    "',Achternaam='" + medewerker.getAchterNaam() +
                    "',Gebruikersnaam='" + medewerker.getGebruikersNaam() +
                    "',Wachtwoord='" + medewerker.getWachtwoord() +
                    "',Inlogpoging=" + medewerker.getInlogPoging() +
                    " WHERE Medewerker_ID=" + medewerker.getMedewerkerID() +
                    " UPDATE Medewerker_Rol SET Rol_ID=" + medewerker.getToegangsNiveau() +
                    " WHERE Medewerker_ID=" + medewerker.getMedewerkerID() + " ";
//            MedewerkerRepository#4
//            Voor iedere medewerker aanwezig in de repo wordt een update query gemaakt en uitgevoerd.
            System.out.println("MedewerkerRepository#4 bouwen update query");
        }
        try {
            connectAndExecute(1);
            System.out.println("MedewerkerRepository#4 Pass");
        }
        catch (SQLException e) {
            throw new PersistenceLayerException(e.getMessage());
        }
    }

    /**
     * Helper methode om connectie met de db te maken, het creëren van een veilige statment en uitvoeren van de juiste query.
     * @param excecuteType, int, om aan te geven of het een update of select query betreft.
     * @throws SQLException, wordt afgehandelt in de view.
     */
    private void connectAndExecute(int excecuteType) throws SQLException {
        connection = DriverManager.getConnection(connectionUrl);
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        if (excecuteType == 1) {
            preparedStatement.executeUpdate();
//            MedewerkerRepository#5
//            Er wordt een update query op de db uitgevoerd.
            System.out.println("MedewerkerRepository#5: Pass");
        } else {
            resultSet = preparedStatement.executeQuery();
//            MedewerkerRepository#6
//            Er wordt een select query op de db uitgevoerd.
            System.out.println("MedewerkerRepository#6: Pass");
        }
    }

    /**
     * Methode om de repo te controleren op aanwezigheid van de gevraagde gebruiker.
     * @param gebruikersNaam String
     * @return Medewerker object
     */
    private Medewerker checkForMedewerker(String gebruikersNaam) {
        Medewerker medewerker = null;
        for (Medewerker m : medewerkers) {
            if (m.getGebruikersNaam().equals(gebruikersNaam)) {
                medewerker = m;
//            MedewerkerRepository#7
//            Als een medewerker in de repo staat wordt deze teruggegeven aan de aanroepende methode.
                System.out.println("MedewerkerRepository#7: Pass");
            }
        }
        return medewerker;
    }

    /**
     * Package private methode om de hele repo op te halen.
     * @return Set van Medewerker objecten
     */
    Set<Medewerker> getMedewerkers() {
        return medewerkers;
    }

    /**
     * Package private methode om de db te updaten met de inhoud van de repo
     * @throws PersistenceLayerException, wordt afgehandelt in de view.
     */
    void updateDb() throws PersistenceLayerException {
        updateDataBase();
    }

    /**
     * Methode om een gebruiker uit de repo of db te halen.
     * @param gebruikersNaam, String
     * @return Medewerker
     * @throws PersistenceLayerException, wordt afgehandelt in de view.
     */
    @Override
    public Medewerker get(String gebruikersNaam) throws PersistenceLayerException {
        Medewerker medewerker;
        // Eerst kijken in de lokale repo
        if (checkForMedewerker(gebruikersNaam) != null) {
            medewerker = checkForMedewerker(gebruikersNaam);
//        MedewerkerRepository#8
//        Een ingelogde medewerker is al aanwezig in de repo, geen db connectie nodig.
            System.out.println("MedewerkerRepository#8: Pass");
        } else {
            // Anders ophalen uit de db.
            getDb(gebruikersNaam);
            medewerker = checkForMedewerker(gebruikersNaam);
//        MedewerkerRepository#9
//        Een ingelogde medewerker is niet aanwezig in de repo, een db connectie wordt opgezet.
            System.out.println("MedewerkerRepository#9: Pass");
        }
        return medewerker;
    }
    /**
     * Publiekelijk toegankelijke methode om alle medewerkers uit de db te halen. Als we wisselen van db dan kan dit gewoon
     * bllijven staan net als alle code die afhankelijk is van deze methode.
     * @return Set van Medewerkers
     * @throws PersistenceLayerException, wordt afgehandelt in de view.
     */
    @Override
    public Set<Medewerker> getAll() throws PersistenceLayerException {
        getAllDb();
        return medewerkers;
    }

    /**
     * Publiekelijk toegankelijke methode om een gebruiker toe te voegen aan de repo en db.
     * @param medewerker Medewerker
     * @throws PersistenceLayerException, wordt afgehandelt in de view.
     */
    @Override
    public void add(Medewerker medewerker) throws PersistenceLayerException {
        // eerst het nieuwe object toevoegen aan de lokale repo.
        medewerkers.add(medewerker);
        // Dan de query uitvoeren op de db om een nieuwe gebruiker toe te voegen, we voeren deze query bewust nu uit
        // omdat we het object direct willen gebruiken op een andere machine / instance van deze applicatie.
        PreparedStatement preparedStatement;
        int result = 0;
        int indexNieuweGebruiker;
        query = "INSERT INTO dbo.Medewerker (Voornaam, Achternaam, Gebruikersnaam, Wachtwoord) " +
                "VALUES ('"+
                medewerker.getVoorNaam() + "','" +
                medewerker.getAchterNaam() + "','" +
                medewerker.getGebruikersNaam() + "','" +
                medewerker.getWachtwoord() + "')";
        try {
            connection = DriverManager.getConnection(connectionUrl);
            preparedStatement = connection.prepareStatement(query, statement.RETURN_GENERATED_KEYS);
            // Execute update bij Insert statements
            indexNieuweGebruiker = preparedStatement.executeUpdate();
            if (indexNieuweGebruiker == 1) {
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    result = resultSet.getInt(1);
                }
            }
        }
        catch (SQLException e) {
            throw new PersistenceLayerException(e.getMessage());
        }
        try {
            query = "INSERT INTO dbo.Medewerker_Rol (Medewerker_ID, Rol_ID) VALUES (" + result + "," +
                    medewerker.getToegangsNiveau() + ")";
            connection = DriverManager.getConnection(connectionUrl);
            preparedStatement = connection.prepareStatement(query);
            // Execute update bij Insert statements
            preparedStatement.executeUpdate();
//        MedewerkerRepository#10
//        Een medewerker wordt toegevoegd aan de repo en toegevoegd aan de persistentie laag van de applicatie.
            System.out.println("MedewerkerRepository#10: Pass");
        }
        catch (SQLException e) {
            throw new PersistenceLayerException(e.getMessage());
        }
    }

    /**
     * Methode om alleen de repo te updaten, deze is een beetje dubbel maar zorgt wel voor minder db verkeer.
     * @param medewerker Medewerker, het oude medewerker object
     * @param updatedMedewerker Medewerker, het nieuwe medewerker object.
     */
    @Override
    public void update(Medewerker medewerker, Medewerker updatedMedewerker) {
        medewerkers.remove(medewerker);
        medewerkers.add(updatedMedewerker);
//        MedewerkerRepository#11
//        Deze methode update de lokale repo.
        System.out.println("MedewerkerRepository#11: Pass");
        // hier kan ook de update van de db, maar dit geeft dan wel veel meer db verkeer.
        // daarom de updateDb methode apart gemaakt zodat deze bewust aangeroepen kan worden.
    }
}
