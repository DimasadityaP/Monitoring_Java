package components;

import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class SidebarMenu extends RoundedPanel {

    public interface MenuListener {
        void onClick(String menu);
    }

    private MenuListener listener;
    private final Map<JLabel, java.util.List<JLabel>> menuMap = new HashMap<JLabel, java.util.List<JLabel>>();
    private JLabel activeMenu;

    public SidebarMenu() {
        super(70);
        init();
    }

    public void setMenuListener(MenuListener listener) {
        this.listener = listener;
    }

    private void init() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(RoundedColors.PRIMARY);
        setPreferredSize(new Dimension(300, 720));
        setMinimumSize(new Dimension(260, 500));
        setBorder(new EmptyBorder(95, 28, 20, 28));

        JLabel dashboard = createMainMenu("Dashboard", "");
        add(dashboard);

        JLabel masterdata = createMainMenu("Master Data", "▾");
        add(masterdata);

        java.util.List<JLabel> masterSub = new ArrayList<JLabel>();
        masterSub.add(createSubMenu("Barang"));
        masterSub.add(createSubMenu("Administrasi"));
        masterSub.add(createSubMenu("User"));
        masterSub.add(createSubMenu("Reimbursement"));
        addSubMenus(masterSub);
        menuMap.put(masterdata, masterSub);

        JLabel transaksi = createMainMenu("Transaksi", "▾");
        add(transaksi);

        java.util.List<JLabel> transaksiSub = new ArrayList<JLabel>();
        transaksiSub.add(createSubMenu("Project"));
        transaksiSub.add(createSubMenu("Mutasi Barang"));
        addSubMenus(transaksiSub);
        menuMap.put(transaksi, transaksiSub);

        add(Box.createVerticalStrut(15));

        JLabel report = createMainMenu("Report", "▾");
        add(report);

        java.util.List<JLabel> reportSubs = new ArrayList<JLabel>();
        reportSubs.add(createSubMenu("Project"));
        reportSubs.add(createSubMenu("Logistic"));
        reportSubs.add(createSubMenu("Administration"));
        reportSubs.add(createSubMenu("Reimbursement"));
        addSubMenus(reportSubs);
        menuMap.put(report, reportSubs);
    }

    private void addSubMenus(java.util.List<JLabel> subs) {
        for (int i = 0; i < subs.size(); i++) {
            JLabel lbl = subs.get(i);
            lbl.setVisible(true);
            add(lbl);
        }
    }

    private JLabel createMainMenu(String text, String arrow) {
        JLabel lbl = new JLabel(getMainText(text, arrow));
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 22));
        lbl.setForeground(Color.WHITE);
        lbl.setBorder(new EmptyBorder(10, 0, 10, 0));
        lbl.setCursor(new Cursor(Cursor.HAND_CURSOR));
        addMenuEffect(lbl, text, Color.WHITE, true);
        return lbl;
    }

    private String getMainText(String text, String arrow) {
        if (arrow == null || arrow.trim().length() == 0) {
            return "▰  " + text;
        }
        return "▰  " + text + "                         " + arrow;
    }

    private JLabel createSubMenu(String text) {
        JLabel lbl = new JLabel("   ▸  " + text);
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 21));
        lbl.setForeground(Color.WHITE);
        lbl.setBorder(new EmptyBorder(5, 28, 5, 0));
        lbl.setCursor(new Cursor(Cursor.HAND_CURSOR));
        addMenuEffect(lbl, text, Color.WHITE, false);
        return lbl;
    }

    private void addMenuEffect(final JLabel lbl, final String text, final Color normalColor, final boolean isMain) {
        lbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (isMain) {
                    toggleMenu(lbl);
                }
                setActive(lbl);
                if (listener != null) {
                    listener.onClick(text);
                }
            }

            public void mouseEntered(java.awt.event.MouseEvent evt) {
                if (lbl != activeMenu) {
                    lbl.setForeground(RoundedColors.GOLD);
                }
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                if (lbl != activeMenu) {
                    lbl.setForeground(normalColor);
                }
            }
        });
    }

    private void setActive(JLabel selected) {
        if (activeMenu != null && activeMenu != selected) {
            activeMenu.setForeground(Color.WHITE);
        }
        activeMenu = selected;
        activeMenu.setForeground(RoundedColors.GOLD);
    }

    private void toggleMenu(JLabel parent) {
        java.util.List<JLabel> subs = menuMap.get(parent);
        if (subs == null || subs.isEmpty()) {
            return;
        }
        boolean visible = subs.get(0).isVisible();
        for (int i = 0; i < subs.size(); i++) {
            subs.get(i).setVisible(!visible);
        }
        revalidate();
        repaint();
    }
}
