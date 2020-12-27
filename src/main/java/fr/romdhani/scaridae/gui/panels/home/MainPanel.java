package fr.romdhani.scaridae.gui.panels.home;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;

/**
 * @author aromdhani
 */
public class MainPanel extends JPanel {
    private final JMenuBar menuBar = new JMenuBar();
    private final JMenu fileMenu = new JMenu("File");
    private final JMenuItem logoutMenuItem = new JMenuItem("Logout");
    private final JMenu settingsMenu = new JMenu("Settings");
    private final JMenuItem outputMenuItem = new JMenuItem("Default output directory");
    private final JMenuItem dbMenuItem = new JMenuItem("Database");
    private final JMenu helpMenu = new JMenu("Help");
    private final JMenuItem helpMenuItem = new JMenuItem("?");
    private final JTabbedPane tabbedPane = new JTabbedPane();
    private static final String ORGANIZATION = "Organisation";
    private static final String REQUESTS = "Requests";
    private static final String ACTIONS = "Actions";
    private static final String RESPONSE = "Responses";
    private static final String PROJECTS = "Projects";
    private static final String REPORTS = "Reports";
    private static final String CONTACTS = "Contacts";
    private static final String ATTENDANCE = "Attendance";
    private static final String ADMIN = "Admin";

    private void init() {

        fileMenu.add(logoutMenuItem);
        settingsMenu.add(outputMenuItem);
        settingsMenu.add(dbMenuItem);
        helpMenu.add(helpMenuItem);
        menuBar.add(fileMenu);
        menuBar.add(settingsMenu);
        menuBar.add(helpMenu);

        tabbedPane.add(ORGANIZATION, new JPanel());
        tabbedPane.add(REQUESTS, new JPanel());
        tabbedPane.add(RESPONSE, new JPanel());
        tabbedPane.add(ACTIONS, new JPanel());
        tabbedPane.add(PROJECTS, new JPanel());
        tabbedPane.add(REPORTS, new JPanel());
        tabbedPane.add(CONTACTS, new JPanel());
        tabbedPane.add(ATTENDANCE, new JPanel());
        tabbedPane.add(ADMIN, new JPanel());
        this.setLayout(new MigLayout("", "grow"));
        this.add(tabbedPane, "");
    }

    public MainPanel() {
        init();
    }

    public JMenuBar getMenuBar() {
        return menuBar;
    }
}
