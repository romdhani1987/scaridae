package fr.romdhani.scaridae.gui;

import com.google.common.eventbus.Subscribe;
import fr.romdhani.scaridae.gui.panels.home.MainPanel;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {

    public Frame(String title) throws HeadlessException {
        super(title);
    }

    @Subscribe
    public void setContentPanel(MainPanel mainPanel) {
        this.getContentPane().removeAll();
        this.setJMenuBar(mainPanel.getMenuBar());
        this.getContentPane().add(mainPanel);
        this.getContentPane().revalidate();
        this.repaint();
    }
}
