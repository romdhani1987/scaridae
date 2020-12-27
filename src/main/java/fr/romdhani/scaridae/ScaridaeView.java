package fr.romdhani.scaridae;

import fr.romdhani.scaridae.controller.EventBusDispatcher;
import fr.romdhani.scaridae.core.database.DBEntityManager;
import fr.romdhani.scaridae.gui.Frame;
import fr.romdhani.scaridae.gui.panels.home.ConnectionPanel;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


/**
 * Creates and displays the main view.
 *
 * @author aromdhani
 */
public class ScaridaeView {

    @SuppressWarnings("unused")
    private void show() {
        Frame frame = new Frame("Scaridae");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ConnectionPanel connectionPanel = new ConnectionPanel();
        frame.setContentPane(connectionPanel);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                super.windowClosing(windowEvent);
                exit();
            }
        });
        frame.pack();
        frame.setSize(1250, 850);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        EventBusDispatcher.getInstance().register(frame);
    }

    private void exit() {
        DBEntityManager.getInstance().closeSessionFactory();
        System.exit(0);
    }

    private void initialize() {
        DBEntityManager.getInstance();
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
