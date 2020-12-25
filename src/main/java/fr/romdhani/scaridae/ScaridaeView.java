package fr.romdhani.scaridae;

import fr.romdhani.scaridae.core.database.DBEntityManager;
import fr.romdhani.scaridae.gui.panels.home.Home;
import fr.romdhani.scaridae.utils.Version;

import javax.swing.*;
import java.awt.*;


/**
 * Creates and displays the main view.
 *
 * @author aromdhani
 */
public class ScaridaeView {
    @SuppressWarnings("unused")
    private void show() {
        Version version = new Version();
        JFrame frame = new JFrame("Scaridae");
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setContentPane(new Home());
        frame.setVisible(true);
    }
    private void initialize() {


    }

    private void launch() {
        initialize();
        show();
    }
    public static void main(String[] args) {
        try {
            System.out.println("*** Start scaridae ***");
            ScaridaeView scaridaeView = new ScaridaeView();
            scaridaeView.launch();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
