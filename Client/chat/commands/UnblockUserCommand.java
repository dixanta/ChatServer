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
public class UnblockUserCommand extends CommandHandler {    
    public void execute(DataObject msg)
    {
        LayoutManager display = LayoutManager.getInstance();
        display.ignoreAvatar(msg.getMessage(), false);
        display.refreshDisplay();
    }
}
