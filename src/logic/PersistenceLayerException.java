package logic;

/**
 * Class die een custom Exception maakt om de persistentielaag specifieke Exceptions door te geven als generieke
 * persistentielaag Exceptions. Dus onafhankelijk van welke persistentielaag gebruikt wordt wordt er altijd dezelfde Exception
 * doorgegeven zodat de code daar niet op aangepast hoeft te worden.
 */
public class PersistenceLayerException extends Exception {
    public PersistenceLayerException(String e) {
        super(e);
    }
}
