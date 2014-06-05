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
public class GetMemberTypeCommand extends ChatCommand {
    public void executeCommand(Responder responder,DataObject dataObject,Socket client)
    {
        int memberType = AvatarProvider.getMemberType(
                dataObject.getMessage());
        if(memberType!=-1)
        {
            ClientHandler clientHandler = ClientHandler.getInstance();
            clientHandler.setMemberType(dataObject.getUserName(), memberType);
            
            DataObject obj=new DataObject(Command.GET_MEMBER_TYPE,Integer.toString(memberType),dataObject.getUserName());
            responder.writeObject(client, obj);
        }
        else
        {
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            ExceptionLog.logErrorMessage("Command : GET_MEMBER_TYPE\n" + 
                    AvatarProvider.getErrMsg() , 
                    sdf.format(cal.getTime()));
        }
    }
}
