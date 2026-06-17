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
                if ("dashboard".equals(menu)) {
                    next = new Dashboard();
                }

                else if ("master.barang".equals(menu)) {
//                    next = new MasterBarangFrame();
                }

                else if ("master.administrasi".equals(menu)) {
                    next = new AdministrationFormFrame();
                }

                else if ("master.user".equals(menu)) {
                    next = new UserListFrame1();
                }

                else if ("master.reimbursement".equals(menu)) {
                    next = new ReimbursementFormFrame();
                } 
                else if ("Mutasi Barang".equals(menu)) {
                    next = new MutasiBarangListFrame();
                }

                else if ("transaksi.project".equals(menu)) {
                    next = new ProjectAdministrationListFrame();
                }

                else if ("transaksi.mutasi_barang".equals(menu)) {
                    next = new MutasiBarangListFrame();
                }

                else if ("report.project".equals(menu)) {
                    next = new ProjectAdministrationListFrame();
                }

                else if ("report.logistic".equals(menu)) {
                    next = new BarangFormFrame();
                }

                else if ("report.administration".equals(menu)) {
                    next = new AdministrationFormFrame();
                }

                else if ("report.reimbursement".equals(menu)) {
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
