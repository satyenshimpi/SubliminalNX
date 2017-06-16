package subliminal.ui;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;

import javax.swing.JPanel;

public class TranslucentPanel extends JPanel {
	
	private static final long serialVersionUID = -6608657692379054499L;
	
	@Override
    protected void paintComponent(Graphics g) {
        if (g instanceof Graphics2D) {
            final int R = 240;
            final int G = 240;
            final int B = 240;

            Paint p =
                new GradientPaint(0.0f, 0.0f, new Color(R, G, B, 0),
                    0.0f, getHeight(), new Color(R, G, B, 0), true);
            Graphics2D g2d = (Graphics2D)g;
            g2d.setPaint(p);
            g2d.fillRect(0, 0, getWidth(), getHeight());
        }
    }


}
