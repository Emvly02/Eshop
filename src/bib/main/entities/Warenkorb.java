package bib.main.entities;

import java.util.HashMap;
import java.util.Map;

public class Warenkorb {

    private Map<Artikel, Integer> inhalt = new HashMap<>();
    private Map<MassengutArtikel, Integer> inhaltM = new HashMap<>();

    public void artikelHinzufuegen(Artikel artikel, int anzahl){
        if(!inhalt.containsKey(artikel)) {
            inhalt.put(artikel, anzahl);
        }
        else{
            inhalt.put(artikel, + 1);
        }
    }

    public void MassenartikelHinzufuegen(MassengutArtikel mArtikel, int anzahl){
        if(!inhaltM.containsKey(mArtikel)) {
            int vielfaches = (int) Math.ceil((double) mArtikel.getBestand() / mArtikel.getPackungsgroesse());
            inhaltM.put(mArtikel, anzahl);
        }
        else{
            inhaltM.put(mArtikel, + 1);
        }
    }



    public void aendereAnzahl(Artikel artikel, int anzahl){
        inhalt.put(artikel,anzahl);
    }

    public void artikelEntfernen(Artikel artikel){
        inhalt.remove(artikel);
    }

    public void warenkorbLeeren(){
        inhalt.clear();
    }

    public Map kaufen(){
        Map<Artikel, Integer> rechnung = inhalt;
        return rechnung;
    }



    public Map<Artikel, Integer> getInhalt() {
        return inhalt;
    }
}
