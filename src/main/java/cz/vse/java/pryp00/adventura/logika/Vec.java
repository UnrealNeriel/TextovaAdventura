package cz.vse.java.pryp00.adventura.logika;

/**
 * Třída Vec - představuje vec ve hře, se kterou můžete nějakým způsobem zacházet
 */
public class Vec
{
    private String jmeno;
    private boolean prenositelna;

	/**
	 * Kontruktor tridy veci
	 * @param jmeno jednoznacny nazev veci
	 * @param prenositelna oznacuje zda hrac muze vec polozit do batohu ci nikoliv
	 */
	public Vec (String jmeno, boolean prenositelna) {
		this.jmeno = jmeno;
		this.prenositelna = prenositelna;
	}

	/**
	 * Metoda vraci nazev veci
	 * @return nazev veci
	 */
    public String getJmeno () {
		return jmeno;
	}

	/**
	 * Metoda vraci prenositelnost veci
	 * @return prenositelnost veci
	 */
	public boolean jePrenositelna() {
		return prenositelna;
	}
}