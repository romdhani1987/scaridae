package fr.romdhani.scaridae.gui;

import com.google.common.eventbus.Subscribe;
import fr.romdhani.scaridae.gui.panels.home.MainPanel;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
    private MainPanel mainPanel;

    public Frame() throws HeadlessException {
    }

    public Frame(String title) throws HeadlessException {
        super(title);
    }

    public Frame(String title, GraphicsConfiguration gc) {
        super(title, gc);
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
