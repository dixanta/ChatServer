/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chat.message;

import chat.data.object.Command;
import chat.data.object.DataObject;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author Admin
 */
public class Responder implements Runnable{
    private final static Responder instance =
            new Responder();
    
    private String myName;
    private String errMsg;
    private Socket socket;
    private String host;
    private int port = 9090;
    private Thread t;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private MessageHandler msgHandler = new MessageHandler();
    
    public static Responder getInstance()
    {
        return instance;
    }
    
    public boolean login()
    {
        try {
            socket = new Socket(host, port);
            if (socket == null) {
                return false;
            }
            if (!sendMessage(Command.LOGIN, "I want to login")) {
                return false;
            }
            t = new Thread(this);
            t.start();
            msgHandler.start();
            return true;
        } catch (Exception ex) {
            errMsg = ex.getMessage();
            return false;
        }        
    }
    
    public void run() {
        try {
            while (!t.isInterrupted()) {
                in = new ObjectInputStream(socket.getInputStream());
                msgHandler.queueData(in.readObject());
            }
        } catch (ClassNotFoundException clsEx) {
            errMsg = clsEx.getMessage();
        } catch (IOException ioEx) {
            errMsg = ioEx.getMessage();
       }
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setPort(int port) {
        this.port = port;
    }
    
    public void setMyName(String myName) {
        this.myName = myName;
    }

    public Boolean sendMessage(Command cmd, String msg)
    {
        try {
            DataObject obj = new DataObject();
            obj.setCommand(cmd);
            obj.setMessage(msg);
            obj.setUserName(myName);
            out = new ObjectOutputStream(socket.getOutputStream());
            out.writeObject(obj);
            return true;
        } catch (Exception ex) {
            errMsg = ex.getMessage();
            return false;
        }
    }
    
    public Boolean sendMessage(Command cmd, String msg, String sender)
    {
        try {
            DataObject obj = new DataObject();
            obj.setCommand(cmd);
            obj.setMessage(msg);
            obj.setUserName(sender);
            out = new ObjectOutputStream(socket.getOutputStream());
            out.writeObject(obj);
            return true;
        } catch (Exception ex) {
            errMsg = ex.getMessage();
            return false;
        }
    }
}
