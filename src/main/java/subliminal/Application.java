/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * File : Subliminal.Application.java
 * Created : Jan 7, 2013
 * By : Satyen S Shimpi
 */
package subliminal;

import java.awt.Dimension;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.SystemTray;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import subliminal.common.ReadAffirmImages;
import subliminal.common.ReadAffirms;
import subliminal.common.settingsVariables;
import subliminal.ui.InVisiblePane;
import subliminal.ui.SettingsForm;
import subliminal.ui.VisiblePane;

/**
 * The <code>Application</code> class represents
 * @author Satyen S Shimpi
 * @version 0.0
 * @since version 0.0
 */
/**
 *
 * @author t0763ss
 */
public class Application {
    public static VisiblePane vp = null;
    
    public static void main(String[] args) {
		int response = JOptionPane.showOptionDialog(null, "Do you want to run Banner over Images?",
				"Please choose option.", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
		if (response == JOptionPane.YES_OPTION) {
			settingsVariables.getInstance().setDisplayType(1);
			runBanner();
		} else {
			settingsVariables.getInstance().setDisplayType(2);
			runImages();
		}
    }

	private static void runImages() {
		java.awt.EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
	            InVisiblePane inviP = InVisiblePane.getInstance();
	            inviP.setVisible(true);
//	            inviP.setExtendedState(JFrame.MAXIMIZED_BOTH);
	            System.out.println("SystemTray height: "+SystemTray.getSystemTray().getTrayIconSize().height);
	            Dimension d = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
	            System.out.println("Scrren Size " +d.width + " - " + d.height);
	            //this location can be set through settings**********************************
	            
	            inviP.setLocation(d.width - inviP.getWidth(), d.height - inviP.getHeight()- (3 * SystemTray.getSystemTray().getTrayIconSize().height));
	            System.out.println("message locatoion Height" + 3 * SystemTray.getSystemTray().getTrayIconSize().height);
	            
	            initTray(); 
	            
	            try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
//                    Logger.getLogger(ApplicationTest.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                //start new affirmation thread
                new ReadAffirmImages().start();
			}
		});
	}

	/**
	 * Runs banner of text
	 */
	private static void runBanner() {
		java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                vp = VisiblePane.getInstance();
                vp.setVisible(true);
//                vp.setExtendedState(JFrame.MAXIMIZED_BOTH);
                System.out.println("SystemTray height: "+SystemTray.getSystemTray().getTrayIconSize().height);
                Dimension d = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
                System.out.println("Scrren Size " +d.width + " - " + d.height);
                //this location can be set through settings**********************************
                
                vp.setLocation(d.width - vp.getWidth(), d.height - vp.getHeight()- (3 * SystemTray.getSystemTray().getTrayIconSize().height));
                System.out.println("message locatoion Height" + 3 * SystemTray.getSystemTray().getTrayIconSize().height);

                initTray();                
                
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
//                    Logger.getLogger(ApplicationTest.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                //start new affirmation thread
                new ReadAffirms().start();
            }
			
        });
	}
	
	/**
	 * Initialise the tray icon for application.
	 */
	static void initTray() {
		TrayMain tm = new TrayMain("satyen");

        MenuItem miSettings = new MenuItem("Settings");
        miSettings.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new SettingsForm().setVisible(true);
            }
        });
        tm.addMenuItem(miSettings);
//        miSettings.setShortcut(new MenuShortcut(KeyEvent.VK_X, true));

        MenuItem miSep1 = new MenuItem("-");
        tm.addMenuItem(miSep1);

        MenuItem miMove = new MenuItem("Move");
        miMove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(settingsVariables.getInstance().getDisplayType() == 2){            		
            		//TODO InVisiblePane.getInstance().setFocusable(true);
            	}else if(settingsVariables.getInstance().getDisplayType() == 1){
            		new SettingsForm().setVisible(true);
            	}
            }
        });
        tm.addMenuItem(miMove);
        
        MenuItem miOnTop = new MenuItem("Set On Top");
        if(settingsVariables.getInstance().getOnTop())
            miOnTop.setLabel("Disable On Top");
        else
            miOnTop.setLabel("Set On Top");
        
        miOnTop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                settingsVariables.getInstance().setOnTop(!settingsVariables.getInstance().getOnTop());
//                Settings.setOnTop(!settingsVariables.getInstance().getOnTop());
                
                if(settingsVariables.getInstance().getOnTop())
                    ((MenuItem)e.getSource()).setLabel("Disable On Top");
                else
                    ((MenuItem)e.getSource()).setLabel("Set On Top");
                
                //code to stop the message
            }
        });
        tm.addMenuItem(miOnTop);
        
        MenuItem miHide = new MenuItem("Stop and Hide");
        if(settingsVariables.getInstance().getOnTop())
            miHide.setLabel("Stop and Hide");
        else
            miHide.setLabel("Start and Show");
        
        miHide.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                settingsVariables.getInstance().setStartNshow(!settingsVariables.getInstance().isStartNshow());
//                Settings.setStartNshow(!Settings.isStartNshow());
                
                if(settingsVariables.getInstance().isStartNshow()) {
                    ((MenuItem)e.getSource()).setLabel("Stop and Hide");
                }
                else {
                    ((MenuItem)e.getSource()).setLabel("Start and Show");
                    VisiblePane.lblMessage.setText("");
                }
                
                //code to stop the message
            }
        });
        tm.addMenuItem(miHide);
        
        MenuItem miResize = new MenuItem("Resize");
        if(settingsVariables.getInstance().getOnTop())
            miResize.setLabel("Resize");
        else
            miResize.setLabel("Done Resize");
        
        miResize.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	InVisiblePane.getInstance().setUndecorated(false);
                settingsVariables.getInstance().setStartNshow(!settingsVariables.getInstance().isStartNshow());
//                Settings.setStartNshow(!Settings.isStartNshow());
                
                if(settingsVariables.getInstance().isStartNshow()) {
                    ((MenuItem)e.getSource()).setLabel("Resize");
                }
                else {
                    ((MenuItem)e.getSource()).setLabel("Done Resize");
                    VisiblePane.lblMessage.setText("");
                }
                
                //code to stop the message
            }
        });
        tm.addMenuItem(miResize);

        tm.addExitMenu();
	}
}
