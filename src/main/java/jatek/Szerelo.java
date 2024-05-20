package jatek;

/**
 * A játékban lévő szerelőket reprezentálja. Képes pumpát és csövet szerelni,
 * csövet kilyukasztani valamint ragadóssá tenni. Célja, hogy minél több víz folyjon a ciszternákba.
 */
public class Szerelo extends Jatekos{
    /**
     * A szerelőnél lévő csővég, amennyiben nincsen nála csővég, értéke null.
     */
    private Cso csoveg=null;

    /**
     * A szerelőnél lévő pumpa, amennyiben nincs nála pumpa, értéke null.
     */
    private Pumpa pumpa=null;

    /**
     * Visszatér a csoveg attribútum értékével.
     * @return csoveg értéke
     */
    public Cso getCsoveg(){
        return csoveg;
    }

    /**
     *  Beállítja a paraméterül adott cs-t a csoveg-nek.
     * @param cs csoveg új értéke
     */
    public void setCsoveg(Cso cs) {
        csoveg = cs;
    }

    /**
     * Visszatér a pumpa attribútum értékével.
     * @return pumpa értéke
     */
    public Pumpa getPumpa(){
        return pumpa;
    }

    /**
     * Beállítja a paraméterül adott p-t a pumpa-nak.
     * @param p pumpa új értéke
     */
    public void setPumpa(Pumpa p){
        pumpa=p;
    }

    /**
     * Megjavítja azt a mezőt, amin a szerelő éppen áll.
     * A játékos meghívja az aktMezo-re a szereloJavit() függvényét, ami megjavítja az aktMezo-t.
     */
    @Override
    public void mezotJavit(){
        getAktMezo().szereloJavit();
        Kontroller.getInstance().ujraRajzol();
    }

    /**
     * A szerelő egy csövet vesz magához.
     * Amennyiben a szerelőnél nincsen pumpa vagy csővég (a pumpa és a csoveg attribútumok értéke null),
     * akkor a szerelő az aktMezo-nek meghívja az adjCsovet() metódusát egy int paraméterrel,
     * ami azt jelenti, hogy a mezőhöz kapcsolódó csövek közül melyiket akarja felvenni, majd a
     * visszatérési értékét beállítja a csoveg attribútum értékének.
     * @param cs cső indexe
     */
    @Override
    public void csovegFelvetele(int cs){
        if(getCsoveg()==null && getPumpa()==null) {
            Cso kapottCso = getAktMezo().adjCsovet(cs);
            setCsoveg(kapottCso);
        }
        Kontroller.getInstance().ujraRajzol();
    }

    /**
     * A szerelő lerakja a nála lévő csővéget.
     * Amennyiben a szerelőnél van csővég (a csoveg attribútum értéke nem null), akkor azt a
     * szerelő az aktMezo-re rakja úgy, hogy meghívja az aktMezo targylerakas() metodusát a csoveg paraméterrel.
     */
    @Override
    public void csovegetLerak(){
        if(getCsoveg()!=null && getAktMezo().targyLerakas(csoveg)){
            setCsoveg(null);
        }
        Kontroller.getInstance().ujraRajzol();
    }

    /**
     * A szerelő pumpát vesz magához.
     * Amennyiben a szerelőnél nincsen pumpa vagy csővég (a pumpa és a csoveg attribútumok értéke null),
     * akkor a szerelő az aktMezo-nek meghívja a pumpaLetrehozasa() metódusát, melynek a visszatérési
     * értékét beállítja a pumpa attribútum értékének.
     */
    @Override
    public void pumpaFelvetele(){
        if(getCsoveg()==null && getPumpa()==null){
            Pumpa kapottPumpa= getAktMezo().pumpaLetrehozasa();
            setPumpa(kapottPumpa);
        }
        Kontroller.getInstance().ujraRajzol();
    }

    /**
     * A szerelő lerakja a nála lévő pumpát.
     * Amennyiben a szerelőnél van pumpa (a pumpa attribútum értéke nem null), akkor azt a szerelő
     * az aktMezo-re rakja úgy, hogy meghívja az aktMezo targylerakas() metodusát a pumpa paraméterrel.
     */
    @Override
    public void pumpatLerak(){
        if(getPumpa()!=null && getAktMezo().targyLerakas(pumpa)){
            setPumpa(null);
        }
        Kontroller.getInstance().ujraRajzol();
    }

    @Override
    public Cso getCso(){
        return getCsoveg();
    }
}