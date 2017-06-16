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
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Random;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;

import subliminal.ui.InVisiblePane;
import subliminal.ui.ScrollJLabel;

/**
 * The <code>ReadAffirmImages</code> class reads the affirmation images from text file and displays them.
 * Runs as separate thread
 * @author Satyen S Shimpi
 * @version 0.0
 * @since version 0.0
 */
public class ReadAffirmImages extends Thread{
    public static void readFile(){
        
    }

    @Override
    public void run() {
        System.out.println("current dir by . location: " + new File(".").getAbsolutePath());
        ClassLoader cl = ReadAffirmImages.class.getClassLoader();
        URL u = cl.getResource(".");
        if(u!=null)
            System.out.println("Current dir from classloader: " + u.getFile());
            
        BufferedReader in = null;
        try {
            File fAffirm=null;
            try {
                fAffirm = new File(ReadAffirmImages.class.getResource("../AffiirmImages.txt").toURI());
            } catch (URISyntaxException ex) {
                Logger.getLogger(ReadAffirmImages.class.getName()).log(Level.SEVERE, null, ex);
            }
            in = new BufferedReader(new FileReader(fAffirm));
            String strLine;
            Vector<String> lines= new Vector<String>();
            while ((strLine = in.readLine()) != null)   {
                lines.add(strLine);
            }
            int i = 0;
            Random rnd = new Random(i);

            for (i = 0; i < lines.size(); i++) {
                if(settingsVariables.getInstance().getMsgOrder().equals(Constants.MSG_RANDOM)){                  // for random msg
                    InVisiblePane.lblMessage.setIcon(new ImageIcon(lines.get(rnd.nextInt(lines.size()*10)%(lines.size()+1))));
                } else {                                                                                           //for ordered msg
                	ImageIcon ic = new ImageIcon(lines.get(i));
                	System.out.println(ic.getDescription());
                	InVisiblePane.lblMessage.setIcon(ic);
                	System.out.println("coming after");
                }
                
                if (settingsVariables.getInstance().isStartNshow()) {
                    if (settingsVariables.getInstance().getMsgDisplayType().equals(Constants.MSG_DISPLAY_TYPE_FLSHNIG)) {
                        this.sleep(settingsVariables.getInstance().getDisplayTime());
                    } else {
                        this.sleep(InVisiblePane.lblMessage.getText().trim().length()* ScrollJLabel.iTimeInterval);
                    }
                }
                
                InVisiblePane.lblMessage.setText("");
                
                while(!settingsVariables.getInstance().isStartNshow())
                    this.sleep(2000);
                
                if(settingsVariables.getInstance().isStartNshow())
                    this.sleep(settingsVariables.getInstance().getDisplayEveryXXsecs() * 1000);
                
                //start i from begining
                if(lines.size()==i+1)
                    i=-1;
            }
    //        Application.vp.repaint();
        } catch (InterruptedException ex) {
            Logger.getLogger(ReadAffirmImages.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ReadAffirmImages.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                in.close();
            } catch (IOException ex) {
                Logger.getLogger(ReadAffirmImages.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
}
