import UI.Frame;
import logic.Bedrijf;

public class VaHeGA {
    /**
     * Start van de applicatie, tijdens de start worden twee objecten aangemaakt die door de hele app gebruikt gaan worden.
     * Dit zijn Bedrijf en Frame. Voor iedere gebruiker zal binnen de applicatie dezelfde instantie van deze objecten gebruikt worden.
     * @param args String[]
     */
    public static void main(String[] args) {
        Bedrijf bedrijf = new Bedrijf();
        Frame frame = new Frame(bedrijf);
    }

}
