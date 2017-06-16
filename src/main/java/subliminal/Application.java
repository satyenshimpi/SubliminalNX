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
import java.awt.SystemTray;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import subliminal.common.Constants;
import subliminal.common.ReadAffirmImages;
import subliminal.common.ReadAffirms;
import subliminal.common.SettingsVariables;
import subliminal.ui.InVisiblePane;
import subliminal.ui.SettingsForm;
import subliminal.ui.VisiblePane;

/**
 * The <code>Application</code> class represents
 * @author Satyen S Shimpi
 * @version 0.0
 * @since version 0.0
 */
public class Application {
	private static VisiblePane vp = null;
    private static Logger log = LogManager.getLogger(Application.class.getName());
    
    public static void main(String[] args) {
		int response = JOptionPane.showOptionDialog(null, "Do you want to run Banner over Images?",
				"Please choose option.", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
		
		if (response == JOptionPane.YES_OPTION) {
			SettingsVariables.getInstance().setDisplayType(1);
			runBanner();
		} else {
			SettingsVariables.getInstance().setDisplayType(2);
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
	            log.info("SystemTray height: "+SystemTray.getSystemTray().getTrayIconSize().height);
	            Dimension d = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
	            log.info("Scrren Size " +d.width + " - " + d.height);
	            //this location can be set through settings**********************************
	            
//	            inviP.setLocation(d.width - inviP.getWidth(), d.height - inviP.getHeight()- (3 * SystemTray.getSystemTray().getTrayIconSize().height));
	            log.info("message locatoion Height" + 3 * SystemTray.getSystemTray().getTrayIconSize().height);
	            
	            initTray(); 
	            
	            try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    log.error("Can not...", ex);
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
	private static void initTray() {
		TrayMain tm = new TrayMain(Constants.APP_NAME);

        MenuItem miSettings = new MenuItem(Constants.MENU_SETTINGS);
        miSettings.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new SettingsForm().setVisible(true);
            }
        });
        tm.addMenuItem(miSettings);
//        miSettings.setShortcut(new MenuShortcut(KeyEvent.VK_X, true));

        MenuItem miSep1 = new MenuItem(Constants.MENU_SEPARATOR);
        tm.addMenuItem(miSep1);

        MenuItem miMove = new MenuItem(Constants.MENU_MOVE);
        miMove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(SettingsVariables.getInstance().getDisplayType() == 2){            		
            		//TODO InVisiblePane.getInstance().setFocusable(true);
            	}else if(SettingsVariables.getInstance().getDisplayType() == 1){
            		new SettingsForm().setVisible(true);
            	}
            }
        });
        tm.addMenuItem(miMove);
        
        MenuItem miOnTop = new MenuItem(Constants.MENU_SET_ON_TOP);
        if(SettingsVariables.getInstance().getOnTop())
            miOnTop.setLabel(Constants.MENU_DISABLE_ON_TOP);
        else
            miOnTop.setLabel(Constants.MENU_SET_ON_TOP);
        
        miOnTop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SettingsVariables.getInstance().setOnTop(!SettingsVariables.getInstance().getOnTop());
//                Settings.setOnTop(!settingsVariables.getInstance().getOnTop());
                
                if(SettingsVariables.getInstance().getOnTop())
                    ((MenuItem)e.getSource()).setLabel(Constants.MENU_DISABLE_ON_TOP);
                else
                    ((MenuItem)e.getSource()).setLabel(Constants.MENU_SET_ON_TOP);
                
                //code to stop the message
            }
        });
        tm.addMenuItem(miOnTop);
        
        MenuItem miHide = new MenuItem(Constants.MENU_STOP_AND_HIDE);
        if(SettingsVariables.getInstance().getOnTop())
            miHide.setLabel(Constants.MENU_STOP_AND_HIDE);
        else
            miHide.setLabel(Constants.MENU_START_AND_SHOW);
        
        miHide.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SettingsVariables.getInstance().setStartNshow(!SettingsVariables.getInstance().isStartNshow());
//                Settings.setStartNshow(!Settings.isStartNshow());
                
                if(SettingsVariables.getInstance().isStartNshow()) {
                    ((MenuItem)e.getSource()).setLabel(Constants.MENU_STOP_AND_HIDE);
                }
                else {
                    ((MenuItem)e.getSource()).setLabel(Constants.MENU_START_AND_SHOW);
                    VisiblePane.lblMessage.setText(Constants.MENU_EMPTY);
                }
                
                //code to stop the message
            }
        });
        tm.addMenuItem(miHide);
        
        MenuItem miResize = new MenuItem(Constants.MENU_RESIZE);
        if(SettingsVariables.getInstance().getOnTop())
            miResize.setLabel(Constants.MENU_RESIZE);
        else
            miResize.setLabel(Constants.MENU_DONE_RESIZE);
        
        miResize.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	InVisiblePane.getInstance().setUndecorated(false);
                SettingsVariables.getInstance().setStartNshow(!SettingsVariables.getInstance().isStartNshow());
//                Settings.setStartNshow(!Settings.isStartNshow());
                
                if(SettingsVariables.getInstance().isStartNshow()) {
                    ((MenuItem)e.getSource()).setLabel(Constants.MENU_RESIZE);
                }
                else {
                    ((MenuItem)e.getSource()).setLabel(Constants.MENU_DONE_RESIZE);
                    VisiblePane.lblMessage.setText(Constants.MENU_EMPTY);
                }
                
                //code to stop the message
            }
        });
        tm.addMenuItem(miResize);

        tm.addExitMenu();
	}
}
