package fr.romdhani.scaridae.gui.table;

import fr.romdhani.scaridae.core.orm.RequestAccess;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;


/**
 * @author aromdhani
 */
public class AccessRequestModel extends AbstractTableModel {
    private final String[] header = {"Name", "Reference"};
    private List<RequestAccess> userStatisticsList = new ArrayList<>();

    @Override
    public int getRowCount() {
        return userStatisticsList.size();
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
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return userStatisticsList.get(rowIndex).getName();
            case 1:
                return userStatisticsList.get(rowIndex).getRef();
            default:
                return null; //Error
        }
    }

    public Object getValueAt(int rowIndex) {
        return userStatisticsList.get(rowIndex);
    }


    /**
     * Add a user statis wrapper
     *
     * @param requestAccess The user to add.
     */
    public void addUserStatis(RequestAccess requestAccess) {
        userStatisticsList.add(requestAccess);
        fireTableRowsInserted(userStatisticsList.size() - 1, userStatisticsList.size() - 1);
    }

    /**
     * Remove all  user statis wrapper
     */
    public void removeAll() {
        userStatisticsList.clear();
        fireTableRowsInserted(userStatisticsList.size() - 1, userStatisticsList.size() - 1);
    }

    /**
     * Add users in batch
     *
     * @param serieWrapperList The users list to add.
     */
    public void addAll(List<RequestAccess> serieWrapperList) {
        userStatisticsList.addAll(serieWrapperList);
        fireTableRowsInserted(userStatisticsList.size() - 1, userStatisticsList.size() - 1);
    }

    /**
     * Set all in batch
     *
     * @param serieWrapperList The users list to add.
     */
    public void setAll(List<RequestAccess> serieWrapperList) {
        userStatisticsList.clear();
        userStatisticsList.addAll(serieWrapperList);
        fireTableRowsInserted(userStatisticsList.size() - 1, userStatisticsList.size() - 1);
    }

    /**
     * Remove  user statis wrapper
     *
     * @param rowIndex The index of the selected SerieWrapper.
     */
    public void removeUserStatisWrapper(int rowIndex) {
        userStatisticsList.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }

    @Override
    public Class getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return String.class;
            default:
                return Object.class;
        }
    }
}



