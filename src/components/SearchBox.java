package components;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class SearchBox extends RoundedPanel {
    private JTextField txtSearch;
    private final String[] placeholder = {"Search..."};

    public SearchBox() {
        super(12);
        init();
    }

    private void init() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setBorder(new EmptyBorder(0, 8, 0, 0));
        setPreferredSize(new Dimension(360, 42));

        txtSearch = new JTextField() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                if (getText().isEmpty() && !isFocusOwner() && !placeholder[0].isEmpty()) {
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setColor(new Color(170, 170, 170));
                    g2.setFont(getFont());

                    Insets insets = getInsets();
                    FontMetrics fm = g2.getFontMetrics();
                    int y = (getHeight() - fm.getHeight()) / 2 + fm.getAscent();

                    g2.drawString(placeholder[0], insets.left + 2, y);
                    g2.dispose();
                }
            }
        };

        txtSearch.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtSearch.setBorder(null);
        txtSearch.setOpaque(false);


        add(txtSearch, BorderLayout.CENTER);
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
    
    public void setPlaceholder(String placeholder) {
        this.placeholder[0] = placeholder;
        txtSearch.repaint();
    }

    public String getPlaceholder() {
        return placeholder[0];
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
