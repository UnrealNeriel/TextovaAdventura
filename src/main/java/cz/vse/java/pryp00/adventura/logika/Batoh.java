package cz.vse.java.pryp00.adventura.logika;

import cz.vse.java.pryp00.adventura.main.IObserver;
import cz.vse.java.pryp00.adventura.main.ISubject;

import java.util.*;

/**
 * Třída batoh
 * představuje uložiště pro {@link Vec} behem hry pro hrace
 */
public class Batoh implements ISubject
{
private Map<String, Vec> seznamVeci ;   // seznam věcí v batohu
private Set<IObserver> seznamPozorovatelu = new HashSet<>();

public Batoh () {
seznamVeci = new HashMap<String, Vec>();
}
    /**
     * Vloží věc do batohu
     *
     *@param  vec  instance věci, která se má vložit
     */
   public void vlozVec (Vec vec) {
     seznamVeci.put(vec.getJmeno(),vec);
    }
     /**
     * Vrací řetězec názvů věcí, které jsou v batohu
     *@return řetězec názvů
     */
    public String nazvyVeci () {
        String nazvy = "věci v batohu: ";
        for (String jmenoVeci : seznamVeci.keySet()){
            	nazvy += jmenoVeci + " ";
        }
        return nazvy;
    }
     /**
     * Hledá věc daného jména a pokud je v batohu, tak ji vrátí a vymaže ze seznamu
     *@param  jmeno   Jméno věci
     *@return            věc nebo hodnota null, pokud tam věc daného jména není
     */
    public Vec vyberVec (String jmeno) {
        Vec nalezenaVec = null;
        if (seznamVeci.containsKey(jmeno)) {
            nalezenaVec = seznamVeci.get(jmeno);
            seznamVeci.remove(jmeno);
        }   
        return nalezenaVec;
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
        for (IObserver observer : seznamPozorovatelu) {
            observer.update();
        }
    }

    /**
     * @return set, ktery ma klice hash mapy.
     */
    public Set<String> vratVeci() {
        return this.seznamVeci.keySet();
    }
}