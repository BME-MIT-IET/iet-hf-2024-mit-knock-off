package jatek;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * A játék kirajzolásáért felelős panel.
 */
public class GamePanel extends JPanel {

    /**
     * A singelton instance-je a GamePanelnek.
     */
    private static GamePanel singleInstance = null;

    /**
     * Singleton osztály, hogy csak egyetlen GamePanel objektum létezzen.
     * @return A GamePanel objektum.
     */
    public static synchronized GamePanel getInstance()
    {
        if (singleInstance == null)
            singleInstance = new GamePanel();

        return singleInstance;
    }

    /**
     * A játékosok nézeteit tartalmazó lista.
     */
    List<JatekosView> jatekosok;

    /**
     * A csövek nézeteit tartalmazó lista.
     */
    List<CsoView> csovek;

    /**
     * A csúcsok nézeteit tartalmazó lista.
     */
    List<CsucsView> csucsok;

    /**
     * A pontokat szemléltető címke: "szabotőrok pontja - szerelők pontja" formátumban
     */
    JLabel score_;

    /**
     * Default Konstruktora a Game Panelnek.
     */
    private GamePanel(){
        super();
        jatekosok = new ArrayList<>();
        csovek = new ArrayList<>();
        csucsok = new ArrayList<>();
        score_ = new JLabel("Score: "+Kontroller.getInstance().getSzabotorPontok()+"-"+Kontroller.getInstance().getSzereloPontok());
        score_.setAlignmentY(JLabel.BOTTOM_ALIGNMENT);
        this.add(score_);
    }

    /**
     * Kirajzolja a pályát. Ez a csővek, csúcsok és játékosok kirajzolását jelenti.
     * @param g A grafikus objektum, amire rajzolunk.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (CsoView cs : csovek){
            cs.draw(g);
        }
        for (CsucsView cs : csucsok){
            cs.draw(g);
        }
        for (JatekosView j : jatekosok){
            j.draw(g);
        }
        score_.setText("Score: "+Kontroller.getInstance().getSzabotorPontok()+"-"+Kontroller.getInstance().getSzereloPontok());
    }

    /**
     * Kiszámolja az összes nézetet és megjeleníti az összes nézetet.
     */
    public void drawAll(){
        calculateAll();
        showAll();
    }

    /**
     * Megjeleníti az összes nézetet.
     */
    public void showAll(){
        this.repaint();
    }

    /**
     * Minden nézetet újraszámol.
     * A csúcsokat egszer, a csöveket kétszer.
     */
    public void calculateAll(){
        for (CsucsView cs : csucsok){
            cs.calculate();
        }
        for (CsoView cs : csovek){
            cs.calculate();
        }
        for (CsoView cs : csovek){
            cs.calculate();
        }
    }

    /**
     * Visszaadja a játékos nézetet a játékos alapján.
     * @param j A játékos, aminek a nézetét keresi.
     * @return A játékos nézete.
     */
    public JatekosView getPlayerViewFromPlayer(Jatekos j){
        for (JatekosView jv : jatekosok){
            if (jv.getJatekos() == j){
                return jv;
            }
        }
        return null;
    }

    /**
     * Visszaadja a csúcs nézetet a csúcs alapján.
     * @param c A csúcs, aminek a nézetét keresi.
     * @return A csúcs nézete.
     */
    public CsucsView getCsucsViewFromCsucs(Csucs c){
        for (CsucsView cv : csucsok){
            if (cv.getCsucs() == c){
                return cv;
            }
        }
        return null;
    }

    /**
     * Visszaadja mindazon játékosokat, akik a csövet cipelik.
     * @param c A cső, aminek a cipelőit keresi.
     * @return A csövet cipelő játékosok listája.
     */
    public List<JatekosView> getPlayerViewFromCso(Cso c){
        List<JatekosView> jatekosok = new ArrayList<>();
        for (JatekosView jv : this.jatekosok){
            if (jv.getJatekos().getCso() == c){
                jatekosok.add(jv);
            }
        }
        return jatekosok;
    }

    /**
     * Visszaadja a megadott csőhöz tartozó CsoView-t.
     * @param c A cső, aminek a CsoView-ját keresi.
     * @return A csőhöz tartozó CsoView.
     */
    public CsoView getCsoViewFromCso(Cso c){
        for (CsoView cv : csovek){
            if (cv.getCso() == c){
                return cv;
            }
        }
        return null;
    }

    /**
     * Kirajzol egy háromszöget a megadott helyre, a megadott irányba.
     * @param g Graphics, amire rajzolni kell.
     * @param from Az egyenlő szárú háromszög rövidebb oldalának a középpontja.
     * @param dir Az egyenlő szárú háromszög rövidebb oldalának a közeppontjából a szembe lévő csúcsba mutató vektor, ezzel arányos lesz a háromszög elnyúlása.
     */
    public void drawTriangle(Graphics g, Vec2 from, Vec2 dir, int size){
        Vec2 a;
        Vec2 b;
        Vec2 c;

        a = from.add(dir.multiply(size));
        Vec2 oldal = dir.getPerpendicular().normalize();
        b = from.add(oldal.multiply(size));
        c = from.add(oldal.multiply(-size));

        int[] x = {(int)a.getX(),(int)b.getX(),(int)c.getX()};
        int[] y = {(int)a.getY(),(int)b.getY(),(int)c.getY()};
        g.fillPolygon(x,y,3);
    }

    /**
     * Hozzáad egy CsoView-t a nézethez.
     * @param csoView a hozzáadandó CsoView.
     */
    public void addCsoView(CsoView csoView){
        csovek.add(csoView);
    }

    /**
     * Hozzáad egy CsucsView-t a nézethez.
     * @param csucsView a hozzáadandó CsucsView.
     */
    public void addCsucsView(CsucsView csucsView){
        csucsok.add(csucsView);
    }

    /**
     * Hozzáad egy JatekosView-t a nézethez.
     * @param jatekosView a hozzáadandó JatekosView.
     */
    public void addJatekosView(JatekosView jatekosView){
        jatekosok.add(jatekosView);
    }
}

