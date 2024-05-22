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
    }


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

    @And("Egy szabotor \\({string}), ami {string}-n all.")
    public void egySzabotorAmiNAll(String szabotorNev, String mezoNev) {
        Szabotor sz1 = new Szabotor();
        szabotorok.put(szabotorNev, sz1);
        Kontroller.getInstance().addJatekos(sz1);

        Mezo mezo = getMezo(mezoNev);
        if (mezo != null) {
            sz1.setAktMezo(mezo);
            mezo.setJatekosRajta(sz1);
        } else {
            fail(szabotorNev + " nem tartozkodhat olyan mezon, ami nem letezik!");
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

    @And("Eltelik egy kor.")
    public void eltelikEgyKor(){
        Kontroller.getInstance().stepKor();
    }

    @And("{string} szerelo megjavitja a mezot amin all")
    public void szereloMegjavitjaAMezotAminAll(String szereloNev){
        Szerelo sz1 = szerelok.get(szereloNev);
        if(sz1 == null)
            fail(sz1 +" nem javithat, ha nem letezik!");
        sz1.mezotJavit();
    }

    @When("{string} szerelo megprobalja megjavitani a mezot amin all")
    public void szereloMegprobaljaMegjavitaniAMezotAminAll(String szereloNev){
        Szerelo sz1 = szerelok.get(szereloNev);
        if(sz1 == null)
            fail(sz1 +" nem javithat, ha nem letezik!");
        sz1.mezotJavit();
    }

    

    @And("{string} szerelo megprobal ellepni az {int} mezore")
    @When("{string} szerelo megprobal ellepni az {int} mezore")
    public void szereloMegprobalEllepniAzMezore(String szereloNev, int mezoSorszam) {
        Szerelo sz1 = szerelok.get(szereloNev);
        if(sz1 == null)
            fail(sz1 +" nem mozoghat, ha nem letezik!");
        sz1.mozgas(mezoSorszam);
    }

    @And("{string} szabotor megprobal ellepni az {int} mezore")
    public void szabotorMegprobalEllepniAzMezore(String szabotorNev, int mezoSorszam) {
        Szabotor sz1 = szabotorok.get(szabotorNev);
        if(sz1 == null)
            fail(sz1 +" nem mozoghat, ha nem letezik!");
        sz1.mozgas(mezoSorszam);
    }


    

    @When("{string} szerelo megprobalja felvenni a szomszedos csovet")
    public void szereloMegprobaljaFelvenniASzomszedosCsovet(String szereloNev){
        Szerelo sz1 = szerelok.get(szereloNev);
        if(sz1 == null)
            fail(sz1 +" nem vehet fel csoveget, ha nem letezik!");
        sz1.csovegFelvetele(0);
    }

    @When("{string} szerelo megprobal felvenni egy pumpat")
    public void szereloMegprobalFelvenniEgyPumpat(String szereloNev){
        Szerelo sz1 = szerelok.get(szereloNev);
        if(sz1 == null)
            fail(sz1 +" nem vehet fel pumpat, ha nem letezik!");
        sz1.pumpaFelvetele();
    }

    @When("{string} szabotor megprobalja elrontani a csovet amin all")
    public void szabotorMegprobaljaElrontaniACsovetAminAll(String szabotorNev){
        Szabotor sz1 = szabotorok.get(szabotorNev);
        if(sz1 == null)
            fail(sz1 +" nem lyukaszthat, ha nem letezik!");
        sz1.csoKilyukasztasa();
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


    @Then("{string} cso nem torott")
    public void csoNemTorott(String csoNev){
        Cso cso = csovek.get(csoNev);
        assertTrue(cso.getRossz(), csoNev + " torott");
    }


    @Then("{string} szerelonel van a {string} cso vege")
    public void szerelonelVanACsoVege(String szereloNev, String csoNev){
        Szerelo sz1 = szerelok.get(szereloNev);
        Cso cso = csovek.get(csoNev);

        assertEquals(sz1.getCsoveg(), cso, szereloNev + " nem " + csoNev + "-et tartja");
        assertTrue(sz1.getCsoveg() != null, szereloNev + " nem tart csovet");
    }

    @Then("{string} szerelonel nincsen pumpa")
    public void szerelonelNincsenPumpa(String szereloNev){
        Szerelo sz1 = szerelok.get(szereloNev);
        assertTrue(sz1.getPumpa() == null, szereloNev + "-nel van pumpa");
    }

    @Given("Egy tábla egy forassal \\({string}), hozza csatolva egy csovel \\({string}).")
    public void egy_tábla_egy_forassal_hozza_csatolva_egy_csovel(String forrasNev, String csoNev) {
        // Write code here that turns the phrase above into concrete actions
        List<Csucs> csucsok = Kontroller.getInstance().getCsucsok();
        List<Cso> kontrollerCsovek = Kontroller.getInstance().getCsovek();

        Forras f1 = new Forras();
        forrasok.put(forrasNev, f1);
        csucsok.add(f1);

        Cso cs1 = new Cso();
        csovek.put(csoNev, cs1);
        kontrollerCsovek.add(cs1);


        cs1.addCsucs(f1);

        f1.addCso(cs1);
    }
    @Given("Egy szabotor \\({string}), ami  {string}-n all.")
    public void egy_szabotor_ami_n_all(String szabotorNev, String forrasNev) {
        // Write code here that turns the phrase above into concrete actions


        // Write code here that turns the phrase above into concrete actions
        Szabotor sz1 = new Szabotor();
        szabotorok.put(szabotorNev, sz1);
        Kontroller.getInstance().addJatekos(sz1);

        Mezo mezo = getMezo(forrasNev);
        if (mezo != null) {
            sz1.setAktMezo(mezo);
            mezo.setJatekosRajta(sz1);
        } else {
            fail(szabotorNev + " nem tartozkodhat olyan mezon, ami nem letezik!");
        }
    }

    @When("{string} szabotor megprobal ellepni az {int} mezore")
    public void szabotor_megprobal_ellepni_az_mezore(String szereloNev, Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        Szerelo sz1 = szerelok.get(szereloNev);
        sz1.mozgas(int1);
    }




    @Then("{string} marad {string}-en")
    public void marad_en(String szereloNev, String mezoNev) {
        // Write code here that turns the phrase above into concrete actions
        Szerelo sz = szerelok.get(szereloNev);
        Mezo akt = sz.getAktMezo();
        Mezo mezo = getMezo(mezoNev);

        List<Jatekos> mezoJatekos = mezo.getJatekosRajta();
        assertEquals(akt,mezo, szereloNev + "a " + mezoNev + "-n tartozkodik");
        assertTrue(mezoJatekos.contains(sz),mezoNev + "-en van jatekos");
    }

    @Given("Egy tábla egy pumpa \\({string}), hozza csatolva egy csovel \\({string}), ami ragad.")
    public void egy_tábla_egy_pumpa_hozza_csatolva_egy_csovel_ami_ragad(String pumpaNev, String csoNev) {
        // Write code here that turns the phrase above into concrete actions
        List<Csucs> csucsok = Kontroller.getInstance().getCsucsok();
        List<Cso> kontrollerCsovek = Kontroller.getInstance().getCsovek();

        Pumpa p1 = new Pumpa();
        pumpak.put(pumpaNev, p1);
        csucsok.add(p1);

        Cso cs1 = new Cso();
        cs1.allapotValtozas(Allapot.RAGADOS);
        csovek.put(csoNev, cs1);
        kontrollerCsovek.add(cs1);


        cs1.addCsucs(p1);

        p1.addCso(cs1);
    }
    @Given("Egy tábla egy ciszterna \\({string}), hozza csatolva egy csovel \\({string}).")
    public void egy_tábla_egy_ciszterna_hozza_csatolva_egy_csovel(String ciszternaNev, String csoNev) {
        // Write code here that turns the phrase above into concrete actions
        List<Csucs> csucsok = Kontroller.getInstance().getCsucsok();
        List<Cso> kontrollerCsovek = Kontroller.getInstance().getCsovek();

        Ciszterna c1 = new Ciszterna();
        ciszternak.put(ciszternaNev, c1);
        csucsok.add(c1);



        Cso cs1 = new Cso();
        csovek.put(csoNev, cs1);
        kontrollerCsovek.add(cs1);

        cs1.addCsucs(c1);

        c1.addCso(cs1);

    }
    @When("{string} szerelo megprobalja kilyukasztani a {string} ciszternat")
    public void szerelo_megprobalja_kilyukasztani_a_ciszternat(String szereloNev, String ciszternaNev) {
        // Write code here that turns the phrase above into concrete actions
        Szerelo sz1 = szerelok.get(szereloNev);
        sz1.csoKilyukasztasa();
    }

    @Then("A {string} ciszterna ugyanugy tud vizet fogadni")
    public void a_ciszterna_ugyanugy_tud_vizet_fogadni(String ciszternaNev) {
        // Write code here that turns the phrase above into concrete actions
        Ciszterna c1 = ciszternak.get(ciszternaNev);

        assertTrue(true,ciszternaNev + " tud vizet fogadni");
    }

    @When("{string} szerelo megprobalja kilyukasztani a {string} csovet")
    public void szerelo_megprobalja_kilyukasztani_a_csovet(String szereloNev, String csoNev) {
        // Write code here that turns the phrase above into concrete actions
        Szerelo sz1 = szerelok.get(szereloNev);
        sz1.csoKilyukasztasa();
    }
    @Then("A {string} cso lukas lesz")
    public void a_cso_lukas_lesz(String csoNev) {
        // Write code here that turns the phrase above into concrete actions
        Cso cs1  = csovek.get(csoNev);
        boolean roosz = cs1.getRossz();
        assertTrue(roosz, "A " + csoNev + "lyukas lett");

    }
}
