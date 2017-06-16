/**
 * File : Subliminal.Settings.java
 * Created : Jan 7, 2013
 * By : Satyen S Shimpi
 */
package subliminal.common;

import java.awt.Color;
import java.awt.Font;

/**
 * The <code>Settings</code> class was for static variables. Now replaced with <code>settingsVariables</code>
 * @author Satyen S Shimpi
 * @version 0.0
 * @since version 0.0
 */
public class Settings {
    /**
     * Message timeout in seconds
     */
    private static int displayEveryXXsecs = SettingsVariables.getInstance().getDisplayEveryXXsecs();
    
    /**
     * Message timeout in seconds
     */
    private static int displayTime = SettingsVariables.getInstance().getDisplayTime();
    
    /**
     * Foreground color of the text
     */
    private static Color foreground = SettingsVariables.getInstance().getForeground();
    
    /**
     * Background color of the text
     */
    private static Color background = SettingsVariables.getInstance().getBackground();
    
    /**
     * Font for the text
     */
    private static Font font = SettingsVariables.getInstance().getFont();
    
    
    private static boolean onTop = SettingsVariables.getInstance().getOnTop();

    
    private static int alpa = SettingsVariables.getInstance().getAlpa();
    
    private static  String msgOrder = SettingsVariables.getInstance().getMsgOrder();
    
    private static  String msgPosition = SettingsVariables.getInstance().getMsgPosition();
    
    private static boolean startNshow = SettingsVariables.getInstance().isStartNshow();
    
//    private static double msgWidth = 0.0;
//    private static double msgHeight = 0.0;
    /**
     * get Message timeout in seconds
     */
    public static int getDisplayEveryXXsecs() {
        return displayEveryXXsecs;
    }

    /**
     * set Message timeout in seconds
     */
    public static void setDisplayEveryXXsecs(int timeout) {
        Settings.displayEveryXXsecs = timeout;
    }

    public static int getDisplayTime() {
        return displayTime;
    }

    public static void setDisplayTime(int displayTime) {
        Settings.displayTime = displayTime;
    }

     /**
     * Get Foreground color of the text
     */
    public static Color getForeground() {
        return foreground;
    }

     /**
     * set Foreground color of the text
     */
    public static void setForeground(Color foreground) {
        Settings.foreground = foreground;
    }

    /**
     * get Background color of the text
     */
    public static Color getBackground() {
        return background;
    }

    /**
     * set Background color of the text
     */
    public static void setBackground(Color background) {
        Settings.background = background;
    }

    /**
     * get Font for the text
     */
    public static Font getFont() {
        return font;
    }

    /**
     * set Font for the text
     */
    public static void setFont(Font font) {
        Settings.font = font;
    }        

    public static boolean getOnTop() {
        return onTop;
    }

    public static boolean isStartNshow() {
        return startNshow;
    }

    public static void setStartNshow(boolean startNshow) {
        Settings.startNshow = startNshow;
    }

    public static void setOnTop(boolean onTop) {
        Settings.onTop = onTop;
    }

    public static int getAlpa() {
        return alpa;
    }

    public static void setAlpa(int alpa) {
        Settings.alpa = alpa;
    }

    public static String getMsgOrder() {
        return msgOrder;
    }

    public static void setMsgOrder(String msgOrder) {
        Settings.msgOrder = msgOrder;
    }

    public static String getMsgPosition() {
        return msgPosition;
    }

    public static void setMsgPosition(String msgPosition) {
        Settings.msgPosition = msgPosition;
    }
    
    
}
