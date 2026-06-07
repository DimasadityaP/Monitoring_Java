package components;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class SearchBox extends RoundedPanel {
    private JTextField txtSearch;
    private JButton btnSearch;

    public SearchBox() {
        super(12);
        init();
    }

    private void init() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setBorder(new EmptyBorder(0, 8, 0, 0));
        setPreferredSize(new Dimension(360, 42));

        txtSearch = new JTextField();
        txtSearch.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtSearch.setBorder(null);
        txtSearch.setOpaque(false);

        btnSearch = new JButton("⌕");
        btnSearch.setFont(new Font("Segoe UI", Font.BOLD, 24));
        btnSearch.setFocusPainted(false);
        btnSearch.setBorderPainted(false);
        btnSearch.setContentAreaFilled(false);
        btnSearch.setPreferredSize(new Dimension(48, 42));

        add(txtSearch, BorderLayout.CENTER);
        add(btnSearch, BorderLayout.EAST);
    }

    public String getText() {
        return txtSearch.getText();
    }

    public void setText(String text) {
        txtSearch.setText(text);
    }

    public JTextField getTextField() {
        return txtSearch;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.BLACK);
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, getRadius(), getRadius());
        g2.dispose();
    }
}
