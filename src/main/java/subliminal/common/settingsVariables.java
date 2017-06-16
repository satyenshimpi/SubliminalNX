/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
* File : Subliminal.settingsVariables.java
* Created : Jan 16, 2013
* By : Satyen S Shimpi
*/

package subliminal.common;

import java.awt.Color;
import java.awt.Font;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * The <code>settingsVariables</code> class is there cause Settings Class has all static variables and cannot serialize.
 * @author Satyen S Shimpi
 * @version 0.0
 * @since version 0.0
 */


public class settingsVariables implements Serializable{
    
    static String strFile = "settings.dll";

    private static settingsVariables instance;
    private settingsVariables() {
        displayEveryXXsecs = 10;
        displayTime=5000;
        foreground = Color.darkGray;
        background = Color.BLACK;
        font = new Font("Tahoma", 0, 36);
        onTop = true;
        alpa = 255;
    }
        
    public static settingsVariables getInstance(){
        //deserialise if any        
        if(instance==null){
            deserializeInstanceIfExists();
            if(instance==null)
                instance = new settingsVariables();
        }
        return instance;
    }

    private static void deserializeInstanceIfExists() {
//        settingsVariables time = null;
        FileInputStream fis = null;
        ObjectInputStream in = null;

        try {
            fis = new FileInputStream(strFile);
            in = new ObjectInputStream(fis);
            instance = (settingsVariables) in.readObject();
            in.close();
        } catch(FileNotFoundException fnfe){
            instance = null;
            return;
        }
        catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        }
        System.out.println(instance.background);
        System.out.println(instance.foreground);
        System.out.println(instance.font);
        System.out.println(instance.displayEveryXXsecs);
        System.out.println(instance.displayTime);
//        return time;
    }
            
    public void serialize(){
        FileOutputStream fos = null;
        ObjectOutputStream out = null;

//        System.out.println(object.background);
        try{
            fos = new FileOutputStream(strFile);
            out = new ObjectOutputStream(fos);
            out.writeObject(instance);
            out.close();
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
        
     /**
     * Message timeout in seconds
     */
    private int displayEveryXXsecs = 5;
    
    /**
     * message display time
     */
    private int displayTime=2000;
    
    /**
     * Foreground color of the text
     */
    private Color foreground = Color.darkGray;
    
    /**
     * Background color of the text
     */
    private Color background = Color.BLACK;
    
    
    private boolean onTop = true;
    
    private boolean startNshow = true;
    
    private int alpa = 255;
    
    private String msgOrder = Constants.MSG_ORDERED;
    
    private String msgPosition = Constants.MSG_LRHS_ON_SCREEN;
    
    private int msgHeight = 44;
    private int msgWidth = 937;
    
    private String msgDisplayType = Constants.MSG_DISPLAY_TYPE_FLSHNIG;
    
    /**
     * Font for the text
     */
    private Font font = new Font("Tahoma", 0, 36);
    
    private int displayType = 2;	//1- text, 2-images 

    public int getDisplayEveryXXsecs() {
        return displayEveryXXsecs;
    }

    public void setDisplayEveryXXsecs(int timeout) {
        this.displayEveryXXsecs = timeout;
    }

    public int getDisplayTime() {
        return displayTime;
    }

    public void setDisplayTime(int displayTime) {
        this.displayTime = displayTime;
    }

    public Color getForeground() {
        return foreground;
    }

    public void setForeground(Color foreground) {
        this.foreground = foreground;
    }

    public Color getBackground() {
        return background;
    }

    public void setBackground(Color background) {
        this.background = background;
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public boolean getOnTop() {
        return onTop;
    }

    public void setOnTop(boolean onTop) {
        this.onTop = onTop;
    }

    public boolean isStartNshow() {
        return startNshow;
    }

    public void setStartNshow(boolean startNshow) {
        this.startNshow = startNshow;
    }

    public int getAlpa() {
        return alpa;
    }

    public void setAlpa(int alpa) {
        this.alpa = alpa;
    }

    public String getMsgOrder() {
        return msgOrder;
    }

    public void setMsgOrder(String msgOrder) {
        this.msgOrder = msgOrder;
    }

    public String getMsgPosition() {
        return msgPosition;
    }

    public void setMsgPosition(String msgPosition) {
        this.msgPosition = msgPosition;
    }

    public int getMsgHeight() {
        return msgHeight;
    }

    public void setMsgHeight(int msgHeight) {
        this.msgHeight = msgHeight;
    }

    public int getMsgWidth() {
        return msgWidth;
    }

    public void setMsgWidth(int msgWidth) {
        this.msgWidth = msgWidth;
    }


    public String getMsgDisplayType() {
        return msgDisplayType;
    }

    public void setMsgDisplayType(String msgDisplayType) {
        this.msgDisplayType = msgDisplayType;
    }
    
    public static void main(String[] args) {
        settingsVariables time = settingsVariables.getInstance();     
        if(time==null)
            time.serialize();
    }

	public int getDisplayType() {
		return displayType;
	}

	public void setDisplayType(int displayType) {
		this.displayType = displayType;
	}
    
}
