package fr.romdhani.scaridae.gui.panels.access;


import fr.romdhani.scaridae.controller.CurrentSession;
import fr.romdhani.scaridae.controller.UserController;
import fr.romdhani.scaridae.core.orm.RequestAccess;
import fr.romdhani.scaridae.core.orm.UserAccount;
import fr.romdhani.scaridae.core.orm.enums.request.Labels;
import fr.romdhani.scaridae.core.orm.enums.request.Priority;
import fr.romdhani.scaridae.core.orm.enums.request.RequestGroup;
import fr.romdhani.scaridae.core.orm.enums.request.Status;
import fr.romdhani.scaridae.gui.renders.UserCellRender;
import fr.romdhani.scaridae.utils.window.WindowUtil;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
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

    private final JComboBox<Priority> priorityCBox = new JComboBox<>(Priority.values());
    private final JComboBox<Labels> labelCBox = new JComboBox<>(Labels.values());
    private final JComboBox<RequestGroup> groupCBox = new JComboBox<>(RequestGroup.values());
    private final JComboBox<Status> statusCBox = new JComboBox<>(Status.values());
    private final JComboBox<UserAccount> assigneeCBox = new JComboBox<>(getUsers());
    private final JTextField reporterField = new JTextField();

    public RequestAccess getRequestAccess() {
        return requestAccess;
    }

    private void init() {
        String layoutConstraintWrap = "width :100:,wrap";
        String layoutConstraint = "width :300:, growx, push ";
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
        if (requestAccess != null) {
            nameField.setText(requestAccess.getName());
        }
        nameField.addActionListener(e -> isTitleOk());
        loginPanel.add(nameField, layoutConstraint);
        loginPanel.add(nameError, layoutConstraintWrap);

        loginPanel.add(new JLabel("Description: "));
        if (requestAccess != null) {
            descriptionField.setText(requestAccess.getDescription());
        }
        descriptionField.setLineWrap(true);
        descriptionField.setWrapStyleWord(true);
        descriptionField.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));

        JScrollPane scroller = new JScrollPane(descriptionField);
        scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        loginPanel.add(scroller, layoutConstraint);
        loginPanel.add(descriptionError, layoutConstraintWrap);

        loginPanel.add(new JLabel("Priority: "));
        if (requestAccess != null) {
            priorityCBox.setSelectedItem(Priority.getPriority(requestAccess.getPriority()));
        }
        priorityCBox.addItemListener(e -> priorityChanged());
        loginPanel.add(priorityCBox, layoutConstraint);
        loginPanel.add(priorityError, layoutConstraintWrap);

        loginPanel.add(new JLabel("Label: "));
        if (requestAccess != null) {
            labelCBox.setSelectedItem(Labels.getLabelByName(requestAccess.getLabel()));
        }
        labelCBox.addItemListener(e -> labelsChanged());
        loginPanel.add(labelCBox, layoutConstraint);
        loginPanel.add(labelError, layoutConstraintWrap);

        loginPanel.add(new JLabel("Group: "));
        if (requestAccess != null) {
            groupCBox.setSelectedItem(RequestGroup.getReqGroup(requestAccess.getGroup()));
        }
        groupCBox.addItemListener(e -> reqGroupChanged());
        loginPanel.add(groupCBox, layoutConstraint);
        loginPanel.add(groupError, layoutConstraintWrap);

        loginPanel.add(new JLabel("Status: "));
        if (requestAccess != null) {
            statusCBox.setSelectedItem(Status.getStatus(requestAccess.getStatus()));
        }
        statusCBox.addItemListener(e -> statusChanged());
        loginPanel.add(statusCBox, layoutConstraint);
        loginPanel.add(statusError, layoutConstraintWrap);

        loginPanel.add(new JLabel("Assignee: "));
        if (requestAccess != null) {
            updateField(assigneeCBox, requestAccess.getAssignee());
        }
        assigneeCBox.setRenderer(new UserCellRender());
        assigneeCBox.addActionListener(e -> assignee());
        loginPanel.add(assigneeCBox, layoutConstraint);
        loginPanel.add(assigneeError, layoutConstraintWrap);

        loginPanel.add(new JLabel("Reporter: "));
        if (requestAccess != null) {
            reporterField.setText(requestAccess.getReporter());
        }
        reporterField.setText(CurrentSession.getInstance().getLogin());
        reporterField.setEnabled(false);
        loginPanel.add(reporterField, layoutConstraint);

        add(loginPanel, "dock center");
    }

    private void updateField(JComboBox<UserAccount> field, String login) {
        try {
            field.setSelectedItem(UserController.getInstance().getUserAccountByLogin(requestAccess.getAssignee()));
        } catch (Exception ex) {
            JOptionPane.showConfirmDialog(
                    WindowUtil.findParentWindow(), "Failed to find user " + ex.getMessage(), "Error",
                    JOptionPane.ERROR);
        }
    }

    private UserAccount[] getUsers() {
        return UserController.getInstance().getAllUserAccounts().toArray(UserAccount[]::new);
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

    public boolean add() {
        if (checkFields()) {
            if (requestAccess == null)
                requestAccess = new RequestAccess();
            requestAccess.setName(nameField.getText());
            requestAccess.setDescription(descriptionField.getText());
            requestAccess.setPriority(priorityCBox.getSelectedItem().toString());
            requestAccess.setStatus(statusCBox.getSelectedItem().toString());
            requestAccess.setGroup(groupCBox.getSelectedItem().toString());
            requestAccess.setLabel(labelCBox.getSelectedItem().toString());
            requestAccess.setReporter(CurrentSession.getInstance().getLogin());
            requestAccess.setAssignee(((UserAccount) assigneeCBox.getSelectedItem()).getLogin());
            return true;
        } else {
            requestAccess = null;
            return false;
        }
    }

    public void cancel() {
        updateFields(false);
        nameField.setText("");
        descriptionField.setText("");
        priorityCBox.setSelectedItem(0);
        labelCBox.setSelectedItem(0);
        groupCBox.setSelectedItem(0);
        statusCBox.setSelectedItem(0);
        assigneeCBox.setSelectedItem(0);
        requestAccess = null;
    }

}
