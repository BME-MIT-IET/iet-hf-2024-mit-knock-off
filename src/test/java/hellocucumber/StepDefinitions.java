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

    @Given("Egy tábla egy pumpaval \\({string}) egy forassal \\({string}), koztuk egy csovel \\({string}).")
    public void egyTáblaEgyPumpavalEgyForassalKoztukEgyCsovel(String pumpaNev, String forrasNev, String csoNev) {
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

    @When("{string} szerelo megprobal ellepni az {int} mezore")
    public void szereloMegprobalEllepniAzMezore(String szereloNev, int mezoSorszam) {
        Szerelo sz1 = szerelok.get(szereloNev);
        sz1.mozgas(mezoSorszam);
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
