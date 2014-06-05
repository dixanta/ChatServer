/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chat.ui;

import java.util.Vector;

/**
 *
 * @author Admin
 */
public class Messages {
    private Vector message = new Vector();
    private static final Messages instance = 
            new Messages();
    
    public static Messages getInstance()
    {
        return instance;
    }
    
    public void addMessage(String msg)
    {
        message.add(msg);
    }
    
    public String getMessage(int index)
    {
        return message.get(index).toString();
    }
    
    public int count()
    {
        return message.size();
    }
}
