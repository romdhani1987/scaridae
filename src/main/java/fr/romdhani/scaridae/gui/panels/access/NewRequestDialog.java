package fr.romdhani.scaridae.gui.panels.access;

import fr.romdhani.scaridae.core.orm.RequestAccess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class NewRequestDialog extends JDialog {
    private final NewRequestPanel newRequestPanel = new NewRequestPanel();

    public NewRequestDialog(Window owner, String title) {
        super(owner, title);
        init();
    }

    private void init() {
        JButton validate = new JButton(" Add ");
        JButton cancel = new JButton("Cancel");
        validate.addActionListener(this::add);
        cancel.addActionListener(this::cancel);
        getContentPane().add(new JScrollPane(newRequestPanel), BorderLayout.CENTER);
        JPanel buttonsPanel = new JPanel(new FlowLayout());
        buttonsPanel.add(validate);
        buttonsPanel.add(cancel);
        getContentPane().add(buttonsPanel, BorderLayout.PAGE_END);
    }

    private void cancel(ActionEvent actionEvent) {
        newRequestPanel.cancel();
        this.dispose();
    }

    private void add(ActionEvent actionEvent) {
        if (newRequestPanel.add())
            this.dispose();
    }

    public RequestAccess getRequestAccess() {
        return newRequestPanel.getRequestAccess();
    }
}
