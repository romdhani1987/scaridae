package fr.romdhani.scaridae.gui.panels.access;

import fr.romdhani.scaridae.controller.ConfigLoader;
import fr.romdhani.scaridae.controller.RequestController;
import fr.romdhani.scaridae.controller.UserController;
import fr.romdhani.scaridae.core.orm.RequestAccess;
import fr.romdhani.scaridae.gui.panels.commons.IRequest;
import fr.romdhani.scaridae.gui.table.model.AccessRequestModel;
import fr.romdhani.scaridae.utils.email.EmailClient;
import fr.romdhani.scaridae.utils.email.EmailData;
import fr.romdhani.scaridae.utils.email.GeneratePlainPassword;
import fr.romdhani.scaridae.utils.window.WindowUtil;
import lombok.NonNull;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.swingx.JXDatePicker;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;

public class RequestAccessPanel extends JPanel implements IRequest {

    private final JTextField searchTextField = new JTextField();
    private final JPanel accessRequestPanel = new JPanel();
    private final JTable requestAccessTable = new JTable();
    private final AccessRequestModel accessRequestModel = new AccessRequestModel();
    private final JButton newRequest = new JButton("New");
    private final JButton editRequest = new JButton("Edit");
    private final JButton removeRequest = new JButton("Remove");
    private final RequestController requestController;


    private void init() {
        try {
            JPanel accessPanel = new JPanel();
            accessPanel.setLayout(new MigLayout());
            JPanel toolbarPanel = new JPanel(new MigLayout(""));
            JLabel nameLabel = new JLabel(" Name: ");
            toolbarPanel.add(nameLabel, "width 80:80:100");
            toolbarPanel.add(searchTextField, "width :180:, height :25:");

            JLabel startDateLabel = new JLabel(" From:");
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            JXDatePicker startDatePicker = new JXDatePicker();
            startDatePicker.setFormats(dateFormat);
            startDatePicker.setPreferredSize(new Dimension(100, 25));

            toolbarPanel.add(startDateLabel, "width :80:");
            toolbarPanel.add(startDatePicker, "width :80:");

            JLabel endDateLabel = new JLabel(" To:");
            JXDatePicker endDatePicker = new JXDatePicker();
            endDatePicker.setFormats(dateFormat);
            endDatePicker.setPreferredSize(new Dimension(100, 25));

            toolbarPanel.add(endDateLabel, "width 80:80:100");
            toolbarPanel.add(endDatePicker, "width :80:");

            JButton searchButton = new JButton("Search");
            toolbarPanel.add(searchButton, "width :80:,wrap");
            accessRequestModel.addAll(requestController.getAllRequestList());
            requestAccessTable.setModel(accessRequestModel);
            accessPanel.add(toolbarPanel, ",wrap");
            accessPanel.add(new JScrollPane(requestAccessTable), ",span,grow,push");

            JScrollPane tableScrollPane = new JScrollPane(accessPanel);
            JSplitPane documentSplitPane = new JSplitPane(
                    JSplitPane.HORIZONTAL_SPLIT, tableScrollPane, accessRequestPanel);
            documentSplitPane.setResizeWeight(0.8);

            JScrollPane scrollPane = new JScrollPane(createBottomPanel());
            JSplitPane rightSplitPane = new JSplitPane(
                    JSplitPane.VERTICAL_SPLIT, documentSplitPane, scrollPane);
            rightSplitPane.setResizeWeight(0.8);
            this.setLayout(new MigLayout(""));

            add(rightSplitPane, "height 95%, width 100%");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(WindowUtil.findParentWindow(), "Failed to initialize " + ex);
        }
    }

    private JPanel createBottomPanel() {
        JPanel bottomPanel = new JPanel(new MigLayout());
        newRequest.addActionListener(e -> newRequest());
        editRequest.addActionListener(e -> editRequest());
        removeRequest.addActionListener(e -> removeRequest());
        bottomPanel.add(newRequest, "w 80:80:");
        bottomPanel.add(editRequest, "w 80:80:");
        bottomPanel.add(removeRequest, "w 80:80:");
        return bottomPanel;
    }

    private void removeRequest() {
        int question = JOptionPane.showConfirmDialog(
                WindowUtil.findParentWindow(), "Do you really want to delete the request? ", "Delete Access Request",
                JOptionPane.YES_NO_OPTION);
        if (question == 0) {
            try {
                RequestAccess selectedRequestAccess = accessRequestModel.getValueAt(requestAccessTable.getSelectedRow());
                requestController.RemoveRequestAccess(selectedRequestAccess);
                accessRequestModel.removeRequest(requestAccessTable.getSelectedRow());
                JOptionPane.showMessageDialog(WindowUtil.findParentWindow(), "Deleted successfully!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(WindowUtil.findParentWindow(), "Failed to create a new request:" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void editRequest() {
        try {
            RequestAccess selectedRequestAccess = accessRequestModel.getValueAt(requestAccessTable.getSelectedRow());
            EditRequestDialog editRequestDialog = new EditRequestDialog(WindowUtil.findParentWindow(), "Edit Access Request", selectedRequestAccess);
            editRequestDialog.setSize(new Dimension(650, 650));
            editRequestDialog.setLocationRelativeTo(null);
            editRequestDialog.setModal(true);
            editRequestDialog.setVisible(true);
            if (editRequestDialog.getRequestAccess() != null) {
                RequestAccess requestAccess = editRequestDialog.getRequestAccess();
                requestController.editRequestAccess(requestAccess);
                accessRequestModel.addAccessRequest(requestAccess);
                if (!UserController.getInstance().getUserAccountByLogin(requestAccess.getAssignee()).isEmpty()) {
                    notifyAssignee(requestAccess);
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(WindowUtil.findParentWindow(), "Failed to edit the selected request:" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void newRequest() {
        try {
            NewRequestDialog requestDialog = new NewRequestDialog(WindowUtil.findParentWindow(), "New Access Request");
            requestDialog.setSize(new Dimension(650, 650));
            requestDialog.setModal(true);
            requestDialog.setLocationRelativeTo(null);
            requestDialog.setVisible(true);
            if (requestDialog.getRequestAccess() != null) {
                RequestAccess requestAccess = requestDialog.getRequestAccess();
                requestController.addAccessRequest(requestAccess);
                accessRequestModel.addAccessRequest(requestAccess);
                if (!UserController.getInstance().getUserAccountByLogin(requestAccess.getAssignee()).isEmpty()) {
                    notifyAssignee(requestAccess);
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(WindowUtil.findParentWindow(), "Failed to create a new request:" + ex.getMessage());
        }
    }

    private void notifyAssignee(@NonNull RequestAccess requestAccess) {
        try {
            EmailData emailData = createEmailData();
            emailData.setTo(UserController.getInstance().getUserAccountByLogin(requestAccess.getAssignee()).get(0).getMail());
            emailData.setMessageTitle(String.format(ConfigLoader.getInstance().getEmailDefaultTitle(), requestAccess.getName()));
            emailData.setMessageAsHtml(String.format(ConfigLoader.getInstance().getEmailDefaultMessage(), requestAccess.getDescription()));
            EmailClient.send(emailData);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(WindowUtil.findParentWindow(), "Failed to send an email:" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public RequestAccessPanel(@NonNull RequestController requestController) {
        this.requestController = requestController;
        init();
    }

    private EmailData createEmailData() {
        EmailData emailData = new EmailData();
        emailData.setSenderEmail(ConfigLoader.getInstance().getEmailSender());
        emailData.setSenderPass(GeneratePlainPassword.generate(ConfigLoader.getInstance().getEmailPassSender()));
        emailData.setHost(ConfigLoader.getInstance().getEmailHost());
        emailData.setPort(Integer.parseInt(ConfigLoader.getInstance().getEmailPort()));
        emailData.setAuth(Boolean.parseBoolean(ConfigLoader.getInstance().getEmailAuth()));
        emailData.setMessageTitle(ConfigLoader.getInstance().getEmailDefaultTitle());
        emailData.setMessageAsHtml(ConfigLoader.getInstance().getEmailDefaultMessage());
        emailData.setUseTls(Boolean.parseBoolean(ConfigLoader.getInstance().getEmailIsUseTls()));
        return emailData;
    }

}
