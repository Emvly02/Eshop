package bib.main.entities;

public class Benutzer {
    protected int id;
    private String benutzerName;
    private String passwort;
    protected String vorname;
    protected String nachname;
    protected String strasse;
    protected int hausnr;
    protected String ort;
    protected int plz;
    protected String land;

    public Benutzer(String vorname, String nachname, String passwort, int id, String strasse, int hausnr, String ort, int plz, String land) {
        this.vorname = vorname;
        this.nachname = nachname;
        this.passwort = passwort;
        this.benutzerName = vorname + nachname;
        this.id = id;
        this.strasse = strasse;
        this.hausnr = hausnr;
        this.ort = ort;
        this.land = land;
        this.plz = plz;
    }


    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getPasswort() {
        return this.passwort;
    }

    public String getBenutzerName() {
        return benutzerName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStrasse() {
        return strasse;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    public int getHausnr() {
        return hausnr;
    }

    public void setHausnr(int hausnr) {
        this.hausnr = hausnr;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public int getPlz() {
        return plz;
    }

    public void setPlz(int plz) {
        this.plz = plz;
    }

    public String getLand() {
        return land;
    }

    public void setLand(String land) {
        this.land = land;
    }
}
