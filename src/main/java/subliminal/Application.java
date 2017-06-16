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

import javax.swing.JOptionPane;

import subliminal.common.SettingsVariables;
import subliminal.ui.BannerThread;
import subliminal.ui.ImagesThread;

/**
 * The <code>Application</code> class represents
 * @author Satyen S Shimpi
 * @version 0.0
 * @since version 0.0
 */
public class Application {
	
//    private static Logger log = LogManager.getLogger(Application.class.getName());
    
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
		java.awt.EventQueue.invokeLater(new ImagesThread());
	}

	/**
	 * Runs banner of text
	 */
	private static void runBanner() {
		java.awt.EventQueue.invokeLater(new BannerThread());
	}
	

}
