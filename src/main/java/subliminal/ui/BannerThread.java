package subliminal.ui;

import java.awt.Dimension;
import java.awt.SystemTray;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import subliminal.TrayMain;
import subliminal.common.Constants;
import subliminal.common.ReadAffirms;

public class BannerThread implements Runnable {

	private static Logger log = LogManager.getLogger(BannerThread.class.getName());
	
	@Override
    public void run() {
		VisiblePane vp = VisiblePane.getInstance();
        vp.setVisible(true);
//        vp.setExtendedState(JFrame.MAXIMIZED_BOTH);
        log.info("SystemTray height: "+SystemTray.getSystemTray().getTrayIconSize().height);
        Dimension d = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        log.info("Scrren Size " +d.width + " - " + d.height);
        //this location can be set through settings**********************************
        
        vp.setLocation(d.width - vp.getWidth(), d.height - vp.getHeight()- (3 * SystemTray.getSystemTray().getTrayIconSize().height));
        log.info("message locatoion Height" + 3 * SystemTray.getSystemTray().getTrayIconSize().height);

        TrayMain tm = new TrayMain(Constants.APP_NAME);
        tm.initTray();                
        
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            log.error("error...", ex);
        }
        
        //start new affirmation thread
        new ReadAffirms().start();
    }

}
