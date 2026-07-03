package components;

import java.awt.*;
import javax.swing.*;

public class ScrollablePanel extends JPanel implements Scrollable {
    public ScrollablePanel() {
        super();
    }

    public ScrollablePanel(LayoutManager layout) {
        super(layout);
    }

    @Override
    public Dimension getPreferredScrollableViewportSize() {
        return getPreferredSize();
    }

    @Override
    public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction) {
        return 16;
    }

    @Override
    public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation, int direction) {
        return 64;
    }

    @Override
    public boolean getScrollableTracksViewportWidth() {
        return true; // Stretch panel horizontally to match scrollpane viewport width
    }

    @Override
    public boolean getScrollableTracksViewportHeight() {
        return false; // Do not stretch vertically, allowing scrollbar to appear
    }
}
