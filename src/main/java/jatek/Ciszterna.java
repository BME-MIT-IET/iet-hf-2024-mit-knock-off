package jatek;

/**
 * A játékban lévő Ciszternákat reprezentáló osztály.
 * Egy Ciszterna egy olyan Csúcs, amelynek mőködése során az összes szomszédos csőből vizet szív be.
 * Ez a víz egyből el is tűnik. És a szerelők pontot kapnak.
 */
public class Ciszterna extends Csucs{
    /**
     * Létrehoz egy Ciszterna objektumot.
     */
    public Ciszterna(){
        super();
    }

    /**
     * Létrehoz egy új Cso objektumot, amit kétszer felcsatol.
     * Ezt akkor teszi meg, ha a Ciszterna szomszédos csövei között nincs hurok.
     */
    @Override
    public Cso csoLetrehozasa(){
        if(!vanHurok()){
            Cso cs = new Cso();
            felcsatol(cs);
            felcsatol(cs);
            return cs;
        }
        return null;
    }

    /**
     * Ellenőrzi, hogy a Ciszterna szomszédos csövei között van-e hurok.
     * @return Igazat ad vissza, ha van hurok, egyébként hamisat.
     */
    private boolean vanHurok(){
        for (Cso c : getSzomszedosCso()){
            if(c.getNeighbours().size()==2 && c.getNeighbours().get(0) == this && c.getNeighbours().get(1) == this){
                return true;
            }
        }
        return false;
    }

    /**
     * Létrehoz egy új Pumpa objektumot amit visszaad.
     * @return A létrehozott Pumpa objektum.
     */
    @Override
    public Pumpa pumpaLetrehozasa(){
        return new Pumpa();
    }

    /**
     * A ciszterna minden szomszédos csőből vizet szív be.
     * Ha sikerült vizet szívni, akkor a szerelők pontot kapnak.
     */
    @Override
    public void vizetPumpal() {
        for (Cso c : getSzomszedosCso()){
            if (c.vizetVeszit()){
                Kontroller.getInstance().pontNovel("szerelok");
            }
        }
    }
}
