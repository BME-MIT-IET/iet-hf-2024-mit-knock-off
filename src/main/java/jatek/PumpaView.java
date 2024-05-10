package jatek;

import java.awt.*;
import java.util.List;

/**
 * A pumpa kirajzolásáért felelős osztály.
 */
public class PumpaView extends CsucsView{
    /**
     * A kirajzolandó pumpa.
     */
    private Pumpa pumpa;

    /**
     * A pumpa sugara.
     */
    private int radius = 30;

    /**
     * Default ctor.
     * @param p a pumpa amit ki kell rajzolni.
     */
    public PumpaView(Pumpa p){
        pumpa = p;
    }

    /**
     * Kirajzolja a pumpát.
     * A pumpa sugara a radius. Ezzel lehet a méretet állítani.
     * Ha a pumpa rossz, akkor piros keretet rajzol a pumpa köré.
     * Ha a pumpában van víz, akkor kék a pumpa, különben fekete.
     * A szomszédos csövek számát a pumpa szélére írja.
     * A kimeneti cső irányába egy nyilat rajzol a pumpán kívülre.
     * A bemeneti cső irányába egy nyilat rajzol a pumpán kívülre.
     * @param g A kirajzoló grafikus objektum.
     */
    @Override
    public void draw(Graphics g){
        int widthOfKorvonal = 10;
        //Kirajzolja a pumpa keretét.
        if (pumpa.getRossz()){
            g.setColor(Color.RED);
            g.fillOval(x-radius-widthOfKorvonal/2,y-radius-widthOfKorvonal/2,2*radius+widthOfKorvonal,2*radius+widthOfKorvonal);
        }
        //Kirajzolja a pumpa körét, ha van benne víz, akkor kék, különben fekete.
        if(pumpa.getVanViz()){
            g.setColor(Color.BLUE);
        }
        else{
            g.setColor(Color.BLACK);
        }
        g.fillOval(x-radius,y-radius,2*radius,2*radius);

        //Kirajzolja a szomszedos csovek szamat.
        g.setColor(Color.WHITE);
        List<Cso> szomszedoscsovek = pumpa.getSzomszedosCso();
        for (int i=0; i<szomszedoscsovek.size(); i++){
            if(!szomszedoscsovek.subList(0,i).contains(szomszedoscsovek.get(i))) {
                CsoView cv = GamePanel.getInstance().getCsoViewFromCso(szomszedoscsovek.get(i));
                Vec2 direction = new Vec2(cv.getX1() - getX(), cv.getY1() - getY());
                if (cv.getX1() == getX() && cv.getY1() == getY()) {
                    direction = new Vec2(cv.getX2() - getX(), cv.getY2() - getY());
                }
                direction = direction.normalize();
                direction = direction.multiply(radius - 5);
                g.drawString(Integer.toString(i + 1), (int) (getX() + direction.getX() - 5), (int) (getY() + direction.getY() + 5));
            }
        }

        //kirajzolja a kimeno cso iranyat
        g.setColor(Color.BLACK);
        Cso kimeneti = szomszedoscsovek.get(pumpa.getKimenetiCso());
        CsoView kimenetiView = GamePanel.getInstance().getCsoViewFromCso(kimeneti);
        Vec2 direction = new Vec2(kimenetiView.getX1()-getX(),kimenetiView.getY1()-getY());
        if (kimenetiView.getX1() == getX() && kimenetiView.getY1() == getY()) {
            direction = new Vec2(kimenetiView.getX2() - getX(), kimenetiView.getY2() - getY());
        }
        direction = direction.normalize();

        GamePanel.getInstance().drawTriangle(g,new Vec2(getX(),getY()).add(direction.multiply(radius+10)),direction.multiply(3), 8);
        g.setColor(Color.WHITE);
        GamePanel.getInstance().drawTriangle(g,new Vec2(getX(),getY()).add(direction.multiply(radius+12)),direction.multiply(3), 5);

        //kirajzolja a bemeneti cso iranyat
        Cso bemeneti = szomszedoscsovek.get(pumpa.getBemenetiCso());
        CsoView bemenetiView = GamePanel.getInstance().getCsoViewFromCso(bemeneti);
        Vec2 direction2 = new Vec2(bemenetiView.getX1()-getX(),bemenetiView.getY1()-getY());
        if (bemenetiView.getX1() == getX() && bemenetiView.getY1() == getY()) {
            direction2 = new Vec2(bemenetiView.getX2() - getX(), bemenetiView.getY2() - getY());
        }
        direction2 = direction2.normalize();
        g.setColor(Color.BLACK);
        GamePanel.getInstance().drawTriangle(g,new Vec2(getX(),getY()).add(direction2.multiply(radius+40)),direction2.multiply(-3), 8);
        g.setColor(Color.WHITE);
        GamePanel.getInstance().drawTriangle(g,new Vec2(getX(),getY()).add(direction2.multiply(radius+38)),direction2.multiply(-3), 5);
    }

    /**
     * Visszaadja a pumpát amit kirajzol
     * @return a pumpa, aminek a kirajzolásáért felelős.
     */
    @Override
    public Csucs getCsucs(){
        return pumpa;
    }
}
