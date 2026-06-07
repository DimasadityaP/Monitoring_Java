package components;

import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class PageTitle extends JLabel {
    public PageTitle() {
        super("PROJECT ADMINISTRATION");
        setFont(new Font("Segoe UI", Font.BOLD, 32));
        setForeground(RoundedColors.PRIMARY);
        setHorizontalAlignment(SwingConstants.RIGHT);
    }
}
