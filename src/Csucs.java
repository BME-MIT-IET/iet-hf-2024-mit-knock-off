import java.util.ArrayList;
import java.util.List;

/**
 * A játékban lévő Csúcsokat reprezentáló absztrakt osztály.
 */
public abstract class Csucs extends Mezo{
    /**
     * A szomszédos csövek listája.
     */
    private List<Cso> szomszedosCso;

    /**
     * Létrehoz egy Csucsot, és inicializálja a szomszedosCso listát.
     */
    public Csucs(){
        super();
        szomszedosCso = new ArrayList<Cso>();
    }

    /**
     * Hozzáad egy csövet a szomszedosCso listához
     * @param cso
     * A hozzáadott cső
     */
    public void addCso(Cso cso){
        szomszedosCso.add(cso);
    }

    public void setRossz(boolean r){

    }
    public void setBemenetiCso(Cso cso){

    }
    public void setKimenetiCso(Cso cso){

    }

    /**
     * Etávolítja a megadott csövet a szomszedosCso listából
     * @param cso
     * Az eltávolítandó cső
     */
    public void removeNeighbour(Cso cso){
        szomszedosCso.remove(cso);
    }
    /**
     * Kap egy játékost és hozzáadja a jatekosRajta listához.
     * @param j A játékos, akit hozzáadunk a listához.
     * @return Igazat ad vissza minden esetben, hogy tájokoztassuk a hívót a hozzáadásról.
     */
    @Override
    public boolean jatekostElfogad(Jatekos j) {
        getJatekosRajta().add(j);
        return true;
    }

    /**
     * A kapott játékost eltávolítja a jatekosRajta listából.
     * @param j A játékos, akit el akarunk távolítani a listaból.
     */
    public void jatekostEltavolit(Jatekos j){
        getJatekosRajta().remove(j);
    }

    /**
     * A paraméterként kapott csövet hozzáadja a szomszedosCso listához. Továbbá beállítja a csőnek magat szomszédnak.
     * @param cs A kapott cső, amit hozzáadunk a listához.
     */
    @Override
    public boolean targyLerakas(Cso cs){
        felcsatol(cs);
        return true;
    }

    /**
     * A csúcs gyárt egy csövet. Ez üres függvény, mert nem minden csúcs tud csövet gyártani.
     * Azon csúcsoknak, amik tudnak csövet gyártani felül kell írniuk ezt a függvényt.
     * @return A csúcs által gyártott cső.
     */
    public Cso csoLetrehozasa(){
        return null;
    }

    /**
     * Szólunk a csúcsnak hogy szeretnénk a cs mod size()-adik csövét megkapni.
     * Ha a csúcsnak csak egy szomszédos csöve van és nincs rajta senki, akkor visszaadja és lecsatolja a csövet.
     * Különben null-t ad vissza.
     * @param cs A szomszédos csövek közül melyiket szeretnénk megkapni.
     * @return A csúcs által visszaadott cső, amennyiben volt ilyen, egyébként null.
     */
    @Override
    public Cso adjCsovet(int cs){
        if(szomszedosCso.size() > 1){
            int k = cs % szomszedosCso.size();
            Cso temp = szomszedosCso.get(k);
            if(temp.getJatekosRajta().size() == 0){
                lecsatol(temp);
                return temp;
            }
        }
        return null;
    }

    /**
     * A Csucs működését jelző függvény. Ezt minden leszármazottnak meg kell valósítania.
     */
    public abstract void vizetPumpal();

    /**
     * A Mainroller elront egy Csucsot. Ez egy üres függvény, mert a Csucs nem tud elromlani.
     * Azon leszármazottakban amik el tudnak romlani meg kell valósítani
     */
    public void kontrollerElront(){
    }

    /**
     * Megszüntet egy kapcsolatot a csúcs és a kapott cső között.
     * A kapott csövet eltávolítja a szomszedosCso listából, és a csőnek megmondja, hogy tavolitse el ezt a Csucsot a szomszedok listajabol..
     * @param cs A kapott cső, amit eltávolítunk a listából.
     */
    public void lecsatol(Cso cs){
        szomszedosCso.remove(cs);
        cs.setCsucsToNull(this);
    }

    /**
     * A kapott csövet hozzáadja a szomszedosCso listához, és a cső szomszédjához is a csúcsot.
     * @param cs A kapott cső, amit hozzáadunk a listához.
     */
    public void felcsatol(Cso cs){
        szomszedosCso.add(cs);
        cs.addCsucs(this);
    }

    /**
     * Kicseréli a cs1 csövet a cs2 csőre. Ehhez lecsatolja a cs1-et és felcsatolja a cs2-t.
     * @param cs1 A lecsatolandó cső.
     * @param cs2 A felcsatolandó cső.
     */
    public void csoCsere(Cso cs1, Cso cs2){
        lecsatol(cs1);
        felcsatol(cs2);
    }

    /**
     * Visszaadja a szomszédos csövek listáját, Mezo típusú listaként.
     * @return A szomszédos csövek listája.
     */
    @Override
    public List<? extends Mezo> getNeighbours(){
        return szomszedosCso;
    }

    /**
     * Visszaadja a szomszédos csövek listáját, Cso típusú listaként.
     * @return A szomszédos csövek listája.
     */
    public List<Cso> getSzomszedosCso(){
        return szomszedosCso;
    }
    /**
     * Beállítja a szomszédos csövek listáját.
     * @param szomszedosCso A beállítandó szomszédos csövek listája.
     */
    public void setSzomszedosCso(List<Cso> szomszedosCso){
        this.szomszedosCso = szomszedosCso;
    }
}
