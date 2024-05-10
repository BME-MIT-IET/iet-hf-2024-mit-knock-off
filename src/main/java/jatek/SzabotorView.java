package jatek;

import java.awt.*;

/**
 * A Szabotor osztály (azaz a szabotőrök) grafikus megjelenítéséért felel.
 */
public class SzabotorView extends JatekosView{
    /**
     * A nézethez tartozó szabotor objektum
     */
    private Szabotor szabotor;

    public SzabotorView(Szabotor sz) {
        szabotor=sz;
    }

    /**
     * Kirajzolja a szabotőrt egy négyszög formájában.
     * Alapesetben barna színnel, de ha le van ragadva sárga, míg ha aktív, akkor zöld színű.
     * @param g A kirajzoló grafikus objektum.
     */
    @Override
    public void draw(Graphics g){
        g.setColor(new Color(109, 28, 28)); //alapesetben barna
        if(szabotor.getRagados()){
            g.setColor(new Color(230, 243, 86));//ha le van ragadva, akkor sárga
        }

        //aktív játékos lekérdezése
        int aktIndex = Kontroller.getInstance().getAktualisJatekos();
        Jatekos aktJatekos = Kontroller.getInstance().getJatekosok().get(aktIndex);
        if(aktJatekos == szabotor){
            g.setColor(new Color(102, 215, 50));//ha aktív, akkor zöld
        }


        int[] xpoints = {getX()-9,getX()+9,getX()+9, getX()-9};
        int[] ypoints = {getY()-9, getY()-9, getY()+9, getY()+9};
        g.fillPolygon(xpoints, ypoints, 4/*negyszog*/);
    }

    @Override
    public Szabotor getJatekos(){
        return szabotor;
    }
}
