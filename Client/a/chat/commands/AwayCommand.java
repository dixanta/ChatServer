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
 * @author Anil
 */
public class AwayCommand extends CommandHandler {
    public void execute(DataObject msg)
    {
        LayoutManager display = LayoutManager.getInstance();
        if(Integer.parseInt(msg.getMessage())==1)
            display.awayAvatar(msg.getUserName(), true);
        else
            display.awayAvatar(msg.getUserName(), false);
        display.refreshDisplay();
    }
}
