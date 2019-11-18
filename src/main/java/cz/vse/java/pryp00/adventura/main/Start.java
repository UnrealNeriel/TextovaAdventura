/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package cz.vse.java.pryp00.adventura.main;

import cz.vse.java.pryp00.adventura.logika.*;
import cz.vse.java.pryp00.adventura.uiText.TextoveRozhrani;

/**
 * Třída {@code Start} je hlavní třídou projektu,
 * který ...
 */
public class Start
{
    /**
     * Metoda, prostřednictvím níž se spouští celá aplikace.
     * @param args Parametry příkazového řádku
     */
    public static void main(String[] args)
    {
        try {
             System.out.println(args[0]);
             System.exit(0);
        } catch (ArrayIndexOutOfBoundsException e) {
            IHra hra = new Hra();
            TextoveRozhrani ui = new TextoveRozhrani(hra);
            ui.hraj();
        }
    }
}