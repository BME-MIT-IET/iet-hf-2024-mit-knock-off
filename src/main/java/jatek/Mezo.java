package jatek;

import java.util.ArrayList;
import java.util.List;
/**
 * A játékban lévő Mezőket reprezentáló absztrakt osztály.
 * A csöveket és csúcsokat kezeli egy egységben a játékosok mozgása szempontjából.
 * A játékosok mezőkön állnak és ezeknek a szomszédaira mozoghatnak.
 */
public abstract class Mezo {
	/**
	 * A rajta álló játékosok listája.
	 */
	private List<Jatekos> jatekosRajta;

	/**
	 * Azt jelzi, hogy az adott elem tartalmaz-e vizet
	 */
	private boolean vanViz;

	/**
	 * Létrehoz egy mezőt, és inicializálja a jatekosRajta listát.
	 */
	public Mezo(){
		jatekosRajta = new ArrayList<Jatekos>();
	}

	/**
	 * Kap egy játékost a mező. Leszármazottakban eldől, hogy elfogadja-e.
	 * @param j Játékos aki érkezik.
	 * @return Igazat ad vissza ha elfogadja a játékost.
	 */
	public abstract boolean jatekostElfogad(Jatekos j);

	public void setVanViz(boolean viz){
		vanViz = viz;
	}

	/**
	 * Eltávolítja a paramétkent kapott játekost a játekosRajta listából.
	 * @param j A játékos, akit el akarunk távolítani a listaból.
	 */
	public void jatekostEltavolit(Jatekos j){
		jatekosRajta.remove(j);
	}

	/**
	 * Az adott mezőre lerakja a csövet, leszármazottak felülírhatják.
	 * @param cs A kapott cső
	 */
	public boolean targyLerakas(Cso cs){return false;}

	/**
	 * Az adott mezőre lerakja a pumpát, leszármazottak felülírhatják.
	 * @param p A kapott pumpa
	 */
	public boolean targyLerakas(Pumpa p){return false;}

	/**
	 * A szerelő megjavít egy mezőt. Üres függvény, mivel a Mezo nem tud megjavulni.
	 * Azon leszármazottakban, amiket meg lehet javítani meg kell valósítani.
	 */
	public void szereloJavit(){}

	/**
	 * Egy játékos elrontja a mezőt. Üres függvény, mivel a Mezo nem tud elromlani.
	 * Azon leszármazottakban, amiket el lehet rontani meg kell valósítani.
	 */
	public void jatekosElront(){}

	/**
	 * A mező létrehoz egy pumpát. Null-al tér vissza alapesetben, mivel a Mezo nem hozhat létre pumpát.
	 * Azon leszármazottakban amik létrehoznak, meg kell valósítani.
	 * @return A létrejött pumpa
	 */
	public Pumpa pumpaLetrehozasa(){
		return null;
	}

	/**
	 * Jelezzük a mezőnek, hogy szeretnénk a hozzá kapcsolódó cs-edik csövet megkapni.
	 * Mivel nem minden Mezo-nek vannak szomszédos csövei, alapesetben Null-al tér vissza.
	 * @param cs A szomszédos csövek közül melyiket szeretnénk megkapni.
	 * @return A mező által visszaadott cső, vagy null.
	 */
	public Cso adjCsovet(int cs){
		return null;
	}

	/**
	 * Visszaadja a mezővel szomszédos mezők listáját.
	 * @return A szomszédos mezők listája.
	 */
	public abstract List<? extends Mezo> getNeighbours();

	/**
	 * Egy Jatekos átállítja a mezőt. Üres függvény, mivel a Mezo nem átállítható.
	 * Azon leszármazottakban amik átállíthatók, meg kell valósítani.
	 * @param kimeneti bemeneti cső index a szomszédos mezőkhöz
	 * @param bemeneti kimeneti cső index a szomszédos mezőkhöz
	 */
	public void atallit(int kimeneti, int bemeneti){}

	/**
	 * Megváltoztatják a mező állapotát.
	 * Üres függvény, azon leszármazottakban, amik állapota változtatható meg kell valósítani.
	 * @param allapot az állapot, amire változik
	 */
	public void allapotValtozas(Allapot allapot){}

	/**
	 * Rarakja a jatekost
	 * @param j A jatekos
	 */
	public void setJatekosRajta(Jatekos j){
		jatekosRajta.add(j);
	}

	/*
	 * Visszaadja rajtalevo jatekosok listajat
	 */
	public List<Jatekos> getJatekosRajta(){
		return jatekosRajta;
	}

	/*
	 * visszaadja hogy van e rajta viz
	 */
	public boolean getVanViz(){
		return vanViz;
	}

	/*
	 * ballitja, hogy determinisztikusan mukodjon-e (nem csinal semmit csak nehany leszarmazottban)
	 */
	public void setDet(Csucs csucs){
	}

}
