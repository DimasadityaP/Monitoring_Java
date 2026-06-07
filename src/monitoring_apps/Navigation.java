package monitoring_apps;

import javax.swing.JFrame;
import components.SidebarMenu;

public final class Navigation {
    private Navigation() { }

    public static void bind(final SidebarMenu sidebar, final JFrame currentFrame) {
        if (sidebar == null) return;
        sidebar.setMenuListener(new SidebarMenu.MenuListener() {
            public void onClick(String menu) {
                JFrame next = null;
                if ("Dashboard".equals(menu) || "Konstruksi".equals(menu) || "Pengadaan".equals(menu)) {
                    next = new DashboardAdministrationFrame();
                } else if ("Project".equals(menu)) {
                    next = new ProjectAdministrationListFrame();
                } else if ("Logistic".equals(menu)) {
                    next = new LogisticFormFrame();
                } else if ("Administration".equals(menu)) {
                    next = new AdministrationFormFrame();
                } else if ("Reimbursement".equals(menu)) {
                    next = new ReimbursementFormFrame();
                }
                if (next != null) {
                    next.setVisible(true);
                    currentFrame.dispose();
                }
            }
        });
    }

    public static void go(JFrame currentFrame, JFrame nextFrame) {
        if (nextFrame != null) {
            nextFrame.setVisible(true);
        }
        if (currentFrame != null) {
            currentFrame.dispose();
        }
    }
}
