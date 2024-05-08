package jatek;

/**
 * 2D vektorokat reprezentáló osztály. Segedmetódusokat tartalmaz a vektorokkal való műveletekhez és a megjelenítéshez
 */
public class Vec2 {
    /**
     * A vektor x kooordinátája.
     */
    private double x;
    /**
     * A vektor y koordinátája.
     */
    private double y;

    /**
     * Default konstruktor. Mindkét koordinátát 0-ra állítja.
     */
    public Vec2(){
        x = 0;
        y = 0;
    }

    /**
     * Konstruktor, amely beállítja a vektor koordinátáit.
     * @param x A vektor x koordináta
     * @param y A vektor y koordináta
     */
    public Vec2(double x, double y){
        this.x = x;
        this.y = y;
    }

    /**
     * Összeadja a vektort a paraméterként kapott vektorral.
     * @param v A vektor, amellyet ehhez hozzáadunk.
     * @return A két vektor összege.
     */
    public Vec2 add(Vec2 v){
        return new Vec2(x+v.x, y+v.y);
    }

    /**
     * Kivonja a paraméterként kapott vektort a vektorból.
     * @param v A vektor, amelyet kivonunk.
     * @return A két vektor különbsége.
     */
    public Vec2 substract(Vec2 v){
        return new Vec2(x-v.x, y-v.y);
    }

    /**
     * A vektor minden koordinátáját megszorozza a paraméterként kapott számmal.
     * @param i A szám, amellyel megszorozzuk a vektort.
     * @return A vektor, amelynek minden koordinátáját megszoroztuk a paraméterként kapott számmal.
     */
    public Vec2 multiply(double i){
        return new Vec2(x*i, y*i);
    }

    /**
     * Normalizálja a vektort, azaz a vektor hosszát 1-re állítja, de az irányát nem változtatja meg.
     * @return A normalizált vektor.
     */
    public Vec2 normalize(){
        double length = getLength();
        return new Vec2(x/length, y/length);
    }

    /**
     * A vektorra merőleges vektort ad vissza.
     * @return A vektorra merőleges vektor.
     */
    public Vec2 getPerpendicular(){
        return new Vec2(-y, x);
    }

    /**
     * A vektor hosszát adja vissza.
     * @return A vektor hossza.
     */
    public double getLength(){
        return Math.sqrt(x*x + y*y);
    }

    /**
     * A vektor és a paraméterként kapott vektor skaláris szorzatát adja vissza.
     * @param v A vektor, amellyel a skaláris szorzatot végezzük.
     * @return A két vektor skaláris szorzata.
     */
    public double dot(Vec2 v){
        return x*v.x + y*v.y;
    }

    /**
     * A vektort elforgatja a paraméterként kapott szöggel.
     * @param angle A szög radiánban, amellyel elforgatjuk a vektort.
     * @return Az elforgatott vektor.
     */
    public Vec2 rotate(double angle){
        double cos = Math.cos(angle);
        double sin = Math.sin(angle);
        return new Vec2(x*cos - y*sin, x*sin + y*cos);
    }

    /**
     * A vektor x koordinátáját adja vissza.
     * @return A vektor x koordinátája.
     */
    public double getX(){return x;}

    /**
     * A vektor y koordinátáját adja vissza.
     * @return A vektor y koordinátája.
     */
    public double getY(){return y;}

    /**
     * A vektor x koordinátáját állítja be.
     * @param x A vektor x koordinátája.
     */
    public void setX(double x){this.x = x;}

    /**
     * A vektor y koordinátáját állítja be.
     * @param y A vektor y koordinátája.
     */
    public void setY(double y){this.y = y;}
}
