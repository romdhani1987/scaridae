package fr.romdhani.scaridae.gui.panels.access;

import fr.romdhani.scaridae.controller.RequestController;
import fr.romdhani.scaridae.core.orm.RequestAccess;
import fr.romdhani.scaridae.gui.panels.commons.IRequest;
import fr.romdhani.scaridae.gui.table.AccessRequestModel;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.swingx.JXDatePicker;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;

/**
 * @author aromdhani
 */
public class AccessRequest extends JPanel implements IRequest {

    private final JTextField searchTextField = new JTextField();
    private final JPanel accessRequestPanel = new JPanel();
    private RequestController requestController;
    private JTable accessTable = new JTable();

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
        AccessRequestModel accessRequestModel = new AccessRequestModel();
        accessTable.setModel(accessRequestModel);
        RequestAccess requestAccess = new RequestAccess("rrr", "Test");
        accessRequestModel.addUserStatis(requestAccess);
        accessPanel.add(toolbarPanel, ",wrap");
        accessPanel.add(new JScrollPane(accessTable), ",span,grow,push");

        JScrollPane tableScrollPane = new JScrollPane(accessPanel);
        JSplitPane documentSplitPane = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT, tableScrollPane, accessRequestPanel);
        documentSplitPane.setResizeWeight(0.8);

        JScrollPane scrollPane = new JScrollPane(new JPanel());
        JSplitPane rightSplitPane = new JSplitPane(
                JSplitPane.VERTICAL_SPLIT, documentSplitPane, scrollPane);
        rightSplitPane.setResizeWeight(0.8);
        this.setLayout(new MigLayout(""));

        add(rightSplitPane, "height 95%, width 100%");
    }

    public AccessRequest(RequestController requestController) {
        this.requestController = requestController;
        init();
    }

}
