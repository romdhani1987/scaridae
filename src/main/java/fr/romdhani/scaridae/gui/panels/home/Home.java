package fr.romdhani.scaridae.gui.panels.home;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class Home extends JPanel {

    private JTextField loginField = new JTextField();
    private JPasswordField passwordField = new JPasswordField();
    private JButton singInButton = new JButton("Sign in");
    private JButton cancelButton = new JButton("Cancel");
    private JButton signupButton = new JButton();

    private void init() {
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new MigLayout());
        JLabel loginLabel = new JLabel("Login");
        JLabel passwordLabel = new JLabel("Password");
        setLayout(new MigLayout());
        loginPanel.add(loginLabel);
        loginPanel.add(loginField, "width :150: ");
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField, "width :150: ,wrap");
        JPanel buttonsPanel = new JPanel(new FlowLayout());
        buttonsPanel.add(singInButton);
        buttonsPanel.add(cancelButton);

        signupButton.setText("<HTML> <FONT color=\"#000099\"><U>Sign up</U></FONT>"
                + "</HTML>");
        buttonsPanel.add(signupButton,"new");
        loginPanel.add(buttonsPanel, "growx, span");
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(loginPanel);
        add(buttonPanel, "dock center");
    }

    public Home() {
        init();
    }
}
