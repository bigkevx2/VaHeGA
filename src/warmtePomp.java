public class warmtePomp extends ProductGroep {
    private double inkoopPrijs;

    public warmtePomp(Double verkoopPrijs, int levertijd, double inkoopPrijs) {
        super(verkoopPrijs, levertijd);
        this.inkoopPrijs = inkoopPrijs;
    }

    public double getInkoopPrijs() {
        return inkoopPrijs;
    }

    public void setInkoopPrijs(double inkoopPrijs) {
        this.inkoopPrijs = inkoopPrijs;
    }
}
