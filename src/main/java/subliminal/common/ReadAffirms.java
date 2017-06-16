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
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
	private static Logger log = LogManager.getLogger(ReadAffirms.class.getName());
	
    private List<String> readFile(){
//      System.out.println("current dir by . location: " + new File(".").getAbsolutePath());
//      ClassLoader cl = ReadAffirms.class.getClassLoader();
//      URL u = cl.getResource(".");
//      if(u!=null)
//          System.out.println("Current dir from classloader: " + u.getFile());
    	
    	List<String> lines = Collections.emptyList();
    	BufferedReader in = null;
        File fAffirm=null;
        try {
            fAffirm = new File(ReadAffirms.class.getResource("../Affiirms.txt").toURI());
			in = new BufferedReader(new FileReader(fAffirm));
	        String strLine;
	        lines= new LinkedList<String>();
	        while ((strLine = in.readLine()) != null)   {
	            lines.add(strLine);
	        }
        } catch (URISyntaxException ex) {
        	log.error("error...", ex);
        } catch (FileNotFoundException e) {
        	log.error("error...", e);
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
        try {          
        	List<String> lines = readFile();
            int i = 0;
            Random rnd = new Random(i);
            for (i = 0; i < lines.size(); i++) {                
//                System.out.println("random :"+rnd.nextInt(lines.size()*10)%(lines.size()+1));
                
                if(SettingsVariables.getInstance().getMsgOrder().equals(Constants.MSG_RANDOM))                  // for random msg
                    VisiblePane.getInstance().getLblMessage().setText(lines.get(rnd.nextInt(lines.size()*10)%(lines.size()+1)));
                else                                                                                            //for ordered msg
                    VisiblePane.getInstance().getLblMessage().setText(lines.get(i));
                
                if (SettingsVariables.getInstance().isStartNshow()) {
                    if (SettingsVariables.getInstance().getMsgDisplayType().equals(Constants.MSG_DISPLAY_TYPE_FLSHNIG)) {
                        Thread.sleep(SettingsVariables.getInstance().getDisplayTime());
                    } else {
//                        System.out.println(VisiblePane.lblMessage.getText()+": "+VisiblePane.lblMessage.getText().trim().length()* ScrollJLabel.iTimeInterval);
                    	Thread.sleep(VisiblePane.getInstance().getLblMessage().getText().trim().length()* ScrollJLabel.iTimeInterval);
                    }
                }
                
                VisiblePane.getInstance().getLblMessage().setText(Constants.MENU_EMPTY);
                
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
