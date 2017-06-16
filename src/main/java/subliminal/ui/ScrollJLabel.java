/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package subliminal.ui;

import javax.swing.JLabel;

/**
 * Not in use yet
 * JLabel extension for scrolling text or splashing images
 * Runs as separate Thread
 * @author Satyen S Shimpi
 */
public class ScrollJLabel extends JLabel implements Runnable {

	private static final long serialVersionUID = 3712755955524326265L;
	
	public ScrollJLabel(String text, int iTimeInterval) {
        this(text);
        this.iTimeInterval = iTimeInterval;
    }
    
    public ScrollJLabel(String text) {
        super(text);
        this.str = this.getText().toCharArray();
    }

    @Override
    public void setText(String text) {
        super.setText(text);
        this.str = this.getText().toCharArray();
    }

    
    public void run() {
    	System.out.println(Thread.currentThread().getStackTrace()[0].getClassName());
        scrollText();
    }

    /**
     * Scrolls the text in JLabel
     */
	@SuppressWarnings("unused")
	private void scrollText() {
		int i = 0;
        while (true) {
            try {
                scroll(str);
                int iLength = str.length;
                char ch = ' ';
                boolean bFirstTime = true;
                for (int j = 0; j < iLength; j++) {
                    if (j < iLength) {
                        if (j == iLength - 1) {
                            if (bFirstTime) {
                                str[j] = ch;
                                bFirstTime = false;
                            } else {
                                str[j] = str[0];
                            }
                        } else {
                            str[j] = str[j + 1];
                        }
                    }
                }

                i++;
                Thread.sleep(iTimeInterval);
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }

        }
	}

	/**
	 * Sets the jlabel text with new string so that it appears as scrolling
	 * @param str
	 */
    public void scroll(char[] str) {
        String sString = "";
        this.str = str;
        for (int i = 0; i < str.length; i++) {
            sString += str[i];
        }
//        System.out.println(sString);
        this.setText(sString);
        VisiblePane.lblMessage.repaint();
    }
    
    
    public char[] str;
    public static int iTimeInterval = 150;
}
