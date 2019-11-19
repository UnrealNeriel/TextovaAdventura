package cz.vse.java.pryp00.adventura.main;

public interface ISubject {
    /**
     * Registrace pozorovatele změn
     * @param observer
     */
    void registerObserver(IObserver observer);

    /**
     * Odebrání ze seznamu pozorovatelů
     * @param observer
     */
    void unregisterObserver(IObserver observer);

    /**
     * Upozornění registrovaných pozorovatelů na změnu
     */
    void notifyObservers();

}
