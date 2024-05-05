/**
 * A játékban lévő Forrásokat reprezentáló osztály.
 * Egy forrás egy olyan Csúcs, amely minden szomszédos csőbe vizet pumpál a működés során.
 */
public class Forras extends Csucs{
    /**
     * Létrehoz egy Forrás objektumot.
     */
    public Forras(){
        super();
    }

    /**
     * A forrás minden szomszédos csőbe vizet pumpál.
     */
    @Override
    public void vizetPumpal(){
        for(Cso c : getSzomszedosCso()){
            c.vizetKap();
        }
    }
}
