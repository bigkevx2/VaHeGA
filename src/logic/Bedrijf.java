package logic;

/**
 * Classe die dient als centraal punt door de hele applicatie, de ingelogde medewerker wordt hier in opgeslagen,
 * en bij het verlaten, afsluiten van de applicatie wordt hier de call gegeven om de db nog bij te werken.
 */
public class Bedrijf {
    private Medewerker medewerker;
    private MedewerkerRepository medewerkerRepository;

    /**
     * Constructor die ervoor zorgt dat Bedrijf de MedewerkerRepository creëert.
     */
    public Bedrijf() {
        medewerkerRepository = new MedewerkerRepository();
    }

    /**
     * Methode die wordt aangeroepen tijdens het inlog proces en het afsluiten van de applicatie.
     * @throws PersistenceLayerException, wordt afgehandelt in de view.
     */
    public void updateRepositoryToDb() throws PersistenceLayerException {
//        Wordt in MedewerkerRepository getest.
        medewerkerRepository.updateDb();
    }

    /**
     * Methode voor het updaten van de repo, wordt gebruikt bij het inlog proces.
     * @param medewerker, het medewerker object voor bewerking.
     * @param updatedMedewerker, het medewerker object na bewerking.
     */
    public void update(Medewerker medewerker, Medewerker updatedMedewerker) {
//        Wordt in MedewerkerRepository getest.
        medewerkerRepository.update(medewerker, updatedMedewerker);
    }

    /**
     * Methode om de medewerker op te vragen die inglogd is.
     * @return een Medewerker object.
     */
    public Medewerker getMedewerker() {
        return medewerker;
    }

    /**
     * Methode voor het ophalen van de ingelogde gebruiker uit de repo.
     * @param gebruikersNaam, String
     * @return object van een Medewerker
     * @throws PersistenceLayerException, wordt afgehandelt in de view
     */
    public Medewerker setMedewerker(String gebruikersNaam) throws PersistenceLayerException {
        medewerker = medewerkerRepository.get(gebruikersNaam);
        // Als de medewerker admin rechten heeft dan moet hij ook met de repo communiceren, dit moet dan wel dezelfde instantie zijn.
        if (medewerker instanceof Administrator) {
            ((Administrator) medewerker).setMedewerkerRepository(this.medewerkerRepository);
//  Bedrijf#1
//  Bedrijf communiceert met een instance van MedewerkerRepository, als een admin inlogt moet deze met dezelfde repo communiceren.
//  Dit wordt gecontroleerd door te kijken van welke klasse het medewerker object wordt aangemaakt.
            System.out.println("Bedrijf#1: Pass");
        }
        return medewerker;
    }


}
