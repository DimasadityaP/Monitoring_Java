package components;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class UserProfileCard extends RoundedPanel {
    private JLabel lblAvatar;
    private JLabel lblUser;
    private JLabel lblDivision;
    private JLabel lblArrow;
    private JPopupMenu popup;

    public UserProfileCard() {
        super(30);
        init();
        setUserName("User_02463");
        setDivision("Div. Administration");
    }

    private void init() {
        setLayout(new BorderLayout(10, 0));
        setBackground(RoundedColors.SOFT_GRAY);
        setPreferredSize(new Dimension(360, 96));
        setMinimumSize(new Dimension(280, 82));
        setBorder(new EmptyBorder(10, 16, 10, 18));
        setCursor(new Cursor(Cursor.HAND_CURSOR));

        lblAvatar = new JLabel() {
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.WHITE);
                g2.fillOval(0, 0, getWidth(), getHeight());
                g2.dispose();
            }
        };
        lblAvatar.setPreferredSize(new Dimension(72, 72));
        lblAvatar.setOpaque(false);

        JPanel textPanel = new JPanel();
        textPanel.setOpaque(false);
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));

        lblUser = new JLabel();
        lblUser.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblUser.setForeground(Color.BLACK);
        lblUser.setAlignmentX(Component.LEFT_ALIGNMENT);

        lblDivision = new JLabel();
        lblDivision.setFont(new Font("Segoe UI", Font.PLAIN, 22));
        lblDivision.setForeground(Color.BLACK);
        lblDivision.setAlignmentX(Component.LEFT_ALIGNMENT);

        textPanel.add(Box.createVerticalGlue());
        textPanel.add(lblUser);
        textPanel.add(lblDivision);
        textPanel.add(Box.createVerticalGlue());

        lblArrow = new JLabel("▾");
        lblArrow.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblArrow.setForeground(Color.BLACK);
        lblArrow.setHorizontalAlignment(SwingConstants.CENTER);
        lblArrow.setPreferredSize(new Dimension(38, 72));

        add(lblAvatar, BorderLayout.WEST);
        add(textPanel, BorderLayout.CENTER);
        add(lblArrow, BorderLayout.EAST);

        popup = new JPopupMenu();
        JMenuItem profile = new JMenuItem("Profil Saya");
        JMenuItem logout = new JMenuItem("Logout");
        popup.add(profile);
        popup.add(logout);

        java.awt.event.MouseAdapter click = new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                popup.show(UserProfileCard.this, 120, getHeight() - 5);
            }
        };
        addMouseListener(click);
        lblAvatar.addMouseListener(click);
        lblUser.addMouseListener(click);
        lblDivision.addMouseListener(click);
        lblArrow.addMouseListener(click);
    }

    public String getUserName() {
        return lblUser.getText();
    }

    public void setUserName(String userName) {
        lblUser.setText(userName);
    }

    public String getDivision() {
        return lblDivision.getText();
    }

    public void setDivision(String division) {
        lblDivision.setText(division);
    }
}
