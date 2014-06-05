/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chat.server.handler;

import chat.commands.ChatCommand;
import chat.commands.CommandFactory;
import chat.data.object.DataObject;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Vector;

/**
 *
 * @author Dixanta Bahadur Shrestha
 */
 
 public class Responder extends Thread {
   private Vector requestQueue = new Vector();

    public synchronized void writeObject(Socket client, DataObject data) {
        try {
            ObjectOutputStream objOutStream = new ObjectOutputStream(client.getOutputStream());
            objOutStream.writeObject(data);
            objOutStream.flush();
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }

    }

    public synchronized void queueMessage(DataQueue dataQueue) {
        requestQueue.add(dataQueue);
        notify();
    }

    public synchronized DataQueue getMessegeFromQueue()
            throws InterruptedException {
        while (requestQueue.size() == 0) {
            wait();
        }
        DataQueue dataQueue = (DataQueue) requestQueue.get(0);
        requestQueue.remove(dataQueue);
        return dataQueue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                DataQueue dataQueue = getMessegeFromQueue();
                //String msg = "";
                DataObject data = dataQueue.getDataObject();
                //System.out.println(data.getCommand() + " : " + data.getMessage());
                if(CommandFactory.isCommand(data.getCommand()))
                {
                    ChatCommand chatCommand=(ChatCommand)CommandFactory.getCommand(data.getCommand());
                    chatCommand.executeCommand(this, data, dataQueue.getSocket());
                }                
            }
        } catch (InterruptedException ine) {
      
        }
    }
}