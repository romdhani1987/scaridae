package fr.romdhani.scaridae.gui.renders;


import fr.romdhani.scaridae.core.orm.UserAccount;

import javax.swing.*;
import java.awt.*;

public class UserCellRender implements ListCellRenderer {
    protected DefaultListCellRenderer defaultRenderer = new DefaultListCellRenderer();

    public Component getListCellRendererComponent(JList list, Object value, int index,
                                                  boolean isSelected, boolean cellHasFocus) {
        String theText = null;
        JLabel renderer = (JLabel) defaultRenderer.getListCellRendererComponent(list, value, index,
                isSelected, cellHasFocus);

        if (value instanceof UserAccount) {
            theText = ((UserAccount) value).getLogin();
        } else {
            theText = "Not found";
        }
        renderer.setText(theText);
        return renderer;
    }
}


