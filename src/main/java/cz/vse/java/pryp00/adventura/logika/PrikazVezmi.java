package cz.vse.java.pryp00.adventura.logika;

/**
 *  Třída PrikazVezmi implementuje pro hru příkaz seber.
 */
public class PrikazVezmi implements IPrikaz
{
private static final String NAZEV = "vezmi";
    private HerniPlan plan;
    private Batoh batoh;

    /**
    *  Konstruktor třídy
    *  @param plan herní plán, ve kterém se bude hledat aktuální místnost
    */      
    public PrikazVezmi(HerniPlan plan, Batoh batoh) {
        this.plan = plan;
        this.batoh = batoh;
    }

    /**
     *  Provádí příkaz "seber". V aktuální místnosti hledá věc, která je předána jako parametr
     *  
     *
     *@param parametry - jako  parametr obsahuje jméno věci,
     *                          která se má sebrat.
     *@return zpráva, kterou vypíše hra hráči
     */ 
    public String proved(String... parametry) {
        if (parametry.length == 0) {
            // pokud chybí druhé slovo , tak ....
            return "Co mám sebrat? Musíš zadat jméno věci";
        }

        String jmenoVeci = parametry[0];

        // vybereme věc
        Vec vec = plan.getAktualniProstor().vyberVec(jmenoVeci);

        if (vec == null) {
            return "Taková věc tu není a nebo ji nemůžeš vzít!";
        } else {
	     // uložíme věc do batohu	
	        batoh.vlozVec(vec);
            return "Sebral jsi " + jmenoVeci;
        }
    }

    /**
     * Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     * @ return nazev prikazu
     */
    public String getNazev() {
        return NAZEV;
    }
}