package components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Window;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

public class UserProfileCard extends RoundedPanel {

    public interface UserActionListener {
        void onProfileClick();
        void onLogoutClick();
    }

    private JLabel lblAvatar;
    private JLabel lblUserName;
    private JLabel lblRole;
    private JLabel lblArrow;

    private String userName = "User_02463";
    private String roleName = "Div. Administration";

    private JPopupMenu popupMenu;
    private UserActionListener listener;

    public UserProfileCard() {
        super(35);
        init();
    }

    public void setUserActionListener(UserActionListener listener) {
        this.listener = listener;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
        if (lblUserName != null) {
            lblUserName.setText(userName);
        }
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
        if (lblRole != null) {
            lblRole.setText(roleName);
        }
    }

    private void init() {
        setLayout(new BorderLayout(10, 0));
        setBackground(new Color(217, 217, 217));
        setPreferredSize(new Dimension(260, 75));
        setBorder(new EmptyBorder(10, 15, 10, 15));
        setCursor(new Cursor(Cursor.HAND_CURSOR));

        lblAvatar = new JLabel();
        lblAvatar.setPreferredSize(new Dimension(45, 45));
        lblAvatar.setHorizontalAlignment(JLabel.CENTER);
        lblAvatar.setVerticalAlignment(JLabel.CENTER);
        lblAvatar.setIcon(loadIcon("/image/icons/user_navy.png", 32, 32));

        JPanel textPanel = new JPanel(new GridLayout(2, 1));
        textPanel.setOpaque(false);

        lblUserName = new JLabel(userName);
        lblUserName.setForeground(new Color(41, 45, 86));
        lblUserName.setFont(new Font("Segoe UI", Font.BOLD, 14));

        lblRole = new JLabel(roleName);
        lblRole.setForeground(new Color(41, 45, 86));
        lblRole.setFont(new Font("Segoe UI", Font.PLAIN, 12));

        textPanel.add(lblUserName);
        textPanel.add(lblRole);

        lblArrow = new JLabel();
        lblArrow.setHorizontalAlignment(JLabel.CENTER);
        lblArrow.setIcon(loadIcon("/image/icons/arrow_down_navy.png", 14, 14));

        add(lblAvatar, BorderLayout.WEST);
        add(textPanel, BorderLayout.CENTER);
        add(lblArrow, BorderLayout.EAST);

        initPopup();
        bindClick(this);
        bindClick(lblAvatar);
        bindClick(lblUserName);
        bindClick(lblRole);
        bindClick(lblArrow);
        bindClick(textPanel);
    }

    private void initPopup() {
        popupMenu = new JPopupMenu();
        popupMenu.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220), 1));

        JMenuItem profileItem = new JMenuItem("Profil Saya");
        profileItem.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        profileItem.setForeground(new Color(41, 45, 86));
        profileItem.setIcon(loadIcon("/image/icons/user_navy.png", 16, 16));

        JMenuItem logoutItem = new JMenuItem("Logout");
        logoutItem.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        logoutItem.setForeground(new Color(154, 61, 120));
        logoutItem.setIcon(loadIcon("/image/icons/logout_pink.png", 16, 16));

        profileItem.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                if (listener != null) {
                    listener.onProfileClick();
                }
            }
        });

        logoutItem.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                if (listener != null) {
                    listener.onLogoutClick();
                } else {
                    int response = JOptionPane.showConfirmDialog(
                        null,
                        "Apakah Anda yakin ingin logout?",
                        "Konfirmasi Logout",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE
                    );

                    if (response == JOptionPane.YES_OPTION) {
                        Window ancestor = SwingUtilities.getWindowAncestor(UserProfileCard.this);
                        if (ancestor instanceof JFrame) {
                            monitoring_apps.Navigation.go((JFrame) ancestor, new monitoring_apps.LoginFrame());
                        } else if (ancestor != null) {
                            monitoring_apps.LoginFrame loginFrame = new monitoring_apps.LoginFrame();
                            loginFrame.setVisible(true);
                            ancestor.dispose();
                        }
                    }
                }
            }
        });

        popupMenu.add(profileItem);
        popupMenu.addSeparator();
        popupMenu.add(logoutItem);
    }

    private void bindClick(Component comp) {
        comp.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                popupMenu.show(UserProfileCard.this, 0, getHeight() + 5);
            }
        });
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