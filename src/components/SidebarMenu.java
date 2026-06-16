package components;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

public class SidebarMenu extends RoundedPanel {

    public interface MenuListener {
        void onClick(String menuKey);
    }

    private MenuListener listener;
    private JLabel activeMenu = null;

    private final Color GOLD = new Color(212, 175, 55);
    private final Color WHITE = Color.WHITE;

    private Map<JLabel, java.util.List<JLabel>> menuMap = new HashMap<JLabel, java.util.List<JLabel>>();

    public SidebarMenu() {
        super(40);
        init();
    }

    public void setMenuListener(MenuListener listener) {
        this.listener = listener;
    }

    private void init() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(65, 71, 150));
        setPreferredSize(new Dimension(260, 700));
        setBorder(new EmptyBorder(25, 15, 25, 15));

        JLabel dashboard = createMainMenu(
                "dashboard",
                "Dashboard",
                "/image/icons/dashboard_white.png",
                "/image/icons/dashboard_gold.png"
        );
        add(dashboard);

        add(Box.createVerticalStrut(8));

        JLabel masterdata = createMainMenu(
                "masterdata",
                "Master Data",
                "/image/icons/folder_white.png",
                "/image/icons/folder_gold.png"
        );
        add(masterdata);

        java.util.List<JLabel> masterSub = new ArrayList<JLabel>();
        masterSub.add(createSubMenu("master.barang", "Barang"));
        masterSub.add(createSubMenu("master.administrasi", "Administrasi"));
        masterSub.add(createSubMenu("master.user", "User"));
        masterSub.add(createSubMenu("master.reimbursement", "Reimbursement"));

        addSubMenus(masterSub);
        menuMap.put(masterdata, masterSub);
        setArrow(masterdata, false);

        add(Box.createVerticalStrut(8));

        JLabel transaksi = createMainMenu(
                "transaksi",
                "Transaksi",
                "/image/icons/transaction_white.png",
                "/image/icons/transaction_gold.png"
        );
        add(transaksi);

        java.util.List<JLabel> transaksiSub = new ArrayList<JLabel>();
        transaksiSub.add(createSubMenu("transaksi.project", "Project"));
        transaksiSub.add(createSubMenu("transaksi.mutasi_barang", "Mutasi Barang"));

        addSubMenus(transaksiSub);
        menuMap.put(transaksi, transaksiSub);
        setArrow(transaksi, false);

        add(Box.createVerticalStrut(8));

        JLabel report = createMainMenu(
                "report",
                "Report",
                "/image/icons/report_white.png",
                "/image/icons/report_gold.png"
        );
        add(report);

        java.util.List<JLabel> reportSubs = new ArrayList<JLabel>();
        reportSubs.add(createSubMenu("report.project", "Project"));
        reportSubs.add(createSubMenu("report.logistic", "Logistic"));
        reportSubs.add(createSubMenu("report.administration", "Administration"));
        reportSubs.add(createSubMenu("report.reimbursement", "Reimbursement"));

        addSubMenus(reportSubs);
        menuMap.put(report, reportSubs);
        setArrow(report, false);
    }

    private void addSubMenus(java.util.List<JLabel> subs) {
        for (JLabel lbl : subs) {
            lbl.setVisible(false);
            add(lbl);
        }
    }

    private JLabel createMainMenu(String key, String text, String iconNormal, String iconActive) {
        JLabel lbl = new JLabel(text);
        lbl.putClientProperty("menuKey", key);
        lbl.putClientProperty("text", text);
        lbl.putClientProperty("iconNormal", iconNormal);
        lbl.putClientProperty("iconActive", iconActive);
        lbl.putClientProperty("defaultColor", WHITE);

        lbl.setIcon(loadIcon(iconNormal, 20, 20));
        lbl.setIconTextGap(12);
        lbl.setForeground(WHITE);
        lbl.setBorder(new EmptyBorder(10, 12, 10, 12));
        lbl.setCursor(new Cursor(Cursor.HAND_CURSOR));

        addMenuEffect(lbl, key, WHITE);

        return lbl;
    }

    private JLabel createSubMenu(String key, String text) {
        JLabel lbl = new JLabel(text);
        lbl.putClientProperty("menuKey", key);
        lbl.putClientProperty("text", text);
        lbl.putClientProperty("iconNormal", "/image/icons/arrow_right_white.png");
        lbl.putClientProperty("iconActive", "/image/icons/arrow_right_gold.png");
        lbl.putClientProperty("defaultColor", WHITE);

        lbl.setIcon(loadIcon("/image/icons/arrow_right_white.png", 12, 12));
        lbl.setIconTextGap(10);
        lbl.setForeground(WHITE);
        lbl.setBorder(new EmptyBorder(6, 35, 6, 10));
        lbl.setCursor(new Cursor(Cursor.HAND_CURSOR));

        addMenuEffect(lbl, key, WHITE);

        return lbl;
    }

    private void addMenuEffect(final JLabel lbl, final String menuKey, final Color defaultColor) {
        lbl.addMouseListener(new java.awt.event.MouseAdapter() {

            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                boolean isParent = menuMap.containsKey(lbl);

                if (isParent) {
                    toggleMenu(lbl);
                }

                if (activeMenu != null) {
                    resetMenu(activeMenu);
                }

                activeMenu = lbl;
                lbl.setForeground(GOLD);
                setMenuIcon(lbl, true);

                if (!isParent && listener != null) {
                    listener.onClick(menuKey);
                }

                repaint();
            }

            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                if (lbl != activeMenu) {
                    lbl.setForeground(GOLD);
                    setMenuIcon(lbl, true);
                }
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                if (lbl != activeMenu) {
                    lbl.setForeground(defaultColor);
                    setMenuIcon(lbl, false);
                }
            }
        });
    }

    private void toggleMenu(JLabel parent) {
        java.util.List<JLabel> subs = menuMap.get(parent);

        if (subs == null || subs.isEmpty()) {
            return;
        }

        boolean visible = subs.get(0).isVisible();

        for (JLabel lbl : subs) {
            lbl.setVisible(!visible);
        }

        setArrow(parent, !visible);
        revalidate();
        repaint();
    }

    private void setArrow(JLabel lbl, boolean expanded) {
        String text = (String) lbl.getClientProperty("text");

        if (text == null) {
            text = lbl.getText();
        }

        lbl.setText(expanded ? text + "    v" : text + "    >");
    }

    private void resetMenu(JLabel lbl) {
        Color defaultColor = (Color) lbl.getClientProperty("defaultColor");

        if (defaultColor == null) {
            defaultColor = WHITE;
        }

        lbl.setForeground(defaultColor);
        setMenuIcon(lbl, false);
    }

    private void setMenuIcon(JLabel lbl, boolean active) {
        String iconPath = active
                ? (String) lbl.getClientProperty("iconActive")
                : (String) lbl.getClientProperty("iconNormal");

        if (iconPath != null) {
            int size = iconPath.contains("arrow") ? 12 : 20;
            lbl.setIcon(loadIcon(iconPath, size, size));
        }
    }

    private ImageIcon loadIcon(String path, int width, int height) {
        java.net.URL url = getClass().getResource(path);

        if (url == null) {
            return null;
        }

        ImageIcon icon = new ImageIcon(url);
        Image image = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);

        return new ImageIcon(image);
    }
}