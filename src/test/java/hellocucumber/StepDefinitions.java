package hellocucumber;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import jatek.*;
import static  org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.List;

public class StepDefinitions {
    private HashMap<String, Cso> csovek = new HashMap<>();
    private HashMap<String, Forras> forrasok = new HashMap<>();
    private HashMap<String, Pumpa> pumpak = new HashMap<>();
    private HashMap<String, Ciszterna> ciszternak = new HashMap<>();
    private HashMap<String, Szerelo> szerelok = new HashMap<>();
    private HashMap<String, Szabotor> szabotorok = new HashMap<>();

    private Mezo getMezo(String mezoNev) {
        if (csovek.containsKey(mezoNev)) {
            return csovek.get(mezoNev);
        } else if (pumpak.containsKey(mezoNev)) {
            return pumpak.get(mezoNev);
        } else if (forrasok.containsKey(mezoNev)) {
            return forrasok.get(mezoNev);
        } else return ciszternak.getOrDefault(mezoNev, null);
    


    @Given("Egy tábla egy pumpaval \\({string}) egy forrassal \\({string}), koztuk egy csovel \\({string}).")
    public void egyTáblaEgyPumpavalEgyForrassalKoztukEgyCsovel(String pumpaNev, String forrasNev, String csoNev) {
        List<Csucs> csucsok = Kontroller.getInstance().getCsucsok();
        List<Cso> kontrollerCsovek = Kontroller.getInstance().getCsovek();

        Pumpa p1 = new Pumpa();
        pumpak.put(pumpaNev, p1);
        csucsok.add(p1);

        Forras f1 = new Forras();
        forrasok.put(forrasNev, f1);
        csucsok.add(f1);

        Cso cs1 = new Cso();
        csovek.put(csoNev, cs1);
        kontrollerCsovek.add(cs1);

        cs1.addCsucs(p1);
        cs1.addCsucs(f1);
        p1.addCso(cs1);
        f1.addCso(cs1);
    }

    @And("Egy szerelo \\({string}), ami {string}-n all.")
    public void egySzereloAmiNAll(String szereloNev, String mezoNev) {
        Szerelo sz1 = new Szerelo();
        szerelok.put(szereloNev, sz1);
        Kontroller.getInstance().addJatekos(sz1);

        Mezo mezo = getMezo(mezoNev);
        if (mezo != null) {
            sz1.setAktMezo(mezo);
            mezo.setJatekosRajta(sz1);
        } else {
            fail(szereloNev + " nem tartozkodhat olyan mezon, ami nem letezik!");
        }
    }

    @And("A {string} cso torott.")
    public void aCsoTorott(String csoNev){
        Cso cso = csovek.get(csoNev);
        if(cso == null){
            fail(csoNev +" nem lehet torott, ha nem letezik!");
        }
        else{
            cso.setRossz(true);
        }
    }

    @When("{string} szerelo megprobal ellepni az {int} mezore")
    public void szereloMegprobalEllepniAzMezore(String szereloNev, int mezoSorszam) {
        Szerelo sz1 = szerelok.get(szereloNev);
        sz1.mozgas(mezoSorszam);
    }

    @When("{string} szerelo megprobalja megjavitani a mezot amin all")
    public void szereloMegprobaljaMegjavitaniAMezotAminAll(String szereloNev){
        Szerelo sz1 = szerelok.get(szereloNev);
        sz1.mezotJavit();
    }

    @When("{string} szerelo megprobalja felvenni a szomszedos csovet")
    public void szereloMegprobaljaFelvenniASzomszedosCsovet(String szereloNev){
        Szerelo sz1 = szerelok.get(szereloNev);
        sz1.csovegFelvetele(0);
    }

    @Then("{string} mar {string}-en tartozkodik")
    public void marEnTartozkodik(String szereloNev, String mezoNev) {
        Szerelo sz1 = szerelok.get(szereloNev);
        Mezo szereloMezo = sz1.getAktMezo();
        Mezo mezo = getMezo(mezoNev);
        List<Jatekos> mezoJatekos = mezo.getJatekosRajta();

        assertEquals(szereloMezo, mezo, szereloNev + " nem " + mezoNev + "-en van");
        assertTrue(mezoJatekos.contains(sz1), mezoNev + "-en nincs " + szereloNev);
    }

    @Then("{string} cso mar nem torott")
    public void csoMarNemTorott(String csoNev){
        Cso cso = csovek.get(csoNev);
        assertTrue(cso.getRossz(), csoNev + " meg mindig rossz");
    }

    @Then("{string} szerelonel van a {string} cso vege")
    public void szerelonelVanACsoVege(String szereloNev, String csoNev){
        Szerelo sz1 = szerelok.get(szereloNev);
        Cso cso = csovek.get(csoNev);

        assertEquals(sz1.getCsoveg(), cso, szereloNev + " nem " + csoNev + "-et tartja");
        assertTrue(sz1.getCsoveg() != null, szereloNev + " nem tart csovet ");
    }
}
