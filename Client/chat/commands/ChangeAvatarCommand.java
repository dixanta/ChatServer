/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chat.commands;

import chat.data.object.Command;
import chat.data.object.DataObject;
import chat.message.CommandHandler;
import chat.message.Responder;

/**
 *
 * @author Anil
 */
public class ChangeAvatarCommand extends CommandHandler{
    public void execute(DataObject msg)
    {
        Responder.getInstance().sendMessage(Command.GET_AVATAR_IMAGE, msg.getMessage(), msg.getUserName());
    }
}
