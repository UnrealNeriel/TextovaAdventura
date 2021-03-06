package cz.vse.java.pryp00.adventura.logika;

/**
 *  Třída PrikazObleknout implementuje pro hru příkaz obleknout.
 */
public class PrikazObleknout implements IPrikaz
{

    private static final String NAZEV = "obleknout";
    private HerniPlan plan;
    private Batoh batoh;

    /**
     *  Konstruktor třídy
     */
    public PrikazObleknout(HerniPlan plan, Batoh batoh) {
        this.plan = plan;
        this.batoh = batoh;
    }

    /**
     * Provadi prikaz "obleknout", ktery pak premeni promennou na true, ktera nesledne
     * dovoli hraci jit do vsech prostoru
     *
     * @param parametry je potreba prave 1 parametr, vec ktere chcete obleknout
     * @return zpráva, kterou vypíše hra hráči
     */
    @Override
    public String proved(String... parametry) {
        if (parametry.length == 0) {
            // pokud chybí druhé slovo , tak ....
            return "Co mám obléknout? Musíš zadat jméno věci";
        }

        String jmenoVeci = parametry[0];

        // vybereme věc
        Vec vec = batoh.vyberVec(jmenoVeci);

        if (vec == null) {
            return "Taková věc v batohu není ";
        } else if (vec.getJmeno().compareTo("obleceni") == 0) {
            // oblekneme obleceni
            plan.hracJeOblecen = true;
            return "Obléknul jsi " + jmenoVeci;
        } else {
            batoh.vlozVec(vec);
            return "Tohle obléknout nelze ";
        }
    }

    /**
     * Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     * @ return nazev prikazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }
}