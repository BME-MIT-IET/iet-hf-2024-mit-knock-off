package jatek;

import java.awt.*;

/**
 * A Szerelo osztály (azaz a szerelők) grafikus megjelenítéséért felel.
 */
public class SzereloView extends JatekosView{
    /**
     * A nézethez tartozó szerelő objektum
     */
    private Szerelo szerelo;
    public SzereloView(Szerelo sz){
        szerelo=sz;
    }

    /**
     * Kirajzolja a szerelőt egy háromszög formájában.
     * Alapesetben barna színnel, de ha le van ragadva sárga, míg ha aktív, akkor zöld színű.
     * Ha a szerelőnél van valamilyen tárgy, akkor az megjelenik a képernyő alján szöveg formájában.
     * @param g A kirajzoló grafikus objektum.
     */
    @Override
    public void draw(Graphics g){
        g.setColor(new Color(109, 28, 28)); //alapesetben barna
        if(szerelo.getRagados()){
            g.setColor(new Color(230, 243, 86)); //ha le van ragadva, akkor sárga
        }

        //aktív játékos lekérdezése
        int aktIndex = Kontroller.getInstance().getAktualisJatekos();
        Jatekos aktJatekos = Kontroller.getInstance().getJatekosok().get(aktIndex);
        if(aktJatekos == szerelo){
            //ha a szerelőnél van tárgy, akkor kiírja a képernyő aljára, ha nincs nála semmi, nem ír ki semmit
            if(szerelo.getCsoveg()!=null || szerelo.getPumpa()!=null){
                String itemString = "Item: ";
                if(szerelo.getCsoveg()!=null){
                    itemString+="Csővég";
                }
                if(szerelo.getPumpa()!=null){
                    itemString+="Pumpa";
                }
                g.setColor(Color.BLACK);
                g.setFont(new Font("TimesRoman", Font.BOLD, 25));
                g.drawString(itemString, Toolkit.getDefaultToolkit().getScreenSize().width*2/5 , Toolkit.getDefaultToolkit().getScreenSize().height*7/8);
            }

            g.setColor(new Color(102, 215, 50)); //ha aktív, akkor zöld
        }

        //háromszög pontjai
        int[] xpoints = {getX(),(getX()-9),(getX()+9)};
        int[] ypoints = {getY()-9, getY()+9, getY()+9};
        g.fillPolygon(xpoints, ypoints, 3/*haromszog*/);


    }
    @Override
    public Szerelo getJatekos(){
        return szerelo;
    }
}
