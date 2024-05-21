package jatek;

import java.util.List;
/**
 * A játékban lévő játékosokat reprezentálja. A Szerelő és Szabotőr közös
 * tulajdonságaiért és viselkedéséért felelős absztrakt osztály. A felhasználó
 * karakteri, mozogni tudnak a pálya elemein, át tudják állítani a pumpákat, be
 * tudják ragasztózni a csöveket és rá tudnak ragadni egy beragasztózott csőre.
 */
public abstract class Jatekos {
    /**
     * A Játékos aktuális mezője, amin éppen tartózkodik.
     */
    private Mezo atkMezo=null;

    /**
     * Az igaz értéke jelzi, hogy az adott Játékos hozzá van ragadva ahhoz
     * a mezőhöz, amin áll, így onnan elmozogni nem képes. Hamis érték esetén
     * a Játékos elmozoghat arról a mezőről, amin áll.
     */
    private boolean ragados=false;

    /**
     * Konstruktor
     */
    Jatekos(){
        setAktMezo(null);
        leragad(false);
    }
    /**
     * Beállítja a paraméterül adott m-et az aktMezo-nek.
     * @param m ami az aktMezo értéke lesz
     */
    public void setAktMezo(Mezo m){
        atkMezo=m;
    }

    /**
     * Visszater a játékos aktMezo-jével.
     * @return játekos aktMezo-je
     */
    public Mezo getAktMezo(){
        return atkMezo;
    }

    /**
     * A játékos ragados attribútumát lehet állítani a paraméterként megadott értékre.
     * @param b ragados új értéke
     */
    public void leragad(boolean b){
        ragados=b;
    }

    /**
     * A játékos ragados attribútumát lehet állítani a paraméterként megadott értékre.
     * @return ragados értéke
     */
    public boolean getRagados(){
        return ragados;
    }

    /**
     * A játékos ragadóssá teszi azt a csövet, amin áll, hogy a következő érkező játékos odaragadjon.
     * A játékos az aktMezo-re meghívja annak az allapotValtozas() metódusát RAGADOS paraméterrel.
     */
    public void beragasztoz(){
        getAktMezo().allapotValtozas(Allapot.RAGADOS);
        Kontroller.getInstance().ujraRajzol();
    }

    /**
     * A játékos kilyukasztja a csövet, amin áll, hogy kifolyjon belőle a víz.
     * A játékos az aktMezo-re meghívja annak a jatekosElront() metódusát.
     */
    public void csoKilyukasztasa(){
        getAktMezo().jatekosElront();
        Kontroller.getInstance().ujraRajzol();
    }

    /**
     * Az aktMezo-ről egy másik, szomszédos mezőre (hova paraméter) lép át.
     * Csak akkor mozoghat egy játékos, ha a ragados attribútuma hamis. Ha csövön áll, akkor valamelyik
     * szomszédos csúcsára lép át, ebben az esetben a szomszedosCsucs lista hova-dik elemére lép. Ha csúcson
     * áll, akkor egy szomszédos csőre lép át, amennyiben ez lehetséges, azaz nem állnak azon a csövön, ebben
     * az esetben a szomszedosCso lista hova-dik elemére lép. Az aktMezo getNeighbours() metódusa adja meg az
     * aktMezo szomszédjainak a listáját. Amennyiben annak a mezőnek, ahová a játékos lép a jatekosElfogad() metódusa
     * igaz értékkel tér vissza, akkor ráléphet az új mezőre a játékos. Ekkor az aktMezo jatekosEltavolit() eltávolítja
     * a játékost az aktuális mezőről és az új mezőt pedig beállítja az aktMezo-nek a setAktMezo() metódus segítségével.
     * @param hova hanyadik mezőre lép a játékos az aktMezo szomszédlistában
     */
    public void mozgas(int hova){
        if(!getRagados()){
            List<? extends Mezo> neighbours = getAktMezo().getNeighbours();
            if(neighbours.size()>hova) {
                Mezo hovamezo = neighbours.get(hova);
                if (hovamezo.jatekostElfogad(this)) {
                    getAktMezo().jatekostEltavolit(this);
                    setAktMezo(hovamezo);
                }
            }
        }
        Kontroller.getInstance().ujraRajzol();
    }

    /**
     * A játékos úgy állít át egy pumpát, hogy megadja, melyik csőből (bemeneti) melyik csőbe (kimeneti) folyjon a víz.
     * A játékos az aktMezo-re meghívja annak az atallit() metódusát a bemeneti és kimeneti paraméterekkel.
     * @param bemeneti melyik csőből folyjon a víz
     * @param kimeneti melyik csőbe folyjon a víz
     */
    public void pumpaAtallitasa(int bemeneti, int kimeneti){
        getAktMezo().atallit(bemeneti, kimeneti);
        Kontroller.getInstance().ujraRajzol();
    }

    public void csuszosit(){}
    public void mezotJavit(){}
    public void csovegFelvetele(int cs){}
    public void csovegetLerak(){}
    public void pumpaFelvetele(){}
    public void pumpatLerak(){}
    public Cso getCso(){return null;}
}
