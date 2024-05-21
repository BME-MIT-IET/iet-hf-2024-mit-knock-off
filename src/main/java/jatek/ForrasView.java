package jatek;

import java.awt.*;
import java.util.List;

/**
 * A források nézetét tartalmazó osztály.
 */
public class ForrasView extends CsucsView{
    /**
     * A forrás, amit megjelenít.
     */
    private Forras forras;

    /**
     * A forrás sugara.
     */
    private int radius = 30;

    /**
     * Konstruktor a forrás nézetének.
     * @param f A forrás, amit megjelenít.
     */
    public ForrasView(Forras f){
        forras = f;
    }

    /**
     * Kirajzolja a forrást.
     * A forrás sugara a radius. Ezzel lehet a méretet állítani.
     * A forrás egy kék kör, fekete körvonallal.
     * A szomszédos csövek számát a ciszterna szélére írja.
     * @param g A kirajzoló grafikus objektum.
     */
    @Override
    public void draw(Graphics g){
        int widthOfKorvonal = 10;

        g.setColor(Color.BLACK);
        g.fillOval(x-radius-widthOfKorvonal/2,y-radius-widthOfKorvonal/2,2*radius+widthOfKorvonal,2*radius+widthOfKorvonal);

        g.setColor(Color.BLUE);
        g.fillOval(x-radius,y-radius,2*radius,2*radius);

        //Kirajzolja a szomszedos csovek szamat.
        g.setColor(Color.WHITE);
        List<Cso> szomszedoscsovek = forras.getSzomszedosCso();
        for (int i=0; i<szomszedoscsovek.size(); i++) {
            if (!szomszedoscsovek.subList(0, i).contains(szomszedoscsovek.get(i))) {
                CsoView cv = GamePanel.getInstance().getCsoViewFromCso(szomszedoscsovek.get(i));
                Vec2 direction = new Vec2(cv.getX1() - ((double)getX()), cv.getY1() - ((double)getY()));
                if (cv.getX1() == getX() && cv.getY1() == getY()) {
                    direction = new Vec2(cv.getX2() - ((double)getX()), cv.getY2() - ((double)getY()));
                }
                direction = direction.normalize();
                direction = direction.multiply(radius - 5.0);
                g.drawString(Integer.toString(i + 1), (int) (getX() + direction.getX() - 5), (int) (getY() + direction.getY() + 5));
            }
        }
    }

    /**
     * Visszaadja a forrást.
     * @return A forrás.
     */
    @Override
    public Forras getCsucs(){
        return forras;
    }
}
