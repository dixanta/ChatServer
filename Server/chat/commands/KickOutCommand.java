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
import java.io.IOException;
import java.net.Socket;
import java.util.Enumeration;

/**
 *
 * @author Dixanta Bahadur Shrestha
 */
public class KickOutCommand extends ChatCommand{
    public void executeCommand(Responder responder,DataObject dataObject,Socket client)
    {
        Socket clientSocket=null;
        ClientHandler clientHandler = ClientHandler.getInstance();
        Enumeration e = clientHandler.getClients(clientHandler.getRoomID(dataObject.getUserName())).elements();
        DataObject obj = new DataObject(Command.LOGOUT, "Logout", dataObject.getMessage());        
        while(e.hasMoreElements())
        {
            Client buddy = (Client)e.nextElement();
            if(buddy.getName().equals(dataObject.getMessage()))
            {
                responder.writeObject(buddy.getClient(), dataObject);
                clientSocket= buddy.getClient();
            }
            else
                responder.writeObject(buddy.getClient(), obj);
        }
        clientHandler.removeClient(clientSocket);
        try
        {
            clientSocket.close();
        }
        catch(IOException ex)
        {}
    }
}
