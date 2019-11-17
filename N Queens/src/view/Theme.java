/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author HSM & Tobi
 */
public class Theme extends JFrame {
    int width, height;
    String title;
    JPanel buttonsPanel;
    JButton themeButtns[];
    int marginComponents;
    public Theme() {
        width = 600;
        height = 600;
        marginComponents = 10;
        themeButtns = new JButton[2];
        title = "8 Queens";
        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.LEFT,marginComponents,marginComponents));
        
        this.setSize(width, height);
        this.setLocationRelativeTo(this);
        this.setResizable(false);
        for (int i = 0; i < themeButtns.length; i++) {
            themeButtns[i] = new JButton("Theme "+(i+1));
            buttonsPanel.add(themeButtns[i]);
        }
        JLabel background=new JLabel(new ImageIcon("c.png"));
        add(background);
      //  this.add(buttonsPanel);
        this.setTitle(title);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.pack();
    }

}
