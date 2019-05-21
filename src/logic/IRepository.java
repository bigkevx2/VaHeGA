package logic;

import java.util.Set;

/**
 * Begin van een eerste opzet van een interface, dit heeft nog werk nodig maar ik ben niet geheel ontevreden met
 * het huidige resultaat.
 */
public interface IRepository {
    void add(Medewerker medewerker) throws PersistenceLayerException;

    void update(Medewerker medewerker, Medewerker updatedMedewerker);

    Medewerker get(String gebruikersNaam) throws PersistenceLayerException;

    Set<Medewerker> getAll() throws PersistenceLayerException;
}
