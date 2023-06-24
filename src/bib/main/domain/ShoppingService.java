package bib.main.domain;

import bib.main.entities.Artikel;
import bib.main.entities.Kunde;
import bib.main.entities.MassengutArtikel;
import bib.main.entities.Warenkorb;

import java.util.Map;

public class ShoppingService {

    public void artikelInWarenkorbLegen(Kunde kunde, Artikel artikel, int anzahl) {
        Warenkorb korb = kunde.getKorb();
            korb.artikelHinzufuegen(artikel, anzahl);
    }

    public void massenartikelInWarenkorbLegen(Kunde kunde, MassengutArtikel mArtikel, int anzahl) {
        Warenkorb korb = kunde.getKorb();
        korb.MassenartikelHinzufuegen(mArtikel, anzahl);
    }


    public void artikelentfernen(Kunde kunde, Artikel artikel){
        Warenkorb korb = kunde.getKorb();
        korb.artikelEntfernen(artikel);
    }

    public void artikelKaufen(Kunde kunde){
        Warenkorb korb = kunde.getKorb();
        korb.kaufen();
    }

    public void warenkorbAusleeren(Kunde kunde){
        Warenkorb korb = kunde.getKorb();
        korb.warenkorbLeeren();
    }

    public Map warenkorbAuflisten(Kunde kunde){
        Warenkorb korb = kunde.getKorb();
        return korb.getInhalt();
    }

    public Map rechnungAnzeigen(Kunde kunde){
        Map rechnung = kunde.getRechnung();
        return rechnung;
    }


}
