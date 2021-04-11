package fr.romdhani.scaridae.gui.panels.access;

import fr.romdhani.scaridae.core.orm.RequestAccess;
import lombok.NonNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class EditRequestDialog extends JDialog {
    private final NewRequestPanel newRequestPanel;

    public EditRequestDialog(Window owner, String title, @NonNull RequestAccess requestAccess) {
        super(owner, title);
        newRequestPanel = new NewRequestPanel(requestAccess);
        init();
    }

    private void init() {
        JButton validate = new JButton("Valid");
        JButton cancel = new JButton("Cancel");
        validate.addActionListener(this::valid);
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

    private void valid(ActionEvent actionEvent) {
        if (newRequestPanel.edit())
            this.dispose();
    }

    public RequestAccess getRequestAccess() {
        return newRequestPanel.getRequestAccess();
    }
}
