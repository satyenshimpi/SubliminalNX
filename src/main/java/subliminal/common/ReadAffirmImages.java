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
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
	private static Logger log = LogManager.getLogger(ReadAffirmImages.class.getName());
	
    public static List<String> readFile(){
		BufferedReader in = null;
		File fAffirm = null;
		List<String> lines = Collections.emptyList();
		try {
			fAffirm = new File(ReadAffirmImages.class.getResource("../AffiirmImages.txt").toURI());
			in = new BufferedReader(new FileReader(fAffirm));
			String strLine;
			lines = new LinkedList<String>();
			while ((strLine = in.readLine()) != null) {
				lines.add(strLine);
			}
		} catch (URISyntaxException ex) {
			log.error("error...", ex);
		} catch (IOException e) {
			log.error("error...", e);
		} finally {
            try {
                in.close();
            } catch (IOException ex) {
            	log.error("error...", ex);
            }
        }
		return lines;
    }

    @Override
    public void run() {
        System.out.println("current dir by . location: " + new File(".").getAbsolutePath());
        
//        ClassLoader cl = ReadAffirmImages.class.getClassLoader();
//        URL u = cl.getResource(".");
//        if(u!=null)
//            System.out.println("Current dir from classloader: " + u.getFile());
        try {
            int i = 0;
            Random rnd = new Random(i);
            List<String> lines = readFile();

            for (i = 0; i < lines.size(); i++) {
                if(SettingsVariables.getInstance().getMsgOrder().equals(Constants.MSG_RANDOM)){                  // for random msg
                    InVisiblePane.getInstance().getLblMessage().setIcon(new ImageIcon(lines.get(rnd.nextInt(lines.size()*10)%(lines.size()+1))));
                } else {                                                                                           //for ordered msg
                	ImageIcon ic = new ImageIcon(lines.get(i));
                	InVisiblePane.getInstance().getLblMessage().setIcon(ic);
                }
                
                if (SettingsVariables.getInstance().isStartNshow()) {
                    if (SettingsVariables.getInstance().getMsgDisplayType().equals(Constants.MSG_DISPLAY_TYPE_FLSHNIG)) {
                        Thread.sleep(SettingsVariables.getInstance().getDisplayTime());
                    } else {
                        Thread.sleep(InVisiblePane.getInstance().getLblMessage().getText().trim().length()* ScrollJLabel.iTimeInterval);
                    }
                }
                
                InVisiblePane.getInstance().getLblMessage().setText("");
                
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
        	log.error("error...", ex);
        }
    }
    
    
}
