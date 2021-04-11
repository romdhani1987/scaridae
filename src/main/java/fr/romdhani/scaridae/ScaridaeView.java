package fr.romdhani.scaridae;

import fr.romdhani.scaridae.controller.ConfigLoader;
import fr.romdhani.scaridae.controller.EventBusDispatcher;
import fr.romdhani.scaridae.core.database.DBEntityManager;
import fr.romdhani.scaridae.core.database.DatabaseUpgrader;
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

    private void loadConfigs() {
        ConfigLoader.getInstance().load();
    }

    private void upgrade() {
        try {
            DatabaseUpgrader databaseUpgrader = new DatabaseUpgrader();
            databaseUpgrader.initAndMigrate();
        } catch (Exception e) {
            logger.error("Error while trying to upgrade Scaridae: ", e);
        }
    }

    private void launch() {
        loadConfigs();
        if (ConfigLoader.getInstance().isUpgradeDatabase()) {
            upgrade();
        }
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
