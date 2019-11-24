package cz.vse.java.pryp00.adventura.logika;

import cz.vse.java.pryp00.adventura.main.IObserver;
import cz.vse.java.pryp00.adventura.main.ISubject;

import java.util.*;

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

    @Override
    public void registerObserver(IObserver observer) {
        seznamPozorovatelu.add(observer);
    }

    @Override
    public void unregisterObserver(IObserver observer) {
        seznamPozorovatelu.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (IObserver observer : seznamPozorovatelu) {
            observer.update();
        }
    }

    public Set<String> vratVeci() {
        return this.seznamVeci.keySet();
    }
}