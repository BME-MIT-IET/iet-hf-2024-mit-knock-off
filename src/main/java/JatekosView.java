import java.awt.*;

/**
 * A Jatekos osztály (azaz a játékosok) grafikus megjelenítéséért felel.
 */
public abstract class JatekosView {
    /**
     * kirajzolás koordinátái x,y
     */
    private int x;
    private int y;

    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }

    public void setX(int x){this.x=x;}

    public void setY(int y){
        this.y=y;
    }

    /**
     * A szerelő és szabotőr leszármazottak valósítják meg a kirajzolást
     * @param g A kirajzoló grafikus objektum.
     */
    public abstract void draw(Graphics g);

    /**
     * Visszatér a leszármazott által megvalósított objektummal
     * @return a megvalósított játékos objektum
     */
    public abstract Jatekos getJatekos();
}
