/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chat.commands;

import chat.client.Client;
import chat.client.ClientHandler;
import chat.data.object.Command;
import chat.data.object.DataObject;
import chat.server.handler.Responder;
import java.net.Socket;
import java.util.Enumeration;

/**
 *
 * @author Dixanta Bahadur Shrestha
 */
public class MoveAvatarCommand extends ChatCommand {
    public void executeCommand(Responder responder,DataObject dataObject,Socket client)
    {
        //Broadcast to other users in the room
        ClientHandler clientHandler = ClientHandler.getInstance();
        clientHandler.setClientPosition(dataObject.getUserName(), dataObject.getMessage());
        Enumeration e = clientHandler.getClientsInMyRoom(dataObject.getUserName()).elements();
        while(e.hasMoreElements())
        {
            Client buddy = (Client)e.nextElement();
            DataObject obj=new DataObject(Command.MOVEAVATAR,dataObject.getMessage(),dataObject.getUserName());
            responder.writeObject(buddy.getClient(), obj);
        }
    }
}
