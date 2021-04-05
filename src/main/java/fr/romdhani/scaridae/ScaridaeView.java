package fr.romdhani.scaridae;

import fr.romdhani.scaridae.controller.DatabaseInitializer;
import fr.romdhani.scaridae.controller.EventBusDispatcher;
import fr.romdhani.scaridae.core.database.DBEntityManager;
import fr.romdhani.scaridae.gui.Frame;
import fr.romdhani.scaridae.gui.panels.home.ConnectionPanel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


/**
 * Creates and displays the main view.
 *
 * @author aromdhani
 */
public class ScaridaeView {
    private static final Logger logger = LogManager.getLogger(ScaridaeView.class);

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
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        EventBusDispatcher.getInstance().register(frame);
    }

    private void exit() {
        logger.debug("*** Exit Scaridae ***");
        DBEntityManager.getInstance().closeSessionFactory();
        System.exit(0);
    }

    private void initialize() {
        try {
            //TODO to remove later when entities have been loaded
            DBEntityManager.getInstance();
            DatabaseInitializer.getInstance().load();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void launch() {
        initialize();
        show();
    }

    public static void main(String[] args) {
        try {
            logger.info("*** Start Scaridae ***");
            ScaridaeView scaridaeView = new ScaridaeView();
            scaridaeView.launch();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
