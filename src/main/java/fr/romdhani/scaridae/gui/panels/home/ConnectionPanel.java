package fr.romdhani.scaridae.gui.panels.home;

import fr.romdhani.scaridae.controller.UserController;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ConnectionPanel extends JPanel {

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
        singInButton.addActionListener(this::signinPerforemd);
        buttonsPanel.add(cancelButton);
        cancelButton.addActionListener(this::cancelActionPerformed);
        signupButton.setText("<HTML> <FONT color=\"#000099\"><U>Sign up</U></FONT>"
                + "</HTML>");
        signupButton.addActionListener(this::signupPerforemd);
        buttonsPanel.add(signupButton, "new");
        loginPanel.add(buttonsPanel, "growx, span");
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(loginPanel);
        add(buttonPanel, "dock center");
    }

    private void cancelActionPerformed(ActionEvent actionEvent) {
        clearFields();
    }

    private void clearFields() {
        loginField.setText("");
        passwordField.setText("");
        loginField.grabFocus();
    }

    private void signinPerforemd(ActionEvent actionEvent) {
        if (UserController.getInstance().signIn(loginField.getText(), passwordField.getText())) {
            System.out.println("Signed in");
        } else {
            System.out.println("Error!");
            clearFields();
        }
    }

    private void signupPerforemd(ActionEvent actionEvent) {
        JDialog dialog = new JDialog();
        SignupPanel signupPanel = new SignupPanel(UserController.getInstance());
        signupPanel.setOnSuccess(() -> {
            dialog.dispose();
        });
        signupPanel.setOnCancel(() -> {
            dialog.dispose();
        });
        dialog.setTitle("Sign up");
        dialog.setContentPane(signupPanel);
        dialog.setSize(new Dimension(800, 650));
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    public ConnectionPanel() {
        init();
    }
}
