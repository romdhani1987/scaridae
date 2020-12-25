package fr.romdhani.scaridae.gui.panels.home;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class Home extends JPanel {

    private JTextField loginField = new JTextField();
    private JPasswordField passwordField = new JPasswordField();
    private JButton singInButton = new JButton("Sign in");
    private JButton cancelButton = new JButton("Cancel");
    private JButton signupButton = new JButton("Sign up");

    private void init() {
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new MigLayout());
        JLabel loginLabel = new JLabel("Login");
        JLabel passwordLabel = new JLabel("Password");
        setLayout(new MigLayout());
        loginPanel.add(loginLabel);
        loginPanel.add(loginField, "width :200: ");
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField, "width :200: ");
        JPanel buttonsPanel = new JPanel(new FlowLayout());
        buttonsPanel.add(singInButton);
        buttonsPanel.add(cancelButton);
        JPanel connectionPanel = new JPanel(new MigLayout());
        connectionPanel.add(new JLabel(""),"span,growx,push");
        connectionPanel.add(loginPanel, "wrap");
        connectionPanel.add(buttonsPanel, "span 4");
        add(connectionPanel, "dock north");
    }

    public Home() {
        init();
    }
}
