package bib.main.domain;

import bib.main.domain.exceptions.MassengutArtikelGibtEsSchon;
import bib.main.entities.Artikel;
import bib.main.entities.MassengutArtikel;

import java.util.ArrayList;
import java.util.List;

public class MassengutArtikelVerwaltung {
 /*  Massengutartikel aufnehmen
        Liste MassengutArtikelBestand
        Massengutartikel aus Bestand nehmen

     */

    private ArrayList<MassengutArtikel> MassengutBestand = new ArrayList<MassengutArtikel>();

    public MassengutArtikelVerwaltung(){
        MassengutBestand.add(new MassengutArtikel( "ColaDosen", 16, 4.99f, 53, 6));
    }

    public void massengutAufnehmen(MassengutArtikel mArtikel) throws MassengutArtikelGibtEsSchon {
        if (!MassengutBestand.contains(mArtikel)) {
            MassengutBestand.add(mArtikel);
        }
    }

    public void MassengutEntfernen(Artikel mArtikel) {
        MassengutBestand.remove(mArtikel);
    }

    public ArrayList<MassengutArtikel> getMassengutBestand() {
        return MassengutBestand;
    }

    public List<MassengutArtikel> sucheMassengutArtikel(String bezeichnung) {
        List<MassengutArtikel> suchErg = new ArrayList<>();
        for (MassengutArtikel aktMartikel : MassengutBestand) {
            if (aktMartikel.getBezeichnung().equals(bezeichnung)) {
                suchErg.add(aktMartikel);
            }
        }
        return suchErg;
    }

    public void MassengutInWarenkorbTun(MassengutArtikel m){
       // int vielfaches = (int) Math.ceil((double) m.getBestand() / m.getPackungsgroesse());

    }


}

