/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * VisiblePane.java
 *
 * Created on Dec 30, 2012, 8:22:50 PM
 */
package subliminal.ui;

import java.awt.Color;
import java.awt.Container;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import subliminal.TrayMain;

/**
 * Used as base pane for splashing images
 * http://docs.oracle.com/javase/tutorial/uiswing/misc/trans_shaped_windows.html
 * http://today.java.net/pub/a/today/2008/03/18/translucent-and-shaped-swing-windows.html
 * @author Satyen Shimpi
 */
public class InVisiblePane extends javax.swing.JFrame {

	private static final long serialVersionUID = -3814743415490599449L;

	static InVisiblePane instance= null;
    /** Creates new form VisiblePane */
    private InVisiblePane() {
    	super();
    	setUndecorated(true);
    	setBackground(new Color(0,0,0,0));
    	setLocationRelativeTo(null);
    	setContentPane( createContentPane());
    	setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    	setAlwaysOnTop(true);
    	setFocusableWindowState(false);
//        setForeground(java.awt.Color.white);

    	setListeners();
    	setContentPaneListeners();
        setLabel();
        setContentPaneLayout();
        
//        this.setOpacity(0.5F);
//        AWTUtilities.setWindowOpaque(this, false);        
        pack();
    }

	private void setContentPaneLayout() {
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        layout.setAutoCreateContainerGaps(true);
        layout.setAutoCreateGaps(true);
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(lblMessage, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
        			.addContainerGap())
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(Alignment.LEADING, layout.createSequentialGroup()
        			.addGap(112)
        			.addComponent(lblMessage)
        			.addContainerGap(134, Short.MAX_VALUE))
        );
        getContentPane().setLayout(layout);
	}

	private void setLabel() {
		lblMessage = new javax.swing.JLabel("à¤¸à¤¤à¥�à¤¯à¥‡à¤¨ à¤¶à¤¿à¤‚à¤ªà¥€");
        lblMessage.setIcon(new ImageIcon(TrayMain.class.getResource("./images.png")));
        lblMessage.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
        lblMessage.setText("");
        lblMessage.setToolTipText("This is a Subliminal Message");
        lblMessage.setAutoscrolls(true);
        lblMessage.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lblMessage.setFocusable(false);
	}

	private void setListeners() {
		addMouseMotionListener(new MouseMotionAdapter() {
    		@Override
    		public void mouseDragged(MouseEvent arg0) {
    			System.out.println("Mouse dragged");
    		}
    		@Override
    		public void mouseMoved(MouseEvent arg0) {
    			System.out.println("Mouse Moved");
    		}
    	});
    	addMouseListener(new MouseAdapter() {
    		@Override
    		public void mouseClicked(MouseEvent arg0) {
    			System.out.println("Mouse clicked");
    		}
    		@Override
    		public void mouseEntered(MouseEvent e) {
    			System.out.println("Mouse Entered");
    		}
    		@Override
    		public void mouseExited(MouseEvent e) {
    			System.out.println("Mouse Exit");
    		}
    		
    		@Override
    		public void mousePressed(MouseEvent e) {
    			System.out.println("Mouse Pressed");
    		}
    		@Override
    		public void mouseReleased(MouseEvent e) {
    			System.out.println("Mouse Released");
    		}
    	});
	}

	private void setContentPaneListeners() {
		getContentPane().addMouseMotionListener(new MouseMotionAdapter() {
    		@Override
    		public void mouseDragged(MouseEvent arg0) {
    			System.out.println("Mouse dragged");
    		}
    		@Override
    		public void mouseMoved(MouseEvent arg0) {
    			System.out.println("Mouse Moved");
    		}
    	});
    	getContentPane().addMouseListener(new MouseAdapter() {
    		@Override
    		public void mouseClicked(MouseEvent arg0) {
    			System.out.println("Mouse clicked");
    		}
    		@Override
    		public void mouseEntered(MouseEvent e) {
    			System.out.println("Mouse Entered");
    		}
    		@Override
    		public void mouseExited(MouseEvent e) {
    			System.out.println("Mouse Exite");
    		}
    		
    		@Override
    		public void mousePressed(MouseEvent e) {
    			System.out.println("Mouse Pressed");
    		}
    		@Override
    		public void mouseReleased(MouseEvent e) {
    			System.out.println("Mouse Released");
    		}
    	});
	}
    
    private Container createContentPane(){
    	JPanel panel = new JPanel() {
            /**
			 * 
			 */
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
        };
        return panel;
    }
    
    public static InVisiblePane getInstance(){
        if(instance==null)
            instance=new InVisiblePane();
        return instance;
    }
   
    private javax.swing.JLabel lblMessage;
	public javax.swing.JLabel getLblMessage() {
		return lblMessage;
	}

	public void setLblMessage(javax.swing.JLabel lblMessage) {
		this.lblMessage = lblMessage;
	}
    
}