import java.io.File;
import java.util.ArrayList;

public class Digitaal extends Order {
    private File csvFile;

    public Digitaal(ArrayList<ProductGroep> artikel, Klant klant, File csvFile) {
        super(artikel, klant);
        this.csvFile = csvFile;
    }

    public void showFilePicker() {

    }
}
