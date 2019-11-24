package cz.vse.java.pryp00.adventura.logika;

/**
 *  Třída PrikazObsahBatohu implementuje pro hru příkaz obsahBatohu.
 */
public class PrikazObsahBatohu implements IPrikaz
{
    private static final String NAZEV = "obsahBatohu";
        private Batoh batoh;

        /**
         *  Konstruktor třídy
         */
    public PrikazObsahBatohu( Batoh batoh) {
            this.batoh = batoh;
        }

        /**
         *  Provádí příkaz "obsahBatohu". Vypíše názvy věcí v batohu
         *
         *@return zpráva, kterou vypíše hra hráči
         */
        public String proved(String... parametry) {
            return batoh.nazvyVeci();
    }

    /**
     * Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     * @ return nazev prikazu
     */
    public String getNazev() {
        return NAZEV;
    }
}