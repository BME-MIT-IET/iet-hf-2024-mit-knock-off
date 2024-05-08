package jatek;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A játék menetét kontrollálja, számolja a két csapat pontjait, számolja a köröket, az akciók után áramoltatja a vizet.
 */
public  class Kontroller {

    /**
     * A szerelők által szerzett pontokat reprezentálja
     */
    private int szereloPontok;
    /**
     * A szabotőrök által szerzett pontokat reprezentálja.
     */
    private int szabotorPontok;
    /**
     * Az aktuális kör számat tartalmazza.
     */
    private int aktualisKor;
    /**
     * A játékban lévő szerelők számát tartalmazza.
     */
    private int szerelokSzama;
    /**
     * A játékban lévő szabotőrök számát tartalmazza.
     */
    private int szabotorokSzama;
    /**
     * Az aktuális játékos indexét tartalmazza.
     */
    private int aktualisJatekos;
    /**
     * A játékban lévő csúcsokat tartalmazó lista.
     */
    private List<Csucs> csucsok = new ArrayList<>();
    /**
     * A játékban lévő csöveket tartalmazó lista.
     */
    private List<Cso> csovek = new ArrayList<>();
    /**
     * A játékban lévő játékosokat tartalmazó lista.
     */
    private List<Jatekos> jatekosok = new ArrayList<>();

    /**
     * A singleton referenciája
     */
    private static Kontroller single_instance = null;

    /**
     * A singletont lekérdező függvény.
     * @return A singleton referenciája.
     */
    public static synchronized Kontroller getInstance()
    {
        if (single_instance == null)
            single_instance = new Kontroller();

        return single_instance;
    }

    /**
     * Visszaadja a szerelők pontjait.
     * @return A szerelők pontjai.
     */
    public int getSzereloPontok(){return szereloPontok;}
    /**
     * Visszaadja a szabotőrök pontjait.
     * @return A szabotőrök pontjai.
     */
    public int getSzabotorPontok(){return szabotorPontok;}
    /**
     * Visszaadja az aktuális kör számát.
     * @return Az aktuális kör száma.
     */
    public int getAktualisKor(){return aktualisKor;}
    /**
     * Visszaadja a szerelők számát.
     * @return A szerelők száma.
     */
    public int getSzerelokSzama(){return szerelokSzama;}
    /**
     * Visszaadja a szabotőrök számát.
     * @return A szabotőrök száma.
     */
    public int getSzabotorokSzama(){return szabotorokSzama;}
    /**
     * Visszaadja az aktuális játékos indexét.
     * @return Az aktuális játékos indexe.
     */
    public int getAktualisJatekos(){return aktualisJatekos;}
    /**
     * Visszaadja a csúcsokat tartalmazó listát.
     * @return A csúcsokat tartalmazó lista.
     */
    public List<Csucs> getCsucsok(){return csucsok;}
    /**
     * Visszaadja a csöveket tartalmazó listát.
     * @return A csöveket tartalmazó lista.
     */
    public List<Cso> getCsovek(){return csovek;}
    /**
     * Visszaadja a játékosokat tartalmazó listát.
     * @return A játékosokat tartalmazó lista.
     */
    public List<Jatekos> getJatekosok(){return jatekosok;}

    /**
     * Beállítja a szerelők pontjait.
     * @param p A szerelők pontjai.
     */
    public void setSzereloPontok(int p){szereloPontok=p;}
    /**
     * Beállítja a szabotőrök pontjait.
     * @param p A szabotőrök pontjai.
     */
    public void setSzabotorPontok(int p){szabotorPontok=p;}
    /**
     * Beállítja az aktuális kör számát.
     * @param j Az aktuális kör száma.
     */
    public void setAktJatekos(int j){aktualisJatekos=j;}
    /**
     * Beállítja a szerelők számát.
     * @param sz A szerelők száma.
     */
    public void setSzerelokSzama(int sz){szerelokSzama=sz;}
    /**
     * Beállítja a szabotőrök számát.
     * @param sz A szabotőrök száma.
     */
    public void setSzabotorokSzama(int sz){szabotorokSzama=sz;}
    /**
     * Beállítja az aktuális kör számát.
     * @param k Az aktuális kör száma.
     */
    public void setAktKor(int k){aktualisKor=k;}

    /**
     * Értesíti a megjelenítő modellt arról hogy változás történt a játékban
     */
    public void ujraRajzol(){
        GamePanel.getInstance().drawAll();
    }

    /**
     * Minden csúcsban pumpálja a vizet.
     * Minden ciszernában létrehozz egy csövet, ha nincs hurokél.
     * Elront véletlenszerűen valahány pumpát.
     */
    public void stepKor(){
        for (Csucs csucs:csucsok) {
            Cso cs = csucs.csoLetrehozasa();
            if(cs!=null) {
                CsoView csv = new CsoView(cs);
                GamePanel.getInstance().addCsoView(csv);
                csovek.add(cs);
            }
            csucs.vizetPumpal();
        }
        veletlenPumpaElrontas();
        Kontroller.getInstance().ujraRajzol();

    }

    /**
     * Létrehozza a játék elemeit, felépítését. Hívja az initPalya(), majd az initJatekosok(int, int) függvényt.
     */
    public void initJatek(){
        initPalya();
        initJatekosok(szerelokSzama, szabotorokSzama);
        Kontroller.getInstance().ujraRajzol();
    }

    /**
     * Inicializálja a pályát.
     * Létrehozza a csúcsokat, a pumpákat és a ciszternákat.
     * 4 darab ciszterna, pumpa és forrás van.
     * Minden ciszterna és forrás össze van kötve egy pumpával.
     * Három pumpa kötött van cső.
     * A csúcsokhoz létrehozza a csúcsok megjelenítőit.
     * A ciszternákat bal oldalra helyezi, a pumpákat a képernyő közepére, a forrásokat pedig jobb oldalra.
     */
    public void initPalya(){
        for (int i = 0; i < 4; i++) {
            Ciszterna c = new Ciszterna();
            CiszternaView cv = new CiszternaView(c);
            cv.setX(100);
            cv.setY(125*(i+1));
            GamePanel.getInstance().addCsucsView(cv);
            csucsok.add(c);
        }

        for(int i = 0; i < 4; i++){
            Pumpa p = new Pumpa();
            PumpaView pv = new PumpaView(p);
            int x = (Toolkit.getDefaultToolkit().getScreenSize().width-400 + 100)/2;
            pv.setX(x);
            pv.setY(125*(i+1));
            GamePanel.getInstance().addCsucsView(pv);
            csucsok.add(p);
        }

        for (int i = 0; i < 4; i++) {
            Forras f = new Forras();
            ForrasView fv = new ForrasView(f);
            int x = Toolkit.getDefaultToolkit().getScreenSize().width-400;
            fv.setX(x);
            fv.setY(125*(i+1));
            GamePanel.getInstance().addCsucsView(fv);
            csucsok.add(f);
        }

        for (int i = 0; i < 4; i++) {
            Cso c = new Cso();
            CsoView cv = new CsoView(c);
            GamePanel.getInstance().addCsoView(cv);
            csovek.add(c);
            csucsok.get(i).felcsatol(c);
            csucsok.get(i+4).felcsatol(c);
        }

        for(int i = 0; i < 4; i++){
            Cso c = new Cso();
            CsoView cv = new CsoView(c);
            GamePanel.getInstance().addCsoView(cv);
            csovek.add(c);
            csucsok.get(i+4).felcsatol(c);
            csucsok.get(i+8).felcsatol(c);
        }

        for(int i = 0; i < 3; i++){
            Cso c = new Cso();
            CsoView cv = new CsoView(c);
            GamePanel.getInstance().addCsoView(cv);
            csovek.add(c);
            csucsok.get(i+4).felcsatol(c);
            csucsok.get(i+5).felcsatol(c);
        }

        for (int i=4; i<8; i++){
            csucsok.get(i).setBemenetiCso(csovek.get(i));
        }
    }

    /**
     * Játékosokat hoz létre és elhelyezi őket a megfelelő ciszternákon.
     * @param szerelokSz szerelők száma
     * @param szabotorokSz szabotőrök száma
     */
    public void initJatekosok(int szerelokSz, int szabotorokSz){
        szerelokSzama=szerelokSz;
        szabotorokSzama=szabotorokSz;
        int ciszternakszama=4;

        for(int i=0; i<szabotorokSz; i++){
            Szabotor szabotor= new Szabotor();
            JatekosView szabotorView = new SzabotorView(szabotor);
            GamePanel.getInstance().addJatekosView(szabotorView);
            jatekosok.add(szabotor);
        }
        for(int i=0; i<szerelokSz; i++){
            Szerelo szerelo= new Szerelo();
            JatekosView szereloView = new SzereloView(szerelo);
            GamePanel.getInstance().addJatekosView(szereloView);
            jatekosok.add(szerelo);
        }

        for(int i=0; i<jatekosok.size(); i++) {
            csucsok.get(i % ciszternakszama).setJatekosRajta(jatekosok.get(i));
            jatekosok.get(i).setAktMezo(csucsok.get(i % ciszternakszama));
        }
    }

    /**
     * Megnöveli a paraméterben átadott csapat pontjait eggyel
     * @param csapat a csapat neve, lehet "szerelok" vagy "szabotorok".
     */
    public void pontNovel(String csapat){
        switch (csapat) {
            case "szerelok" -> szereloPontok++;
            case "szabotorok" -> szabotorPontok++;
        }
        Kontroller.getInstance().ujraRajzol();
    }

    /**
     * Véletlenszerűen elront pumpákat. Minden csúcsot 1%-os eséllyel ront el.
     */
    public void veletlenPumpaElrontas(){
        int percent = 1;

        Random r = new Random();

        for (Csucs cs : csucsok) {
            if (r.nextInt(100) < percent) {
                cs.kontrollerElront();
            }
        }
        Kontroller.getInstance().ujraRajzol();
    }

    /**
     * A csövek normálissá  változásáig hátralévő időt csökkenti.
     * Minden, a csovek listában lévő, csövön meghívja az adott cső stepTime() függvényét.
     */
    public void stepTime(){
        for(Cso cs: csovek){
            cs.stepTime();
        }
        Kontroller.getInstance().ujraRajzol();
    }
    public void vizLeptet(){
        Kontroller.getInstance().ujraRajzol();
    }

    /**
     * Hozzáad egy csövet a csovek listához. A paramétert berakja a lista végére.
     * A csőhöz tartozó CsoView-t is hozzáadja a GamePanelhez.
     * @param cso a hozzáadandó cső.
     */
    public void addCso(Cso cso){
        csovek.add(cso);
        CsoView cv = new CsoView(cso);
        GamePanel.getInstance().addCsoView(cv);
    }

    /**
     * Hozzáad egy csúcsot a csucsok listájához. A paramétert berakja a lista végére.
     * A csúcshoz tartozó PumpaView-t is hozzáadja a GamePanelhez.
     * @param csucs a hozzáadandó csúcs.
     */
    public void addCsucs(Pumpa csucs){
        csucsok.add(csucs);
        PumpaView pv = new PumpaView((csucs));
        Cso holvolt =csucs.getSzomszedosCso().get(1);
        CsoView cv = GamePanel.getInstance().getCsoViewFromCso(holvolt);
        pv.setX((cv.getX1() + cv.getX2())/2);
        pv.setY((cv.getY1() + cv.getY2())/2);
        GamePanel.getInstance().addCsucsView(pv);
    }

    /**
     * Eltávolítja a paraméterként kapott Jatekos-t a jatekosok listából.
     * @param jatekos a törölni kívánt játékos.
     */
    public void removeJatekos(Jatekos jatekos){
        for(int i=0; i<jatekosok.size(); i++){
            if(jatekosok.get(i).equals(jatekos)) {
                jatekosok.remove(i);
                break;
            }
        }
    }

    /**
     * Eltávolítja a paraméterként kapott Cso-t a csovek listából.
     * @param cso a törölni kívánt cső.
     */
    public void removeCso(Cso cso){
        for(int i=0; i<csovek.size(); i++){
            if(csovek.get(i).equals(cso)) {
                csovek.remove(i);
                break;
            }
        }
    }

    /**
     * Eltávolítja a paraméterként kapott Csucs-ot a csucsok listából.
     * @param csucs a törölni kívánt csúcs.
     */
    public void removeCsucs(Csucs csucs){
        for(int i=0; i<csucsok.size(); i++){
            if(csucsok.get(i).equals(csucs)) {
                csucsok.remove(i);
                break;
            }
        }
    }


    /**
     * Kiüríti a csúcsok, csövek és játékosok listáját, visszaállítja a kört, a pontokat és a játékosok számát 0-ra.
     */
    public void reset(){
        csucsok.clear();
        csovek.clear();
        jatekosok.clear();
        aktualisKor=0;
        szerelokSzama=0;
        szabotorokSzama=0;
        szereloPontok=0;
        szabotorPontok=0;
    }

    /**
     * Hozzáad egy játékost a jatekosok listájához. A paramétert berakja a lista végére.
     * @param jatekos a hozzáadandó játékos.
     */
    public void addJatekos(Jatekos jatekos){
        jatekosok.add(jatekos);
    }
}
