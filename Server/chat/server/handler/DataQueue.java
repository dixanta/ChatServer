/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chat.server.handler;

import chat.data.object.*;
import java.net.*;

/**
 *
 * @author Dixanta Bahadur Shrestha
 */
 
 
public class DataQueue {

    private DataObject dataObject;
    private Socket socket;

    public DataQueue(DataObject dataObject, Socket socket) {
        this.dataObject = dataObject;
        this.socket = socket;
    }

    public DataQueue() {
    }

    public DataObject getDataObject() {
        return dataObject;
    }

    public void setDataObject(DataObject dataObject) {
        this.dataObject = dataObject;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
    
}
