/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chat.commands;

import chat.data.object.DataObject;
import chat.message.CommandHandler;
import chat.ui.LayoutManager;

/**
 *
 * @author Admin
 */
public class LogOutCommand extends CommandHandler {    
    public void execute(DataObject msg)
    {
        LayoutManager display = new LayoutManager();
        display.removeAvatar(msg.getUserName());        
        display.refreshDisplay();
        try
        {
            Thread.sleep(200);
        }
        catch(InterruptedException IEx){}
        display.refreshDisplay();
    }
}
