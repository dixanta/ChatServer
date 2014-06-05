/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chat.commands;

import chat.client.ClientHandler;
import chat.data.object.Command;
import chat.data.object.DataObject;
import chat.server.handler.Responder;
import db.provider.AvatarProvider;
import db.util.ExceptionLog;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author Dixanta Bahadur Shrestha
 */
public class GetAvatarImageCommand extends ChatCommand {
    public void executeCommand(Responder responder,DataObject dataObject,Socket client)
    {
        String avatarImage = AvatarProvider.getAvatarImage(
                Integer.parseInt(dataObject.getMessage()));
        if(avatarImage!=null)
        {
            ClientHandler clientHandler = ClientHandler.getInstance();
            clientHandler.setAvatarImage(dataObject.getUserName(), avatarImage);
            DataObject obj=new DataObject(Command.GET_AVATAR_IMAGE,avatarImage,dataObject.getUserName());
            responder.writeObject(client, obj);
        }
        else
        {
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            ExceptionLog.logErrorMessage("Command : GET_AVATAR_IMAGE\n" + 
                    AvatarProvider.getErrMsg() , 
                    sdf.format(cal.getTime()));
        }
    }
}
