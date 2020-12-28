package fr.romdhani.scaridae.utils.size;

import java.awt.*;

public class Desktop {

    public static Dimension getScreenDimension() {
        return Toolkit.getDefaultToolkit().getScreenSize();
    }

    public static int getScreenWidth() {
        return Toolkit.getDefaultToolkit().getScreenSize().width;
    }

    public static int getScreenHeight() {
        return Toolkit.getDefaultToolkit().getScreenSize().height;
    }
}
