/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chat.commands;

import chat.avatar.AvatarType;
import chat.data.object.DataObject;
import chat.message.CommandHandler;
import chat.ui.LayoutManager;

/**
 *
 * @author Admin
 */
public class GetMemberTypeCommand extends CommandHandler {    
    public void execute(DataObject msg)
    {
        LayoutManager display = LayoutManager.getInstance();
        AvatarType type = AvatarType.values()[Integer.parseInt(msg.getMessage())];
        display.setAvatarType(msg.getUserName(), type);
        //display.refreshDisplay();
    }
}
