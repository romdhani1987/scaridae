package fr.romdhani.scaridae.gui.panels.access;

import fr.romdhani.scaridae.gui.panels.commons.IRequest;
import fr.romdhani.scaridae.gui.table.AccessRequestModel;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.swingx.JXDatePicker;
import org.jdesktop.swingx.JXTable;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;

public class AccessRequest extends JPanel implements IRequest {
    private final JTextField searchTextField = new JTextField();
    private JTable accessTable;
    private final JPanel accessRequestPanel = new JPanel();

    private void init() {
        JPanel accessPanel = new JPanel();
        accessPanel.setLayout(new MigLayout());
        JLabel nameLabel = new JLabel(" Name: ");
        accessPanel.add(nameLabel, "width 80:80:100");
        accessPanel.add(searchTextField, "width :180:, height :25:");

        JLabel startDateLabel = new JLabel(" From:");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        JXDatePicker startDatePicker = new JXDatePicker();
        startDatePicker.setFormats(dateFormat);
        startDatePicker.setPreferredSize(new Dimension(100, 25));

        accessPanel.add(startDateLabel, "width :80:");
        accessPanel.add(startDatePicker);

        JLabel endDateLabel = new JLabel(" To:");
        JXDatePicker endDatePicker = new JXDatePicker();
        endDatePicker.setFormats(dateFormat);
        endDatePicker.setPreferredSize(new Dimension(100, 25));

        accessPanel.add(endDateLabel, "width 80:80:100");
        accessPanel.add(endDatePicker);

        JButton searchButton = new JButton("Search");
        accessPanel.add(searchButton, "wrap");
        AccessRequestModel accessRequestModel = new AccessRequestModel();
        accessTable = new JTable(accessRequestModel);
        accessTable.setRowHeight(25);

        accessPanel.add(accessTable, ",span , grow,push");

        JScrollPane tableScrollPane = new JScrollPane(accessPanel);
        JSplitPane documentSplitPane = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT, tableScrollPane, accessRequestPanel);
        documentSplitPane.setResizeWeight(0.8);

        JScrollPane scrollPane = new JScrollPane(new JPanel());
        JSplitPane rightSplitPane = new JSplitPane(
                JSplitPane.VERTICAL_SPLIT, documentSplitPane, scrollPane);
        rightSplitPane.setResizeWeight(0.8);
        this.setLayout(new MigLayout("fill,debug"));

        add(rightSplitPane, "height 100%, width 100%");
    }

    public AccessRequest() {
        init();
    }

}
