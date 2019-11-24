package cz.vse.java.pryp00.adventura.logika;

import cz.vse.java.pryp00.adventura.main.IObserver;
import cz.vse.java.pryp00.adventura.main.ISubject;
import java.util.HashSet;
import java.util.Set;


public class HerniPlan implements ISubject {
    public static boolean hracJeOblecen = false;
    private Prostor aktualniProstor;
    private Prostor viteznyProstor;

    private Set<IObserver> seznamPozorovatelu = new HashSet<>();
    
     /**
     *  Konstruktor který vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví halu.
     */
    public HerniPlan() {
        zalozProstoryHry();
    }

    /**
     *  Vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví pokoj.
     */
    private void zalozProstoryHry() {
        // vytvářejí se jednotlivé prostory
        Prostor pokoj = new Prostor("pokoj", "pokoj, ve kterém bydlí hráč");
        Prostor dvur = new Prostor("dvur", "dvůr domu, ve kterém bydlí hráč");
        Prostor ulice = new Prostor("ulice", "ulice, ve které bydlí hráč");
        Prostor slepaUlice = new Prostor("slepa_ulice", "vedlejší ulice, která nikam nevede");
        Prostor park = new Prostor("park", "park, vedle kterého bydlí váš kamarád");
        Prostor kamaraduvByt = new Prostor("kamaraduv_byt", "byt kamaráda, který tě pozval pařit");

        // přiřazují se průchody mezi prostory (sousedící prostory)
        pokoj.setVychod(dvur);
        dvur.setVychod(pokoj);
        dvur.setVychod(ulice);
        ulice.setVychod(dvur);
        ulice.setVychod(park);
        ulice.setVychod(slepaUlice);
        slepaUlice.setVychod(ulice);
        park.setVychod(ulice);
        park.setVychod(kamaraduvByt);
        kamaraduvByt.setVychod(park);
                
        aktualniProstor = pokoj;  // hra začíná v pokoji
        viteznyProstor = kamaraduvByt ;

        // prirazeni veci do prostoru
        pokoj.vlozVec(new Vec("obleceni", true));
        pokoj.vlozVec(new Vec("penezenka", true));
        pokoj.vlozVec(new Vec("klice", true));

        dvur.vlozVec(new Vec("postovni_schranka", false));
        dvur.vlozVec(new Vec("dopis", true));

        ulice.vlozVec(new Vec("auta", false));
        ulice.vlozVec(new Vec("rohlik", true));

        park.vlozVec(new Vec("strom", false));
        park.vlozVec(new Vec("casopis", true));

        kamaraduvByt.vlozVec(new Vec("pivo", true));
        kamaraduvByt.vlozVec(new Vec("pizza", true));
    }
    
    /**
     *  Metoda vrací odkaz na aktuální prostor, ve ktetém se hráč právě nachází.
     *@return     aktuální prostor
     */
    public Prostor getAktualniProstor() {
        return aktualniProstor;
    }
    
    /**
     *  Metoda nastaví aktuální prostor, používá se nejčastěji při přechodu mezi prostory
     *@param  prostor nový aktuální prostor
     */
    public void setAktualniProstor(Prostor prostor) {
       aktualniProstor = prostor;
       notifyObservers();
    }

    /**
     *  Metoda vrací odkaz na vítězný prostor.
     *@return     vítězný prostor
     */
    public Prostor getViteznyProstor() {
        return viteznyProstor;
    }

    /**
     * Registrace pozorovatele změn
     * @param observer pozorovatel změn
     */
    @Override
    public void registerObserver(IObserver observer) {
        seznamPozorovatelu.add(observer);
    }

    /**
     * Odebrání ze seznamu pozorovatelů
     * @param observer pozorovatel změn
     */
    @Override
    public void unregisterObserver(IObserver observer) {
        seznamPozorovatelu.remove(observer);
    }

    /**
     * Upozornění registrovaných pozorovatelů na změnu
     */
    @Override
    public void notifyObservers() {
        for(IObserver observer : seznamPozorovatelu) {
            observer.update();
        }
    }
}