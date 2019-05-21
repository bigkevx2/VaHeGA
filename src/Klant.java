public abstract class Klant {
    private Naw nawGegevens;

    public Klant(Naw nawGegevens) {
        this.nawGegevens = nawGegevens;
    }

    public Naw getNawGegevens() {
        return nawGegevens;
    }

    public void setNawGegevens(Naw nawGegevens) {
        this.nawGegevens = nawGegevens;
    }
}
