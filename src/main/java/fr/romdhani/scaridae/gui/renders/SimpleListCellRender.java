package fr.romdhani.scaridae.gui.renders;

import fr.romdhani.scaridae.core.orm.Service;
import fr.romdhani.scaridae.core.orm.UserFunction;

import javax.swing.*;
import java.awt.*;

public class SimpleListCellRender implements ListCellRenderer {
    protected DefaultListCellRenderer defaultRenderer = new DefaultListCellRenderer();

    public Component getListCellRendererComponent(JList list, Object value, int index,
                                                  boolean isSelected, boolean cellHasFocus) {
        String theText = null;
        JLabel renderer = (JLabel) defaultRenderer.getListCellRendererComponent(list, value, index,
                isSelected, cellHasFocus);

        if (value instanceof UserFunction) {
            theText = ((UserFunction) value).getName();
        } else if (value instanceof Service) {
            theText = ((Service) value).getName();
        } else {
            theText = "Not found";
        }
        renderer.setText(theText);
        return renderer;
    }
}


