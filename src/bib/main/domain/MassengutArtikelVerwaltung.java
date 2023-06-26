package bib.main.domain;

import bib.main.domain.exceptions.MassengutArtikelGibtEsSchon;
import bib.main.entities.Artikel;
import bib.main.entities.MassengutArtikel;

import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
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
        try{
            List<String> Stammdaten = Files.readAllLines(Paths.get(String.format("%s\\massengutArtikel", System.getProperty("user.dir"))));
            for(int i=0; i<Stammdaten.size(); i++){
                String[] Arg = Stammdaten.get(i).split(",");
                MassengutBestand.add(new MassengutArtikel(Arg[0], Integer.parseInt(Arg[1]), Float.parseFloat(Arg[2]), Integer.parseInt(Arg[3]), Integer.parseInt(Arg[4])));
            }
        }
        catch(Exception Ausnahme){
        }
    }

    public void massengutAufnehmen(MassengutArtikel mArtikel) throws MassengutArtikelGibtEsSchon {
        if (!MassengutBestand.contains(mArtikel)) {
            MassengutBestand.add(mArtikel);
            try{
                List<String> Stammdaten = Files.readAllLines(Paths.get(String.format("%s\\massengutArtikel", System.getProperty("user.dir"))));
                Stammdaten.add(String.format("%s,%s,%s,%s", mArtikel.getBezeichnung(), mArtikel.getArtikelNr(), mArtikel.getPreis(), mArtikel.getBestand(), mArtikel.getPackungsgroesse()));

                FileWriter fileWriter = new FileWriter(String.format("%s\\massengutArtikel", System.getProperty("user.dir")));
                for(String aktuelleZeile : Stammdaten){
                    fileWriter.write(String.format("%s\n", aktuelleZeile));
                }
                fileWriter.close();
            }
            catch(Exception Ausnahme){
            }
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

