/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package cz.vse.java.pryp00.adventura.logika;


/**
 *  Rozhraní které musí implementovat hra, je na ně navázáno uživatelské rozhraní
 */
public interface IHra
{
    /**
     *  Vrátí úvodní zprávu pro hráče.
     */
    public String vratUvitani();
    
    /**
     *  Vrátí závěrečnou zprávu pro hráče.
     */
    public String vratEpilog();
    
    /** 
     * Vrací true, pokud hra skončila.
     */
     public boolean konecHry();
     
      /**
     *  Metoda zpracuje řetězec uvedený jako parametr, rozdělí ho na slovo příkazu a další parametry.
     *  Pak otestuje zda příkaz je klíčovým slovem  např. jdi.
     *  Pokud ano spustí samotné provádění příkazu.
     *
     *@param  radek  text, který zadal uživatel jako příkaz do hry.
     *@return          vrací se řetězec, který se má vypsat na obrazovku
     */
     public String zpracujPrikaz(String radek);

    /**
     *  Metoda vrátí odkaz na herní plán,
     *  jejím prostřednictvím získává aktualní místnost hry.
     *  @return     odkaz na herní plán
     */
     public HerniPlan getHerniPlan();

    /**
     * Metoda vraci batoh
     * @return Batoh
     */
     public Batoh getBatoh();
}