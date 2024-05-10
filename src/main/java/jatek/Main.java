package jatek;

/**
 * Main osztály, tartalmazza a program belépési pontját.
 */
public class Main {
    /**
     * A program belépési pontja.
     * Létrehozza a játék ablakát.
     * @param args A parancssori argumentumok.
     */
    public static void main(String args[])
    {
        GameFrame frame = new GameFrame();
        frame.setVisible(true);
    }
}
