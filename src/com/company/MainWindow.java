package com.company;

import com.company.Drawers.DrawerEnum;
import com.company.utils.MyObserver;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class MainWindow extends JFrame {
    private DrawPanel drawPanel;

    private MyObserver myObserver;

    private JComboBox<DrawerEnum> comboBox;
    public MainWindow() throws HeadlessException {
        drawPanel = new DrawPanel();

        Label label = new Label("Рисуем с помощью алгоритма: ");
        label.setAlignment(Label.LEFT);
        comboBox = new JComboBox<>(DrawerEnum.values());
        comboBox.setEditable(false);

        //comboBox.setAlignmentY(Component.RIGHT_ALIGNMENT);
        //comboBox.setAlignmentY(Component.BOTTOM_ALIGNMENT);
        myObserver = drawPanel.getListener();
        comboBox.addActionListener((event) -> {
            myObserver.typeChanged((DrawerEnum) comboBox.getSelectedItem());
            drawPanel.repaint();
        });
        myObserver.typeChanged((DrawerEnum) comboBox.getSelectedItem());
        JPanel comboPanel = new JPanel();
        comboPanel.add(label);
        comboPanel.add(comboBox);
        this.add(drawPanel);
        this.add(comboPanel, BorderLayout.SOUTH);
    }
}
