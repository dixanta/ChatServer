/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chat.commands;

import chat.avatar.AvatarHandler;
import chat.data.object.DataObject;
import chat.message.CommandHandler;
import chat.ui.LayoutManager;
import chat.ui.Messages;

/**
 *
 * @author Admin
 */
public class MessageCommand extends CommandHandler{
    public void execute(DataObject msg)
    {
        AvatarHandler avatarHandler = AvatarHandler.getInstance();
        if(!avatarHandler.getAvatar(msg.getUserName()).isIsBlocked())
        {
            avatarHandler.setAvatarMsg(msg.getUserName(),
                    LayoutManager.getInstance().manageText(msg.getMessage()));
            Messages.getInstance().addMessage(msg.getUserName() + " : " + msg.getMessage());
        }
    }
}
