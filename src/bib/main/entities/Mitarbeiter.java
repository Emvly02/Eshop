package bib.main.entities;

public class Mitarbeiter extends Benutzer {
    public Mitarbeiter(String vorname, String nachname, int ID, String passwort, String strasse, int hausnr, String ort, int plz, String land) {
        super(vorname, nachname, passwort, ID, strasse, hausnr, ort, plz, land);
    }

    @Override
    /*public String toString() {
        return String.format("%s: %s, %s, %s", vorname, nachname, String.valueOf(id));
    }

     */

    public boolean equals(Object andererMitarbeiter) {
        if (andererMitarbeiter instanceof Mitarbeiter) {
            return ((this.id == ((Mitarbeiter) andererMitarbeiter).id)
                    && (this.vorname.equals(((Mitarbeiter) andererMitarbeiter).vorname)));
        }
        else {
            return false;
        }
    }



    public String getNachname() {
        return nachname;
    }


    public String getVorname() {
        return vorname;
    }


}
