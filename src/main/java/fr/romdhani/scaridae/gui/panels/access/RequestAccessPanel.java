package fr.romdhani.scaridae.gui.panels.access;

import fr.romdhani.scaridae.controller.RequestController;
import fr.romdhani.scaridae.controller.UserController;
import fr.romdhani.scaridae.core.orm.RequestAccess;
import fr.romdhani.scaridae.gui.panels.commons.IRequest;
import fr.romdhani.scaridae.gui.panels.home.SignupPanel;
import fr.romdhani.scaridae.gui.table.model.AccessRequestModel;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.swingx.JXDatePicker;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;

/**
 * @author aromdhani
 */
public class RequestAccessPanel extends JPanel implements IRequest {

    private final JTextField searchTextField = new JTextField();
    private final JPanel accessRequestPanel = new JPanel();
    private final JTable accessTable = new JTable();
    private final AccessRequestModel accessRequestModel = new AccessRequestModel();
    private final JButton newRequestButton = new JButton("New");
    private final JButton editRequestButton = new JButton("Edit");
    private final JButton removeRequestButton = new JButton("Remove");
    private RequestController requestController;


    private void init() {
        JPanel accessPanel = new JPanel();
        accessPanel.setLayout(new MigLayout());
        JPanel toolbarPanel = new JPanel(new MigLayout(""));
        JLabel nameLabel = new JLabel(" Name: ");
        toolbarPanel.add(nameLabel, "width 80:80:100");
        toolbarPanel.add(searchTextField, "width :180:, height :25:");

        JLabel startDateLabel = new JLabel(" From:");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        JXDatePicker startDatePicker = new JXDatePicker();
        startDatePicker.setFormats(dateFormat);
        startDatePicker.setPreferredSize(new Dimension(100, 25));

        toolbarPanel.add(startDateLabel, "width :80:");
        toolbarPanel.add(startDatePicker, "width :80:");

        JLabel endDateLabel = new JLabel(" To:");
        JXDatePicker endDatePicker = new JXDatePicker();
        endDatePicker.setFormats(dateFormat);
        endDatePicker.setPreferredSize(new Dimension(100, 25));

        toolbarPanel.add(endDateLabel, "width 80:80:100");
        toolbarPanel.add(endDatePicker, "width :80:");

        JButton searchButton = new JButton("Search");
        toolbarPanel.add(searchButton, "width :80:,wrap");
        accessRequestModel.addAll(requestController.getAllRequestList());
        accessTable.setModel(accessRequestModel);
        accessPanel.add(toolbarPanel, ",wrap");
        accessPanel.add(new JScrollPane(accessTable), ",span,grow,push");

        JScrollPane tableScrollPane = new JScrollPane(accessPanel);
        JSplitPane documentSplitPane = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT, tableScrollPane, accessRequestPanel);
        documentSplitPane.setResizeWeight(0.8);

        JScrollPane scrollPane = new JScrollPane(createBottomPane());
        JSplitPane rightSplitPane = new JSplitPane(
                JSplitPane.VERTICAL_SPLIT, documentSplitPane, scrollPane);
        rightSplitPane.setResizeWeight(0.8);
        this.setLayout(new MigLayout(""));

        add(rightSplitPane, "height 95%, width 100%");
    }

    private JPanel createBottomPane() {
        JPanel bottomPanel = new JPanel(new MigLayout());
        newRequestButton.addActionListener(e -> newRequest());
        editRequestButton.addActionListener(e -> editRequest());
        removeRequestButton.addActionListener(e -> removeRequest());
        bottomPanel.add(newRequestButton, "w :50:");
        bottomPanel.add(editRequestButton, "w :50:");
        bottomPanel.add(removeRequestButton, "w :50:");
        return bottomPanel;
    }

    private void removeRequest() {
    }

    private void editRequest() {
        JDialog dialog = new JDialog();
        SignupPanel signupPanel = new SignupPanel(UserController.getInstance());
        signupPanel.setOnSuccess(() -> {
            dialog.dispose();
        });
        signupPanel.setOnCancel(() -> {
            dialog.dispose();
        });
        dialog.setTitle("Edit Request");
        dialog.setContentPane(signupPanel);
        dialog.setSize(new Dimension(800, 650));
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    private void newRequest() {
        JDialog dialog = new JDialog();
        NewRequestPanel newRequestPanel = new NewRequestPanel();
        newRequestPanel.setOnSuccess(() -> {
            RequestAccess requestAccess = newRequestPanel.getRequestAccess();
            if (requestAccess != null) {
                requestController.addReq(requestAccess);
                accessRequestModel.addRequest(requestAccess);
            }
            newRequestPanel.setRequestAccess(null);
            dialog.dispose();
        });
        newRequestPanel.setOnCancel(() -> {
            dialog.dispose();
        });
        dialog.setTitle("New Request");
        dialog.setContentPane(newRequestPanel);
        dialog.setSize(new Dimension(800, 650));
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    public RequestAccessPanel(RequestController requestController) {
        this.requestController = requestController;
        init();
    }

}
