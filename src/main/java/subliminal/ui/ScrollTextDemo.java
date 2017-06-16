/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * File : Subliminal.ScrollTextDemo.java
 * Created : Jan 7, 2013
 * By : Satyen S Shimpi
 */
package subliminal.ui;

import java.awt.*;
import javax.swing.*;

/**
 * The <code>ScrollTextDemo</code> class represents
 * @author Satyen S Shimpi
 * @version 0.0
 * @since version 0.0
 */
public class ScrollTextDemo extends JPanel implements Runnable {

	private static final long serialVersionUID = 2487844533945171010L;
	
	public ScrollTextDemo() {
        this.setLayout(new BorderLayout());
        this.add(lbl, BorderLayout.CENTER);
        Thread t = new Thread(this);
        t.start();
    }

    public void run() {
        int i = 0;
        while (true) {
            try {

                scroll(str);
                int iLength = str.length;
                char ch;
                if (false) {
                    //if you want to make the same text scroll again then assign as below
                    //it will reassign the first character at the end of string, since it will get replaced by 2nd char
//                char ch = str[0];  
                } else {
                    //below line is to make the same text scroll once only
                    ch = 0;  // defualt char  u\0000
                }
                if(str[0]==ch)
                    System.out.println("this is last");
                
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
            } catch (InterruptedException ex) {
                System.err.println(ex.getMessage());
            }

        }
    }

    public void scroll(char[] str) {
        String sString = new String(str);
//        this.str = str;
//        for (int i = 0; i < str.length; i++) {
//            sString += str[i];
//        }
        System.out.println(sString);
        lbl.setText(sString);
    }

    public static void main(String[] args) {
        JFrame frm = new JFrame("Scroll");
        ScrollTextDemo st = new ScrollTextDemo();
        String sFullText = "";
        if (args != null && args.length > 0) {
            st.iTimeInterval = Integer.parseInt(args[0]);
            for (int k = 1; k < args.length; k++) {
                sFullText += args[k];
                sFullText += " ";
            }
            st.str = sFullText.toCharArray();
        } else {
            System.out.println("Please Specify the timeInteval(first) and text in argument");
        }

        frm.getContentPane().add(st, BorderLayout.CENTER);
        frm.setSize(200, 200);
        frm.setVisible(true);
    }
    String sDefaultText = "Specified Text";
    public char[] str = sDefaultText.toCharArray();
    JLabel lbl = new JLabel();
    private int iTimeInterval = 500;
}
