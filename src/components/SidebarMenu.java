/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import java.awt.Color;
import java.awt.*;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.*;
import javax.swing.border.*;

/**
 *
 * @author Dimas Aditya P
 */
public class SidebarMenu extends RoundedPanel {

    public interface MenuListener {
        void onClick(String menu);
    }

    private MenuListener listener;

    public void setMenuListener(MenuListener listener) {
        this.listener = listener;
    }

    public SidebarMenu() {
        super(40); // radius rounded
        init();
    }

    private void init() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(70, 76, 150));
        setPreferredSize(new Dimension(220, 0));

        add(Box.createVerticalStrut(20));

        add(createMenu("Dashboard"));
        add(createSubMenu("Konstruksi"));
        add(createSubMenu("Pengadaan"));

        add(Box.createVerticalStrut(20));

        add(createMenu("Report"));
        add(createSubMenu("Project"));
        add(createSubMenu("Logistic"));
        add(createSubMenu("Administration"));
        add(createSubMenu("Reimbursement"));
    }

    private JLabel createMenu(String text) {
        JLabel lbl = new JLabel(text);
        lbl.setForeground(Color.WHITE);
        lbl.setBorder(new EmptyBorder(10, 15, 10, 10));
        lbl.setCursor(new Cursor(Cursor.HAND_CURSOR));

        addHoverEffect(lbl, text, true);

        return lbl;
    }

    private JLabel createSubMenu(String text) {
        JLabel lbl = new JLabel("   " + text);
        lbl.setForeground(Color.LIGHT_GRAY);
        lbl.setBorder(new EmptyBorder(5, 25, 5, 10));
        lbl.setCursor(new Cursor(Cursor.HAND_CURSOR));

        addHoverEffect(lbl, text, false);

        return lbl;
    }

    private void addHoverEffect(JLabel lbl, String text, boolean isMain) {
        lbl.addMouseListener(new java.awt.event.MouseAdapter() {

            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (listener != null) {
                    listener.onClick(text);
                }
            }

            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbl.setOpaque(true);
                lbl.setBackground(new Color(90, 96, 170));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbl.setOpaque(false);
            }
        });
    }
}
