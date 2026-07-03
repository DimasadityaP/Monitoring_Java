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
                    next = new BarangList();
                }

                else if ("master.administrasi".equals(menu)) {
                    next = new AdministarationList();
                }

                else if ("master.user".equals(menu)) {
                    next = new UserListFrame1();
                }

                else if ("master.mutasi_barang".equals(menu)) {
                        next = new MutasiBarangListFrame();
                    }
               
                else if ("Mutasi Barang".equals(menu)) {
                    next = new MutasiBarangListFrame();
                }

                else if ("transaksi.project".equals(menu)) {
                    next = new ProjectList();
                }

                else if ("transaksi.reimbursement".equals(menu)) {
                    next = new ReimbursementListFrame();
                }

                else if ("report.project".equals(menu)) {
                    next = new ProjectList();
                }

                else if ("report.logistic".equals(menu)) {
                    next = new BarangList();
                }

                else if ("report.administration".equals(menu)) {
                    next = new AdministarationList();
                }

              else if ("report.reimbursement".equals(menu)) {
                    next = new ReimbursementListFrame();
                }
                
                if (next != null) {
                    navigate(currentFrame, next);
                }
            }
        });
    }

    public static void go(JFrame currentFrame, JFrame nextFrame) {
        if (nextFrame != null) {
            navigate(currentFrame, nextFrame);
        }
    }

    private static void navigate(final JFrame currentFrame, final JFrame nextFrame) {
        if (currentFrame != null) {
            int state = currentFrame.getExtendedState();
            nextFrame.setExtendedState(state);
            if ((state & JFrame.MAXIMIZED_BOTH) != JFrame.MAXIMIZED_BOTH) {
                nextFrame.setBounds(currentFrame.getBounds());
            }
        }
        nextFrame.setVisible(true);
        if (currentFrame != null) {
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    currentFrame.dispose();
                }
            });
        }
    }
}
