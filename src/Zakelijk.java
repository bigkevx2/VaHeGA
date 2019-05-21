public abstract class Zakelijk extends Klant {
    private int kvkNummer;
    private String contactPersoon;
    private String userName;
    private String password;

    public Zakelijk(Naw nawGegevens, int kvkNummer, String contactPersoon, String userName, String password) {
        super(nawGegevens);
        this.kvkNummer = kvkNummer;
        this.contactPersoon = contactPersoon;
        this.userName = userName;
        this.password = password;
    }

    public int getKvkNummer() {
        return kvkNummer;
    }

    public void setKvkNummer(int kvkNummer) {
        this.kvkNummer = kvkNummer;
    }

    public String getContactPersoon() {
        return contactPersoon;
    }

    public void setContactPersoon(String contactPersoon) {
        this.contactPersoon = contactPersoon;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
