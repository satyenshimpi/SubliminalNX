/*
 * To change this template, choose Tools | Templates
 * Created on Oct 03, 2011, 11:58:55 AM
 */
package subliminal;

import system.WindowForm;
import java.awt.AWTException;
import java.awt.Font;
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
import javax.swing.JDialog;

/**
 * The <code>TrayMain</code> class represents the Application in System tray.
 * @author Satyen S Shimpi
 * @version 0.0
 * @since version 0.0
 */
/**
 *
 * @author c0763ss
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
    

    public static void main(String[] args) {
//        printEnvVars();

//            System.out.println(TrayMain.class.getResource("/" + Main.basePackageName.replaceAll("\\.", "/") + "/resources/TrayIcon.png"));
            
            TrayMain tm = new TrayMain("satyen");
            tm.addExitMenu();
            
            MenuItem mi1 = new MenuItem("Start Service");
            tm.addMenuItem(mi1);
                        
            MenuItem miSep = new MenuItem("-");
            tm.addMenuItem(miSep);
            
            MenuItem mi3 = new MenuItem("Open App");
            mi3.setFont(new Font(null, Font.BOLD, 11));
            mi3.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    String[] arr = new String[]{"", ""};
                    try {
                        new WindowForm().setVisible(true);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });
            tm.addMenuItem(mi3);

            MenuItem mi4 = new MenuItem("About");
            mi4.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
//                    new BasicDialog().setVisible(true);
                    new JDialog().setVisible(true);
                }
            });
            
            tm.addMenuItem(mi4);

            // cant add same separator twice. to add new seperator create a new menu item
            MenuItem miSep1 = new MenuItem("-");
            tm.addMenuItem(miSep1);

    }
}
