public abstract class ProductGroep {
    private Double verkoopPrijs;
    private int levertijd;

    public ProductGroep(Double verkoopPrijs, int levertijd) {
        this.verkoopPrijs = verkoopPrijs;
        this.levertijd = levertijd;
    }

    public void plaatsProductieOrder() {

    }

    public Double getVerkoopPrijs() {
        return verkoopPrijs;
    }

    public void setVerkoopPrijs(Double verkoopPrijs) {
        this.verkoopPrijs = verkoopPrijs;
    }

    public int getLevertijd() {
        return levertijd;
    }

    public void setLevertijd(int levertijd) {
        this.levertijd = levertijd;
    }
}
