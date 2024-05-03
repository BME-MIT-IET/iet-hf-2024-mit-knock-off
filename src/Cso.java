import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A csőrendszerben lévő csöveket reprezentáló osztály.
 * Két csúcsot köthet össze, valamint azokról a csúcsokról le lehet csatolni és más csúcsra felcsatolni.
 * Passzív elem tehát víz úgy folyhat rajta, ha egy aktív elem szivattyúz belőle vagy bele pumpál.
 */
public class Cso extends Mezo {

    /**
     * A szomszédos csúcsok listája
     */
    private List<Csucs> szomszedosCsucs;

    /**
     * A cső működés szerinti állapota.
     */
    private boolean rossz;

    /**
     * A cső ideiglenes állapotából hátralevő körök száma.
     */
    private int timeToNormal;

    /**
     * A cső ideiglenes állapota, ami a játékosok mozgását befolyásolhatja.
     */
    private Allapot allapot = Allapot.NORMALIS;

    /**
     * A cső nem lyukaszthatóságát jelzi körök számában mérve.
     */
    private int foltozasiGarancia;

    /**
     * Determinisztikus csúszáskor erre a csúcsra mozog a játékos.
     */
    private Csucs det;

    /**
     * Létrehoz egy Csövet, és inicializálja a szomszedosCsucs listát.
     */
    public Cso() {
        super();
        szomszedosCsucs = new ArrayList<>();
    }

    public void removeNeighbour(Csucs csucs){
        szomszedosCsucs.remove(csucs);
    }

    /**
     * Kap egy játékost és jelzi, hogy a játékos a csőre tudott-e lépni.
     * @param j A játékos, aki érkezik.
     * @return Igazat ad vissza ha a játékos ezen a mezőn marad mozgása végén.
     */
    public boolean jatekostElfogad(Jatekos j) {
        if(getJatekosRajta().size() == 0 && szomszedosCsucs.size() == 2){
            if(allapot == Allapot.NORMALIS){
                getJatekosRajta().add(j);
                return true;
            } else if (allapot == Allapot.RAGADOS) {
                j.leragad(true);
                getJatekosRajta().add(j);
                return true;
            } else if (allapot == Allapot.CSUSZOS) {
                Mezo aktMezo = j.getAktMezo();
                aktMezo.jatekostEltavolit(j);
                if(det != null){
                    det.jatekostElfogad(j);
                    j.setAktMezo(det);
                }
                else {
                    Random random = new Random();
                    int irany = random.nextInt(2);
                    szomszedosCsucs.get(irany).jatekostElfogad(j);
                    j.setAktMezo(szomszedosCsucs.get(irany));
                }
                return false;
            }
        }
        return false;
    }

    /**
     * A paraméterként kapott pumpát lehelyezi a cső közepén ezzel egy új csövet is létrehozva.
     * @param p A kapott pumpa, amit lerakunk a csőre.
     */
    @Override
    public boolean targyLerakas(Pumpa p) {
        Cso fele = new Cso();
        Csucs szCsucs = this.szomszedosCsucs.get(0);
        szCsucs.csoCsere(this, fele);
        //szCsucs.felcsatol(fele);
        p.felcsatol(fele);
        p.felcsatol(this);
        Kontroller.getInstance().addCso(fele);
        Kontroller.getInstance().addCsucs(p);
        return true;
    }

    /**
     * Visszaadja a csővel szomszédos csúcsok listáját, Mezo típusú listaként.
     * @return A szomszédos csúcsok listája.
     */
    @Override
    public List<? extends Mezo> getNeighbours() {
        return szomszedosCsucs;
    }

    /**
     * Egy játékos elrontja a csövet.
     */
    @Override
    public void jatekosElront() {
        if(foltozasiGarancia == 0)
            setRossz(true);
    }

    /**
     * A szerelő megjavítja a csövet.
     */
    @Override
    public void szereloJavit() {
        if(rossz){
            setRossz(false);
            Random random = new Random();
            foltozasiGarancia = random.nextInt(3) + 2;
        }
    }

    /**
     * A cső vizet kap. Ha üres, elfogadja, ha van benne víz, nem fogadja el.
     * @return Igazat ad vissza, ha elfogadta a vizet.
     */
    public boolean vizetKap() {
        if(getVanViz())
            return false;
        if(szomszedosCsucs.size() != 2 || rossz){
            Kontroller.getInstance().pontNovel("szabotorok");
            return true;
        }
        setVanViz(true);
        return true;
    }

    /**
     * A csőből vizet szivattyúznak. Ha üres nem ad semmit, ha van benne víz, azt továbbadja.
     * @return Igazat ad vissza, ha volt benne víz.
     */
    public boolean vizetVeszit() {
        if(getVanViz()){
            setVanViz(false);
            return true;
        }
        return false;
    }

    /**
     * A csőnek megváltoztatják az állapotát. CSUSZOS vagy RAGADOS csak abban az esetben lehet, ha eddig NORMALIS volt.
     * @param allapot az állapot, amire változik
     */
    @Override
    public void allapotValtozas(Allapot allapot) {
        if(allapot == Allapot.NORMALIS) {
            this.allapot = Allapot.NORMALIS;
        } else if (this.allapot == Allapot.NORMALIS) {
            if(allapot == Allapot.RAGADOS){
                this.allapot = Allapot.RAGADOS;
            }
            else {
                this.allapot = Allapot.CSUSZOS;
            }
            timeToNormal = 3;
        }
    }

    /**
     * Cső állapotaiból hátralévő idő csökken, ami állapot változást idézhet elő, vagy szabadon engedhet odaragadt játékost.
     */
    public void stepTime(){
        if(timeToNormal == 1){
            allapotValtozas(Allapot.NORMALIS);
            if(getJatekosRajta().size() != 0){
                getJatekosRajta().get(0).leragad(false);
            }
        }
        if(timeToNormal > 0)
            timeToNormal--;
        if(foltozasiGarancia > 0)
            foltozasiGarancia--;
    }

    /**
     * Beveszi a szomszédos csúcsok listájába a paraméterül kapott csúcsot
     * @param cs A csúcs, ami bekerül a szomszédok listájába
     */
    public void addCsucs(Csucs cs){
        szomszedosCsucs.add(cs);
    }

    /**
     * Eltávolítja a szomszédos csúcsok listájából a paraméterül kapott csúcsot
     * @param cs A csúcs, amit eltávolít a szomszédok listájából
     */
    public void setCsucsToNull(Csucs cs){
        szomszedosCsucs.remove(cs);
    }

    /**
     * Beállítja a szomszédos csúcsok listáját.
     * @param csucsok A beállítandó szomszédos csúcsok listája.
     */
    public void setSzomszedosCsucs(List<Csucs> csucsok){
        szomszedosCsucs = csucsok;
    }

    /**
     * Beállítja a cső hibásságát
     * @param r A cső hibássága.
     */
    public void setRossz(boolean r){
        rossz = r;
    }

    /**
     * visszaadja a cső hibásságát
     * @return a rossz értéke
     */
    public boolean getRossz(){
        return rossz;
    }

    /**
     * visszaadja a cső állapotát
     * @return az allapot
     */
    public Allapot getAllapot(){
        return allapot;
    }

    /**
     * visszaadja a cső timeToNormal változójának értékét
     * @return timeToNormal
     */
    public int getTimeToNormal(){
        return timeToNormal;
    }
    /**
     * visszaadja a cső foltozasiGarancia változójának értékét
     * @return foltozasiGarancia
     */
    public int getFoltozasiGarancia(){
        return foltozasiGarancia;
    }

    /**
     * Visszaadja a szomszédos csúcsok listáját, Csucs típusú listaként.
     * @return A szomszédos csúcsok listája.
     */
    public List<Csucs> getSzomszedosCsucs() {
        return szomszedosCsucs;
    }

    /**
     * Beallitja a determinisztikus mukodeshez a csucsot amire a jatekos csuszik
     * @param csucs A csucs, amire csuszhat.
     */
    public void setDet(Csucs csucs){
        det = csucs;
    }

}