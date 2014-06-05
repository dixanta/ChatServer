/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chat.commands;
import chat.data.object.DataObject;
import chat.server.handler.Responder;
import java.net.Socket;
/**
 *
 * @author Dixanta Bahadur Shrestha
 */
public abstract class ChatCommand {
    public abstract void executeCommand(Responder responder,DataObject dataObject,Socket client);
}
