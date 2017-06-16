/*
 * To change this template, choose Tools | Templates
 * Created on Oct 03, 2011, 11:58:55 AM
 */
package subliminal;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

import subliminal.common.Constants;
import subliminal.common.SettingsVariables;
import subliminal.ui.InVisiblePane;
import subliminal.ui.SettingsForm;
import subliminal.ui.VisiblePane;

/**
 * The <code>TrayMain</code> class represents the Application in System tray.
 * @author Satyen S Shimpi
 * @version 0.0
 * @since version 0.0
 */
public class TrayMain {

    public static SystemTray sysTray = SystemTray.getSystemTray();
    public static PopupMenu popMnu;
    public static TrayIcon trayIco;

    public TrayMain(String appName) {
        Image image = Toolkit.getDefaultToolkit().
                getImage(TrayMain.class.getResource("./TrayIcon.png"));
        popMnu = new PopupMenu();

        trayIco = new TrayIcon(image, appName, popMnu);
        try {
            sysTray.add(trayIco);
        } catch (AWTException ex) {
            Logger.getLogger(TrayMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void addExitMenu() {
        MenuItem miExit = new MenuItem("Exit");
        miExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        popMnu.add(miExit);
    }
    
    public void addMenuItem(MenuItem miItem) {
        popMnu.add(miItem);
    }
    
	/**
	 * Initialise the tray icon for application.
	 */
	public void initTray() {
//		TrayMain tm = new TrayMain(Constants.APP_NAME);

        MenuItem miSettings = new MenuItem(Constants.MENU_SETTINGS);
        miSettings.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new SettingsForm().setVisible(true);
            }
        });
        this.addMenuItem(miSettings);
//        miSettings.setShortcut(new MenuShortcut(KeyEvent.VK_X, true));

        MenuItem miSep1 = new MenuItem(Constants.MENU_SEPARATOR);
        this.addMenuItem(miSep1);

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
        this.addMenuItem(miMove);
        
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
        this.addMenuItem(miOnTop);
        
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
                    VisiblePane.getInstance().getLblMessage().setText(Constants.MENU_EMPTY);
                }
                
                //code to stop the message
            }
        });
        this.addMenuItem(miHide);
        
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
                    VisiblePane.getInstance().getLblMessage().setText(Constants.MENU_EMPTY);
                }
                
                //code to stop the message
            }
        });
        this.addMenuItem(miResize);

        this.addExitMenu();
	}

//    public static void main(String[] args) {
////        printEnvVars();
//
////            System.out.println(TrayMain.class.getResource("/" + Main.basePackageName.replaceAll("\\.", "/") + "/resources/TrayIcon.png"));
//            
//            TrayMain tm = new TrayMain("satyen");
//            tm.addExitMenu();
//            
//            MenuItem mi1 = new MenuItem("Start Service");
//            tm.addMenuItem(mi1);
//                        
//            MenuItem miSep = new MenuItem("-");
//            tm.addMenuItem(miSep);
//            
//            MenuItem mi3 = new MenuItem("Open App");
//            mi3.setFont(new Font(null, Font.BOLD, 11));
//            mi3.addActionListener(new ActionListener() {
//
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    try {
//                        new WindowForm().setVisible(true);
//                    } catch (Exception ex) {
//                        ex.printStackTrace();
//                    }
//                }
//            });
//            tm.addMenuItem(mi3);
//
//            MenuItem mi4 = new MenuItem("About");
//            mi4.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
////                    new BasicDialog().setVisible(true);
//                    new JDialog().setVisible(true);
//                }
//            });
//            
//            tm.addMenuItem(mi4);
//
//            // cant add same separator twice. to add new seperator create a new menu item
//            MenuItem miSep1 = new MenuItem("-");
//            tm.addMenuItem(miSep1);
//
//    }
}
