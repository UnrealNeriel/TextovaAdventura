package cz.vse.java.pryp00.adventura.logika;

/**
 * Třída Vec - představuje vec ve hře, se kterou můžete nějakým způsobem zacházet
 */
public class Vec
{
    private String jmeno;
    private boolean prenositelna;

    public Vec (String jmeno, boolean prenositelna) {
		this.jmeno = jmeno;
		this.prenositelna = prenositelna;
	}

    public String getJmeno () {
		return jmeno;
	}

	public boolean jePrenositelna() {
		return prenositelna;
	}
}