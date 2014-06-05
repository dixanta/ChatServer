/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.server.handler;

import chat.client.ClientHandler;
import chat.commands.ChatCommand;
import chat.commands.CommandFactory;
import chat.data.object.Command;
import chat.data.object.DataObject;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

/**
 *
 * @author Dixanta Bahadur Shrestha
 */
public class ClientListener extends Thread {

    private Socket client;
    private Responder responder;
    private ObjectInputStream objInStream;

    public ClientListener() {
    }

    public ClientListener(Socket client, Responder responder) {
        this.client = client;
        this.responder = responder;
    }

    public void run() {
        try {
            while (!isInterrupted()) {
                objInStream = new ObjectInputStream(client.getInputStream());
                DataObject data = (DataObject) objInStream.readObject();
                DataQueue dataQueue = new DataQueue(data, client);
                responder.queueMessage(dataQueue);
            }
        } catch (IOException ioe) {
            //cleaning here
            ClientHandler clientHandler = ClientHandler.getInstance();
            String name = clientHandler.getClientName(client);
            if(!name.equals(""))
            {
                ChatCommand chatCommand=(ChatCommand)CommandFactory.getCommand(Command.LOGOUT);
                chatCommand.executeCommand(responder, new DataObject(Command.LOGOUT, "Logout", name), client);
            }
        } catch (ClassNotFoundException cne) {
            
        }
    }
}
