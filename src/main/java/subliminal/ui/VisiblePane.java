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
import java.awt.Dimension;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import subliminal.common.Constants;
import subliminal.common.SettingsVariables;
//import com.sun.awt.AWTUtilities;

/**
 * Used as base pane for scrolling text
 * http://docs.oracle.com/javase/tutorial/uiswing/misc/trans_shaped_windows.html
 * http://today.java.net/pub/a/today/2008/03/18/translucent-and-shaped-swing-windows.html
 * @author Satyen S Shimpi
 */
public class VisiblePane extends javax.swing.JFrame {

	private static final long serialVersionUID = 340091960015455083L;
	private static Logger log = LogManager.getLogger(VisiblePane.class.getName());
	
	static VisiblePane instance= null;
    /** Creates new form VisiblePane */
    private VisiblePane() {
    	setUndecorated(true);
    	setBackground(new Color(0,0,0,0));
    	setLocationRelativeTo(null);
    	setContentPane( new TranslucentPanel());
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        setFocusable(false);
        setFocusableWindowState(false);
        
    	setLabel();
    	setContentPaneLayout();
        pack();

//        AWTUtilities.setWindowOpacity(this, 0.05F);
//        this.setOpacity(0.5F);
//        AWTUtilities.setWindowOpaque(this, false);        
    }
    
    public static VisiblePane getInstance(){
        if(instance==null)
            instance=new VisiblePane();
        return instance;
    }
    
    private javax.swing.JLabel lblMessage;
	public javax.swing.JLabel getLblMessage() {
		return lblMessage;
	}

	public void setLblMessage(javax.swing.JLabel lblMessage) {
		this.lblMessage = lblMessage;
	}
    
	private void setLabel() {
        if (SettingsVariables.getInstance().getMsgDisplayType().equals(Constants.MSG_DISPLAY_TYPE_FLSHNIG)){
            lblMessage = new javax.swing.JLabel("à¤¸à¤¤à¥�à¤¯à¥‡à¤¨ à¤¶à¤¿à¤‚à¤ªà¥€");
        } else {
            lblMessage = new ScrollJLabel("à¤¸à¤¤à¥�à¤¯à¥‡à¤¨ à¤¶à¤¿à¤‚à¤ªà¥€");
            Thread t = new Thread((ScrollJLabel) lblMessage);
            t.start();
            t.setName(this.getClass().getName());
        }
        lblMessage.setForeground(SettingsVariables.getInstance().getForeground());
        lblMessage.setBackground(SettingsVariables.getInstance().getBackground());        
        lblMessage.setFont(SettingsVariables.getInstance().getFont());
        lblMessage.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lblMessage.setText("Subliminal Message");
        lblMessage.setToolTipText("This is a Subliminal Message");
        lblMessage.setAutoscrolls(true);
        lblMessage.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        
        log.info("settingsVariables.getInstance().getOnTop()  "+ SettingsVariables.getInstance().getOnTop());
        lblMessage.repaint();
        Dimension d = lblMessage.getSize();
        log.info("Lable Size " +d.width + " - " + d.height);
//        d.setSize(d.width, d.height);
        //set size from settings form
        d.setSize(SettingsVariables.getInstance().getMsgWidth(),
                SettingsVariables.getInstance().getMsgWidth());
        lblMessage.setSize(d);
	}

	private void setContentPaneLayout(){
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblMessage, javax.swing.GroupLayout.DEFAULT_SIZE, 937, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(144, Short.MAX_VALUE)
                .addComponent(lblMessage))
        );
        getContentPane().setLayout(layout);
	}

    /**
     * @param args the command line arguments
     */
    public static void main1(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VisiblePane.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VisiblePane.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VisiblePane.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VisiblePane.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new VisiblePane().setVisible(true);
            }
        });
    }

}