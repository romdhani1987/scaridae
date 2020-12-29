package fr.romdhani.scaridae.gui.panels.home;

import fr.romdhani.scaridae.controller.RequestController;
import fr.romdhani.scaridae.controller.UserController;
import fr.romdhani.scaridae.gui.panels.access.AccessRequest;
import fr.romdhani.scaridae.utils.size.Desktop;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

/**
 * @author aromdhani
 */
public class MainPanel extends JPanel {
    private final JMenuBar menuBar = new JMenuBar();
    private final JMenu fileMenu = new JMenu("File");
    private final JMenuItem exitMenuItem = new JMenuItem("Exit");
    private final JMenu settingsMenu = new JMenu("Settings");
    private final JMenuItem outputMenuItem = new JMenuItem("Default output directory");
    private final JMenuItem dbMenuItem = new JMenuItem("Database");
    private final JMenu helpMenu = new JMenu("Help");
    private final JMenuItem helpMenuItem = new JMenuItem("?");
    private final JTabbedPane tabbedPane = new JTabbedPane();
    private static final String PRODUCTS = "Products";
    private static final String REQUESTS = "Requests";
    private static final String ACTIONS = "Actions";
    private static final String RESPONSE = "Responses";
    private static final String PROJECTS = "Projects";
    private static final String REPORTS = "Reports";
    private static final String CONTACTS = "Contacts";
    private static final String ATTENDANCE = "Plannings";
    private static final String ADMIN = "Admin";
    private RequestController requestController = new RequestController();

    private void init() {
        fileMenu.add(exitMenuItem);
        exitMenuItem.addActionListener(e -> {
            UserController.getInstance().exit();
        });
        settingsMenu.add(outputMenuItem);
        settingsMenu.add(dbMenuItem);
        helpMenu.add(helpMenuItem);
        menuBar.add(fileMenu);
        menuBar.add(settingsMenu);
        menuBar.add(helpMenu);
        AccessRequest accessRequest = new AccessRequest(requestController);

        JToolBar toolBar = new JToolBar();
        JPanel accessPanel = new JPanel(new MigLayout());
        accessPanel.add(toolBar, "wrap");
        JTabbedPane requestTabbedPane = new JTabbedPane();
        requestTabbedPane.add("Access", accessRequest);
        accessPanel.add(requestTabbedPane, "grow, push, wrap");

        tabbedPane.add(REQUESTS, accessPanel);
        tabbedPane.add(RESPONSE, new JPanel());
        tabbedPane.add(ACTIONS, new JPanel());
        tabbedPane.add(PROJECTS, new JPanel());
        tabbedPane.add(PRODUCTS, new JPanel());
        tabbedPane.add(REPORTS, new JPanel());
        tabbedPane.add(CONTACTS, new JPanel());
        tabbedPane.add(ATTENDANCE, new JPanel());
        tabbedPane.add(ADMIN, new JPanel());
        this.setLayout(new MigLayout("fill"));
        this.setPreferredSize(Desktop.getScreenDimension());
        this.add(tabbedPane, "grow,push");
    }

    public MainPanel() {
        init();
    }

    public JMenuBar getMenuBar() {
        return menuBar;
    }
}
