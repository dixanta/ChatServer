/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chat.commands;

import chat.client.Client;
import chat.client.ClientHandler;
import chat.constants.ChatConstant;
import chat.data.object.Command;
import chat.data.object.DataObject;
import chat.server.handler.Responder;
import java.net.Socket;
import java.util.Enumeration;

/**
 *
 * @author Dixanta Bahadur Shrestha
 */
public class GetRoomUsersCommand extends ChatCommand {
    public void executeCommand(Responder responder,DataObject dataObject,Socket client)
    {
        ClientHandler clientHandler = ClientHandler.getInstance();
        Enumeration e = clientHandler.getClients(Integer.parseInt(dataObject.getMessage())).elements();
        String msg="";
        while(e.hasMoreElements())
        {
            Client obj = (Client)e.nextElement();
            if(!obj.getName().equals(dataObject.getUserName()))
                if(msg.equals(""))
                    msg = obj.getName() + ChatConstant.FIRST_SEPARATOR + 
                            obj.getMemberType() + ChatConstant.FIRST_SEPARATOR +
                            obj.getAvatarImage() + ChatConstant.FIRST_SEPARATOR +
                            obj.getPosition();
                else
                    msg = msg + ChatConstant.SECOND_SEPARATOR + 
                            obj.getName() + ChatConstant.FIRST_SEPARATOR + 
                            obj.getMemberType() + ChatConstant.FIRST_SEPARATOR +
                            obj.getAvatarImage() + ChatConstant.FIRST_SEPARATOR +
                            obj.getPosition();
                        
        }
        DataObject obj=new DataObject(Command.GET_ROOM_USERS,msg,dataObject.getUserName());
        responder.writeObject(client, obj);
        
        //We also broadcast new user here
        Client clientObj = clientHandler.getClientObject(dataObject.getUserName());
        Enumeration en = clientHandler.getClientsInMyRoom(dataObject.getUserName()).elements();
        while(en.hasMoreElements())
        {
            Client buddy = (Client)en.nextElement();
            DataObject objNewUser=new DataObject(Command.NEW_ROOM_USER, 
                    clientObj.getName() + ChatConstant.FIRST_SEPARATOR + 
                    clientObj.getMemberType() + ChatConstant.FIRST_SEPARATOR +
                    clientObj.getAvatarImage(),dataObject.getUserName());
            responder.writeObject(buddy.getClient(), objNewUser);
        }
    }
}
