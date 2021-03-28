package fr.romdhani.scaridae.gui.panels.access;


import fr.romdhani.scaridae.controller.CurrentSession;
import fr.romdhani.scaridae.controller.UserController;
import fr.romdhani.scaridae.core.orm.*;
import fr.romdhani.scaridae.core.orm.enums.request.Labels;
import fr.romdhani.scaridae.core.orm.enums.request.Priority;
import fr.romdhani.scaridae.core.orm.enums.request.RequestGroup;
import fr.romdhani.scaridae.core.orm.enums.request.Status;
import fr.romdhani.scaridae.gui.renders.UserCellRender;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.List;

/**
 * @author aromdhani
 */
public class NewRequestPanel extends JPanel {

    private RequestAccess requestAccess;
    private final JLabel nameError = new JLabel("Name error !");
    private final JLabel descriptionError = new JLabel("Details error !");
    private final JLabel priorityError = new JLabel("Priority error !");
    private final JLabel labelError = new JLabel("Label error !");
    private final JLabel groupError = new JLabel("Group error !");
    private final JLabel statusError = new JLabel("Status error !");
    private final JLabel assigneeError = new JLabel("Status error !");

    private final JTextField nameField = new JTextField();
    private final JTextArea descriptionField = new JTextArea(15, 20);

    private JComboBox<Priority> priorityCBox;
    private JComboBox<Labels> labelCBox;
    private JComboBox<RequestGroup> groupCBox;
    private JComboBox<Status> statusCBox;
    private JComboBox<UserAccount> assigneeCBox;
    private final JTextField reporterField = new JTextField();

    private final JButton validateButton = new JButton("Valid");
    private final JButton cancelButton = new JButton("Cancel");

    private Runnable onSuccess = () -> {
    };
    private Runnable onFailure = () -> {
    };
    private Runnable onCancel = () -> {
    };

    public RequestAccess getRequestAccess() {
        return requestAccess;
    }

    public void setRequestAccess(RequestAccess requestAccess) {
        this.requestAccess = requestAccess;
    }

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
        String layoutConstraintWrap = "width :100: ,wrap";
        String layoutConstraint = "width :300:";
        nameError.setForeground(Color.RED);
        descriptionError.setForeground(Color.RED);
        priorityError.setForeground(Color.RED);
        labelError.setForeground(Color.RED);
        groupError.setForeground(Color.RED);
        statusError.setForeground(Color.RED);
        assigneeError.setForeground(Color.RED);

        updateFields(false);

        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new MigLayout());

        setLayout(new MigLayout());
        loginPanel.add(new JLabel("Title: "));
        nameField.addActionListener(e -> isTitleOk());
        loginPanel.add(nameField, layoutConstraint);
        loginPanel.add(nameError, layoutConstraintWrap);

        loginPanel.add(new JLabel("Description: "));
        descriptionField.setLineWrap(true);
        descriptionField.setWrapStyleWord(true);
        descriptionField.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));

        JScrollPane scroller = new JScrollPane(descriptionField);
        scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        loginPanel.add(scroller, layoutConstraint);
        loginPanel.add(descriptionError, layoutConstraintWrap);

        loginPanel.add(new JLabel("Priority: "));
        priorityCBox = new JComboBox<>(Priority.values());
        priorityCBox.addItemListener(e -> priorityChanged());
        loginPanel.add(priorityCBox, layoutConstraint);
        loginPanel.add(priorityError, layoutConstraintWrap);


        loginPanel.add(new JLabel("Label: "));
        labelCBox = new JComboBox<>(Labels.values());
        labelCBox.addItemListener(e -> labelsChanged());
        loginPanel.add(labelCBox, layoutConstraint);
        loginPanel.add(labelError, layoutConstraintWrap);


        loginPanel.add(new JLabel("Group: "));
        groupCBox = new JComboBox<>(RequestGroup.values());
        groupCBox.addItemListener(e -> reqGroupChanged());
        loginPanel.add(groupCBox, layoutConstraint);
        loginPanel.add(groupError, layoutConstraintWrap);

        loginPanel.add(new JLabel("Status: "));
        statusCBox = new JComboBox<>(Status.values());
        statusCBox.addItemListener(e -> statusChanged());
        loginPanel.add(statusCBox, layoutConstraint);
        loginPanel.add(statusError, layoutConstraintWrap);

        loginPanel.add(new JLabel("Assignee: "));
        List<UserAccount> userAccountList = UserController.getInstance().getAllUserAccounts();
        UserAccount[] userAccounts = new UserAccount[userAccountList.size()];
        userAccountList.toArray(userAccounts);

        assigneeCBox = new JComboBox<>(userAccounts);
        assigneeCBox.setRenderer(new UserCellRender());
        assigneeCBox.addActionListener(e -> assignee());
        loginPanel.add(assigneeCBox, layoutConstraint);
        loginPanel.add(assigneeError, layoutConstraintWrap);

        loginPanel.add(new JLabel("Reporter: "));
        reporterField.setText(CurrentSession.getInstance().getLogin());
        reporterField.setEnabled(false);
        loginPanel.add(reporterField, layoutConstraint);

        JPanel buttonsPanel = new JPanel(new FlowLayout());
        buttonsPanel.add(validateButton);
        validateButton.addActionListener(this::add);
        buttonsPanel.add(cancelButton);
        cancelButton.addActionListener(this::cancel);
        loginPanel.add(new JSeparator(), "wrap");
        loginPanel.add(new JSeparator(), "wrap");
        loginPanel.add(buttonsPanel, "growx, span");
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(loginPanel);
        add(buttonPanel, "dock center");
    }

    private boolean isTitleOk() {
        if (!nameField.getText().isEmpty()) {
            nameError.setVisible(false);
            return true;
        } else {
            nameError.setVisible(true);
            return false;
        }
    }

    private boolean isDescOk() {
        if (!descriptionField.getText().isEmpty()) {
            descriptionError.setVisible(false);
            return true;
        } else {
            descriptionError.setVisible(true);
            return false;
        }
    }

    private boolean assignee() {
        if (assigneeCBox.getSelectedItem() != null) {
            assigneeError.setVisible(false);
            return true;
        } else {
            statusError.setVisible(true);
            return false;
        }
    }

    private boolean statusChanged() {
        if (statusCBox.getSelectedItem() != null) {
            statusError.setVisible(false);
            return true;
        } else {
            statusError.setVisible(true);
            return false;
        }
    }

    private boolean reqGroupChanged() {
        if (groupCBox.getSelectedItem() != null) {
            groupError.setVisible(false);
            return true;
        } else {
            groupError.setVisible(true);
            return false;
        }
    }

    private boolean labelsChanged() {
        if (labelCBox.getSelectedItem() != null) {
            labelError.setVisible(false);
            return true;
        } else {
            labelError.setVisible(true);
            return false;
        }
    }

    private boolean priorityChanged() {
        if (priorityCBox.getSelectedItem() != null) {
            priorityError.setVisible(false);
            return true;
        } else {
            priorityError.setVisible(true);
            return false;
        }
    }

    private void cancel(ActionEvent actionEvent) {
        onCancel.run();
    }

    private void updateFields(boolean isVisible) {
        List<JLabel> list = Arrays.asList(nameError, descriptionError, priorityError, labelError, groupError, statusError, assigneeError);
        list.forEach(l -> l.setVisible(isVisible));
    }

    public NewRequestPanel(RequestAccess requestAccess) {
        this.requestAccess = requestAccess;
        init();
    }

    public NewRequestPanel() {
        init();
    }


    private boolean checkFields() {
        return assignee() && statusChanged() && reqGroupChanged() && labelsChanged() && priorityChanged() && isTitleOk() && isDescOk();
    }

    private void add(ActionEvent e) {
        if (checkFields()) {
            requestAccess = new RequestAccess(nameField.getText(), descriptionField.getText());
            requestAccess.setRequestPriority(priorityCBox.getSelectedItem().toString());
            requestAccess.setAssignee(((UserAccount)assigneeCBox.getSelectedItem()).getLogin());
            requestAccess.setRequestGroup(groupCBox.getSelectedItem().toString());
            requestAccess.setRequestLabel(labelCBox.getSelectedItem().toString());
            onSuccess.run();
        } else {
            System.err.println("can not create access request!");
            onFailure.run();
        }
    }
}
