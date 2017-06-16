/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * File : Subliminal.ReadAffirms.java
 * Created : Jan 7, 2013
 * By : Satyen S Shimpi
 */
package subliminal.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Random;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import subliminal.ui.ScrollJLabel;
import subliminal.ui.VisiblePane;

/**
 * The <code>ReadAffirms</code> class reads the affirmation text file and displays them on banner.
 * Runs as separate thread
 * @author Satyen S Shimpi
 * @version 0.0
 * @since version 0.0
 */
public class ReadAffirms extends Thread{
    public static void readFile(){
        
    }

    @Override
    public void run() {
        System.out.println("current dir by . location: " + new File(".").getAbsolutePath());
        ClassLoader cl = ReadAffirms.class.getClassLoader();
        URL u = cl.getResource(".");
        if(u!=null)
            System.out.println("Current dir from classloader: " + u.getFile());
            
        BufferedReader in = null;
        try {          
//            File fAffirm = new File("C:\\E_Home-data\\satyen\\sample code\\Java\\MiscProject\\src\\subliminal\\Affiirms.txt");         
            File fAffirm=null;
            try {
                fAffirm = new File(ReadAffirms.class.getResource("../Affiirms.txt").toURI());
            } catch (URISyntaxException ex) {
                Logger.getLogger(ReadAffirms.class.getName()).log(Level.SEVERE, null, ex);
            }
            in = new BufferedReader(new FileReader(fAffirm));
            String strLine;
            Vector<String> lines= new Vector<String>();
            while ((strLine = in.readLine()) != null)   {
                lines.add(strLine);
//                VisiblePane.lblMessage.setText(strLine);
//                if(Settings.isStartNshow())
//                    this.sleep(Settings.getDisplayTime());
//                VisiblePane.lblMessage.setText("");
//                while(!Settings.isStartNshow())
//                    this.sleep(2000);
//                if(Settings.isStartNshow())
//                    this.sleep(Settings.getDisplayEveryXXsecs() * 1000);
            }
            int i = 0;
            Random rnd = new Random(i);
            for (i = 0; i < lines.size(); i++) {                
//                System.out.println("random :"+rnd.nextInt(lines.size()*10)%(lines.size()+1));
                
                if(SettingsVariables.getInstance().getMsgOrder().equals(Constants.MSG_RANDOM))                  // for random msg
                    VisiblePane.lblMessage.setText(lines.get(rnd.nextInt(lines.size()*10)%(lines.size()+1)));
                else                                                                                            //for ordered msg
                    VisiblePane.lblMessage.setText(lines.get(i));
                
                if (SettingsVariables.getInstance().isStartNshow()) {
                    if (SettingsVariables.getInstance().getMsgDisplayType().equals(Constants.MSG_DISPLAY_TYPE_FLSHNIG)) {
                        Thread.sleep(SettingsVariables.getInstance().getDisplayTime());
                    } else {
//                        System.out.println(VisiblePane.lblMessage.getText()+": "+VisiblePane.lblMessage.getText().trim().length()* ScrollJLabel.iTimeInterval);
                    	Thread.sleep(VisiblePane.lblMessage.getText().trim().length()* ScrollJLabel.iTimeInterval);
                    }
                }
                
                VisiblePane.lblMessage.setText("");
                
                while(!SettingsVariables.getInstance().isStartNshow())
                	Thread.sleep(2000);
                
                if(SettingsVariables.getInstance().isStartNshow())
                	Thread.sleep(SettingsVariables.getInstance().getDisplayEveryXXsecs() * 1000);
                
                //start i from begining
                if(lines.size()==i+1)
                    i=-1;
            }
    //        Application.vp.repaint();
        } catch (InterruptedException ex) {
            Logger.getLogger(ReadAffirms.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ReadAffirms.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                in.close();
            } catch (IOException ex) {
                Logger.getLogger(ReadAffirms.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
}
