package fr.romdhani.scaridae;

import fr.romdhani.scaridae.gui.panels.home.HomePanel;
import fr.romdhani.scaridae.utils.Version;

import javax.swing.*;


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
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setContentPane(new HomePanel());
        frame.pack();
        frame.setSize(1250, 850);
        frame.setLocationRelativeTo(null);
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
