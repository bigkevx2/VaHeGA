import java.util.ArrayList;

public abstract class Order {
    private ArrayList<ProductGroep> artikel;
    private Klant klant;

    public Order(ArrayList<ProductGroep> artikel, Klant klant) {
        this.artikel = artikel;
        this.klant = klant;
    }

    public void bepaalKlantCondities() {

    }

    public void orderOpslaan() {

    }

}
