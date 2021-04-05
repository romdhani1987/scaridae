package fr.romdhani.scaridae.gui.panels.home;

import fr.romdhani.scaridae.controller.UserController;
import fr.romdhani.scaridae.core.orm.Address;
import fr.romdhani.scaridae.core.orm.Service;
import fr.romdhani.scaridae.core.orm.UserAccount;
import fr.romdhani.scaridae.core.orm.UserFunction;
import fr.romdhani.scaridae.gui.renders.SimpleListCellRender;
import fr.romdhani.scaridae.utils.email.GenerateEncryptionPassword;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.List;

/**
 * @author aromdhani
 */
public class SignupPanel extends JPanel {

    private UserController userController;

    private final JLabel LoginExistError = new JLabel("Login error !");
    private final JLabel passwordError = new JLabel("Passwords error !");
    private final JLabel emailError = new JLabel("Email address error !");

    private final JTextField firstNameField = new JTextField();
    private final JTextField secondNameField = new JTextField();
    private final JTextField mailField = new JTextField();
    private final JTextField phoneField = new JTextField();
    private final JTextField loginField = new JTextField();

    private final JTextField streetField = new JTextField();
    private final JTextField cityField = new JTextField();
    private final JTextField codeField = new JTextField();
    private final JTextField countryField = new JTextField();

    private JComboBox<UserFunction> functionCBox;
    private JComboBox<Service> serviceCBox;
    private JComboBox<String> groupCBox = new JComboBox<>();
    private JComboBox<String> companyCBox = new JComboBox<>();

    private final JPasswordField firstPasswordField = new JPasswordField();
    private final JPasswordField secondPasswordField = new JPasswordField();
    private final JButton singInButton = new JButton("Create account");
    private final JButton cancelButton = new JButton("Cancel");

    private Runnable onSuccess = () -> {
    };
    private Runnable onFailure = () -> {
    };
    private Runnable onCancel = () -> {
    };

    public Runnable getOnSuccess() {
        return onSuccess;
    }

    public void setOnSuccess(Runnable onSuccess) {
        this.onSuccess = onSuccess;
    }

    public Runnable getOnFailure() {
        return onFailure;
    }

    public void setOnFailure(Runnable onFailure) {
        this.onFailure = onFailure;
    }

    public Runnable getOnCancel() {
        return onCancel;
    }

    public void setOnCancel(Runnable onCancel) {
        this.onCancel = onCancel;
    }

    private void init() {
        String layoutConstraint = "width :180: ,wrap";
        LoginExistError.setForeground(Color.RED);
        passwordError.setForeground(Color.RED);
        emailError.setForeground(Color.RED);
        updateFields(false);

        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new MigLayout());
        JLabel loginLabel = new JLabel("Login (*): ");
        JLabel firstPasswordLabel = new JLabel("Password (*): ");
        JLabel secondPasswordLabel = new JLabel("Confirm Password (*): ");
        setLayout(new MigLayout());
        loginPanel.add(loginLabel);
        loginPanel.add(loginField, "width :180:");
        loginPanel.add(LoginExistError, layoutConstraint);
        loginPanel.add(firstPasswordLabel);
        loginPanel.add(firstPasswordField, layoutConstraint);
        loginPanel.add(secondPasswordLabel);
        loginPanel.add(secondPasswordField, "width :180:");
        loginPanel.add(passwordError, layoutConstraint);

        loginPanel.add(new JLabel("First name: "));
        loginPanel.add(firstNameField, layoutConstraint);
        loginPanel.add(new JLabel("Last name: "));
        loginPanel.add(secondNameField, layoutConstraint);

        loginPanel.add(new JLabel("Phone number: "));
        loginPanel.add(phoneField, layoutConstraint);
        loginPanel.add(new JLabel("Email address (*): "));
        loginPanel.add(mailField, "width :180:");
        loginPanel.add(emailError, layoutConstraint);

        loginPanel.add(new JSeparator(), "wrap");
        loginPanel.add(new JSeparator(), "wrap");
        loginPanel.add(new JLabel("Address "), layoutConstraint);

        loginPanel.add(new JLabel("Street: "));
        loginPanel.add(streetField, layoutConstraint);
        loginPanel.add(new JLabel("City: "));
        loginPanel.add(cityField, layoutConstraint);
        loginPanel.add(new JLabel("Code: "));
        loginPanel.add(codeField, layoutConstraint);
        loginPanel.add(new JLabel("Country: "));
        loginPanel.add(countryField, layoutConstraint);

        loginPanel.add(new JSeparator(), "wrap");
        loginPanel.add(new JSeparator(), "wrap");

        loginPanel.add(new JLabel("Function: "));
        functionCBox = new JComboBox<>();
        functionCBox.setRenderer(new SimpleListCellRender());
        loginPanel.add(functionCBox, layoutConstraint);


        loginPanel.add(new JLabel("Service: "));
        serviceCBox = new JComboBox<>();
        serviceCBox.setRenderer(new SimpleListCellRender());
        loginPanel.add(serviceCBox, layoutConstraint);

        loginPanel.add(new JLabel("Group: "));
        loginPanel.add(groupCBox, layoutConstraint);

        loginPanel.add(new JLabel("Company: "));
        loginPanel.add(companyCBox, layoutConstraint);

        JPanel buttonsPanel = new JPanel(new FlowLayout());
        buttonsPanel.add(singInButton);
        singInButton.addActionListener(this::signup);
        buttonsPanel.add(cancelButton);
        cancelButton.addActionListener(this::cancel);
        loginPanel.add(new JSeparator(), "wrap");
        loginPanel.add(new JSeparator(), "wrap");
        loginPanel.add(buttonsPanel, "growx, span");
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(loginPanel);
        add(buttonPanel, "dock center");
    }

    private void cancel(ActionEvent actionEvent) {
        onCancel.run();
    }

    private void updateFields(boolean isVisible) {
        List<JLabel> list = Arrays.asList(LoginExistError, emailError, passwordError);
        list.forEach(l -> l.setVisible(isVisible));
    }

    public SignupPanel(UserController userController) {
        this.userController = userController;
        init();
    }

    private boolean isLoginAvailable() {
        if (!loginField.getText().isBlank() && userController.isLoginAvailable(loginField.getText())) {
            LoginExistError.setVisible(false);
            return true;
        } else {
            LoginExistError.setVisible(true);
            return false;
        }
    }

    private boolean isValidPass() {
        if (firstPasswordField.getText().equals(secondPasswordField.getText()) &&
                firstPasswordField.getText().length() > 7 && secondPasswordField.getText().length() > 7) {
            passwordError.setVisible(false);
            return true;
        } else {
            passwordError.setVisible(true);
            return false;
        }
    }

    private boolean isValidMail() {
        if (mailField.getText().matches("^(.+)@(.+)$")) {
            emailError.setVisible(false);
            return true;
        } else {
            emailError.setVisible(true);
            return false;
        }
    }

    private boolean checkFields() {
        return isLoginAvailable() && isValidPass() && isValidMail();
    }

    private void signup(ActionEvent e) {
        if (checkFields()) {
            UserAccount userAccount = new UserAccount();
            userAccount.setFirstName(firstNameField.getText());
            userAccount.setLastName(secondNameField.getText());
            userAccount.setLogin(loginField.getText());
            userAccount.setMail(mailField.getText());
            userAccount.setPasswordHash(GenerateEncryptionPassword.generate(secondPasswordField.getText()));
            userAccount.setPhone(phoneField.getText());
            Address address = new Address();
            address.setStreet(streetField.getText());
            address.setCity(cityField.getText());
            address.setCode(codeField.getText());
            address.setCountry(countryField.getText());
            userAccount.setAddress(address);
            userController.signup(userAccount, address);
            onSuccess.run();
        } else {
            onFailure.run();
        }
    }
}
