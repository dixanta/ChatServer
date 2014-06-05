/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chat.commands;

import chat.client.Client;
import chat.client.ClientHandler;
import chat.constants.ChatConstant;
import chat.data.object.DataObject;
import chat.server.handler.Responder;
import java.net.Socket;
import chat.data.object.Command;
import db.provider.RoomProvider;
import db.util.ExceptionLog;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Enumeration;
/**
 *
 * @author Dixanta Bahadur Shrestha
 */
public class GetRoomImageCommand extends ChatCommand {
    public void executeCommand(Responder responder,DataObject dataObject,Socket client)
    {
        String roomImage = RoomProvider.getRoomImage(
                Integer.parseInt(dataObject.getMessage()));
        if(roomImage!=null)
        {
            ClientHandler clientHandler = ClientHandler.getInstance();

            //Notify other users in the apartment about the change of room
            Enumeration e = clientHandler.getClientsInMyRoom(dataObject.getUserName()).elements();
            while(e.hasMoreElements())
            {
                Client buddy = (Client)e.nextElement();
                DataObject objNewUser=new DataObject(Command.LOGOUT,
                        "Logout",dataObject.getUserName());
                responder.writeObject(buddy.getClient(), objNewUser);
            }

            //Change client's room
            clientHandler.setRoomID(dataObject.getUserName(), 
                    Integer.parseInt(dataObject.getMessage()));
            
            DataObject roomImageData=new DataObject(Command.GET_ROOM_IMAGE,roomImage,dataObject.getUserName());
            responder.writeObject(client, roomImageData);            
        }
        else
        {
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            ExceptionLog.logErrorMessage("Command : GET_ROOM_IMAGE\n" + 
                    RoomProvider.getErrMsg() , 
                    sdf.format(cal.getTime()));
        }
    }
}
