package fr.romdhani.scaridae.gui.panels.home;

import fr.romdhani.scaridae.controller.EventBusDispatcher;
import fr.romdhani.scaridae.controller.UserController;
import fr.romdhani.scaridae.core.database.DBEntityManager;
import fr.romdhani.scaridae.utils.window.WindowUtil;
import net.miginfocom.swing.MigLayout;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ConnectionPanel extends JPanel {
    private static final Logger logger = LogManager.getLogger(DBEntityManager.class);
    private final JTextField loginField = new JTextField();
    private final JPasswordField passwordField = new JPasswordField();
    private final JButton singInButton = new JButton("Sign in");
    private final JButton cancelButton = new JButton("Cancel");
    private final JButton signupButton = new JButton();
    private final JLabel errorSignIn = new JLabel("Failed to sign in!");

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
        singInButton.addActionListener(e -> signInPerformed());
        singInButton.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    signInPerformed();
                }
            }
        });
        buttonsPanel.add(cancelButton);
        cancelButton.addActionListener(this::cancelActionPerformed);
        signupButton.setText("<HTML> <FONT color=\"#000099\"><U>Sign up</U></FONT>"
                + "</HTML>");
        errorSignIn.setForeground(Color.RED);
        errorSignIn.setVisible(false);
        signupButton.addActionListener(e -> signupPerforemd());
        buttonsPanel.add(signupButton, "new");
        loginPanel.add(buttonsPanel, "growx, span,wrap");
        loginPanel.add(new JLabel(""), "span2");

        loginPanel.add(errorSignIn, "growx");
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(loginPanel);
        add(buttonPanel, "dock center");

    }

    private void cancelActionPerformed(ActionEvent actionEvent) {
        clearFields();
        errorSignIn.setVisible(false);
    }

    private void clearFields() {
        loginField.setText("");
        passwordField.setText("");
        loginField.grabFocus();
    }

    private void signInPerformed() {
        if (UserController.getInstance().signIn(loginField.getText(), passwordField.getText())) {
            errorSignIn.setVisible(false);
            MainPanel mainPanel = new MainPanel();
            EventBusDispatcher.getInstance().getEventBus().post(mainPanel);
        } else {
            logger.error("Unable to sign in!");
            errorSignIn.setVisible(true);
            clearFields();
        }
    }

    private void signupPerforemd() {
        JDialog dialog = new JDialog(WindowUtil.findParentWindow());
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
