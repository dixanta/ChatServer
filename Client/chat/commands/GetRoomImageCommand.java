/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chat.commands;

import chat.avatar.Avatar;
import chat.avatar.AvatarHandler;
import chat.constants.ChatConstant;
import chat.data.object.Command;
import chat.data.object.DataObject;
import chat.message.CommandHandler;
import chat.message.Responder;
import chat.ui.LayoutManager;

/**
 *
 * @author Admin
 */
public class GetRoomImageCommand extends CommandHandler {
    public void execute(DataObject msg)
    {
        LayoutManager display = LayoutManager.getInstance();
        display.setRoomImage(msg.getMessage());        
        display.changeRoomImage();
        display.removeAllAvatarsExcept(msg.getUserName());
        //Check if already logged in
        if(display.isUserExist(msg.getUserName()))
        {
            Responder responder = Responder.getInstance();
            Avatar avatar = AvatarHandler.getInstance().getAvatar(msg.getUserName());
            responder.sendMessage(Command.MOVEAVATAR, avatar.getX() + ChatConstant.FIRST_SEPARATOR + avatar.getY());
        }
        display.refreshDisplay();
    }
}
