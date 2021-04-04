package fr.romdhani.scaridae.gui.table.model;

import fr.romdhani.scaridae.core.orm.RequestAccess;
import fr.romdhani.scaridae.core.orm.RequestStatus;
import fr.romdhani.scaridae.core.orm.RequestType;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;


/**
 * Request Model
 *
 * @author aromdhani
 */
public class AccessRequestModel extends AbstractTableModel {
    private final String[] header = {"Name", "Reference", "Description", "Priority", "Assignee", "Reporter", "Label", "Group", "Creation Time", "Last Modification", "Status"};
    private List<RequestAccess> requestAccessList = new ArrayList<>();

    @Override
    public int getRowCount() {
        return requestAccessList.size();
    }

    @Override
    public int getColumnCount() {
        return header.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return header[columnIndex];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return requestAccessList.get(rowIndex).getName();
            case 1:
                return requestAccessList.get(rowIndex).getRef();
            case 2:
                return requestAccessList.get(rowIndex).getDescription();
            case 3:
                return requestAccessList.get(rowIndex).getPriority();
            case 4:
                return requestAccessList.get(rowIndex).getAssignee();
            case 5:
                return requestAccessList.get(rowIndex).getReporter();
            case 6:
                return requestAccessList.get(rowIndex).getLabel();
            case 7:
                return requestAccessList.get(rowIndex).getGroup();
            case 8:
                return requestAccessList.get(rowIndex).getCreationTime();
            case 9:
                return requestAccessList.get(rowIndex).getLastModificationTime();
            case 10:
                return requestAccessList.get(rowIndex).getStatus();
            default:
                return null; //Error
        }
    }

    public Object getValueAt(int rowIndex) {
        return requestAccessList.get(rowIndex);
    }

    public AccessRequestModel() {
        super();
    }

    /**
     * @param requestAccess The request to add.
     */
    public void addAccessRequest(RequestAccess requestAccess) {
        requestAccessList.add(requestAccess);
        fireTableRowsInserted(requestAccessList.size() - 1, requestAccessList.size() - 1);
    }

    /**
     * Remove all  user statis wrapper
     */
    public void removeAll() {
        requestAccessList.clear();
        fireTableRowsInserted(requestAccessList.size() - 1, requestAccessList.size() - 1);
    }

    /**
     * Add users in batch
     *
     * @param serieWrapperList The users list to add.
     */
    public void addAll(List<RequestAccess> serieWrapperList) {
        requestAccessList.addAll(serieWrapperList);
        fireTableRowsInserted(requestAccessList.size() - 1, requestAccessList.size() - 1);
    }

    /**
     * Set all in batch
     *
     * @param requestList The request list to add.
     */
    public void setAll(List<RequestAccess> requestList) {
        requestAccessList.clear();
        requestAccessList.addAll(requestList);
        fireTableRowsInserted(requestAccessList.size() - 1, requestAccessList.size() - 1);
    }

    /**
     * Remove
     *
     * @param rowIndex The index of the selected request.
     */
    public void removeRequest(int rowIndex) {
        requestAccessList.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }

    @Override
    public Class getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 6:
                return RequestType.class;
            case 7:
                return RequestStatus.class;
            default:
                return Object.class;
        }
    }
}



