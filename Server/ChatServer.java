/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dixanta Bahadur Shrestha
 */

import java.io.*;
import java.net.*;
import chat.server.handler.*;
import java.util.Properties;
public class ChatServer {
public static void main(String args[])
{
    try{
        Properties configFile=new Properties();
        configFile.load(new FileInputStream("config.properties"));
        String port=configFile.getProperty("port");
        
        ServerSocket server=new ServerSocket(Integer.parseInt(port));
        System.out.println("Server is Runnint at :" + port);
        Responder responder=new Responder();
        responder.start();
        while(true)
        {
            Socket client=server.accept();
            ClientListener clientListener=new ClientListener(client,responder);
            clientListener.start();
        }
    }catch(IOException ioe)
    {
        
    }
    catch(NumberFormatException ne)
    {
        System.out.println("Port number of the server hasn't been assigned or invalid port");
    }
}
}
