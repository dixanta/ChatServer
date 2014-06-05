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
import java.util.StringTokenizer;

/**
 *
 * @author Dixanta Bahadur Shrestha
 */
public class PrivateMessageCommand extends ChatCommand {
    public void executeCommand(Responder responder,DataObject dataObject,Socket client)
    {
        StringTokenizer tokenizer = new StringTokenizer(dataObject.getMessage(), ChatConstant.FIRST_SEPARATOR);
        String messageTo = tokenizer.nextToken().toString();
        String message = tokenizer.nextToken().toString();

        Client obj = ClientHandler.getInstance().getClientObject(messageTo);
        
        responder.writeObject(obj.getClient(), new DataObject(Command.PRIVATE_MESSAGE, message, dataObject.getUserName()));
    }
}
