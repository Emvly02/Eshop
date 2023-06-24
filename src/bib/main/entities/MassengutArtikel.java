package bib.main.entities;

public class MassengutArtikel extends Artikel {
    //Massengutartikel haben
    //eine individuelle Packungsgröße, so dass Ein- und Auslagerungen nur in Vielfachen dieser Packungsgröße
    //vorgenommen werden können

    int packungsgroesse;

    public  MassengutArtikel(String bezeichnung, int nr, float preis, int bestand, int packungsgroesse) {
        super(bezeichnung, nr, preis, bestand);
        this.packungsgroesse = packungsgroesse;
    }

    public MassengutArtikel(String bezeichnung, int nr, int packungsgroesse) {
        super(bezeichnung, nr);
        this.packungsgroesse = packungsgroesse;
    }

    public int getPackungsgroesse() {
        return packungsgroesse;
    }

    public void setPackungsgroesse(int packungsgroesse) {
        this.packungsgroesse = packungsgroesse;
    }

}
