/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chat.util;

import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class MessageBox {
    public void displayMessage(String msg)
    {
        JOptionPane.showConfirmDialog(null, msg,"Chat Client", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public int displayMessage(String msg, int option)
    {
        return JOptionPane.showConfirmDialog(null, msg, "Chat Client",option);
    }
}
