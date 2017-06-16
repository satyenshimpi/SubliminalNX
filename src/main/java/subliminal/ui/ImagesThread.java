package subliminal.ui;

import java.awt.Dimension;
import java.awt.SystemTray;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import subliminal.TrayMain;
import subliminal.common.Constants;
import subliminal.common.ReadAffirmImages;

public class ImagesThread implements Runnable {
	private static Logger log = LogManager.getLogger(ImagesThread.class.getName());

	@Override
	public void run() {
        InVisiblePane inviP = InVisiblePane.getInstance();
        inviP.setVisible(true);
//        inviP.setExtendedState(JFrame.MAXIMIZED_BOTH);
        log.info("SystemTray height: "+SystemTray.getSystemTray().getTrayIconSize().height);
        Dimension d = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        log.info("Scrren Size " +d.width + " - " + d.height);
        //this location can be set through settings**********************************
        
//        inviP.setLocation(d.width - inviP.getWidth(), d.height - inviP.getHeight()- (3 * SystemTray.getSystemTray().getTrayIconSize().height));
        log.info("message locatoion Height" + 3 * SystemTray.getSystemTray().getTrayIconSize().height);
        
        TrayMain tm = new TrayMain(Constants.APP_NAME);
        tm.initTray(); 
        
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            log.error("Can not...", ex);
        }
        
        //start new affirmation thread
        new ReadAffirmImages().start();
	}

}
