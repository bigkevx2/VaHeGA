public class Groothandel extends Zakelijk {
    private int minimumAfname;
    private double korting;

    public Groothandel(Naw nawGegevens, int kvkNummer, String contactPersoon, String userName, String password, int minimumAfname, double korting) {
        super(nawGegevens, kvkNummer, contactPersoon, userName, password);
        this.minimumAfname = minimumAfname;
        this.korting = korting;
    }

    public double getKorting() {
        return korting;
    }

    public void setKorting(double korting) {
        this.korting = korting;
    }

    public int getMinimumAfname() {
        return minimumAfname;
    }

    public void setMinimumAfname(int minimumAfname) {
        this.minimumAfname = minimumAfname;
    }
}
