import java.awt.*;
import java.util.List;

public abstract class CsucsView {
    int x;
    int y;

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public void setY(int y){
        this.y=y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public abstract void draw(Graphics g);

    /**
     * Kiszámolja a csúcson található játékosok koordinátáit, körben helyezve el őket a csúcson
     */
    public void calculate(){
        List<Jatekos> jatekosList = getCsucs().getJatekosRajta();
        int count = jatekosList.size();
        double alpha = Math.PI*2/count;
        for(int i = 0; i<count;++i){
            Vec2 coord = new Vec2(x+10*Math.cos(alpha*(i+1)),y-10*Math.sin(alpha*(i+1)));
            GamePanel.getInstance().getPlayerViewFromPlayer(jatekosList.get(i)).setX((int) coord.getX());
            GamePanel.getInstance().getPlayerViewFromPlayer(jatekosList.get(i)).setY((int) coord.getY());
        }
    }
    public abstract Csucs getCsucs();
}
