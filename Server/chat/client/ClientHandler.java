/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chat.client;
import java.net.Socket;
import java.util.Enumeration;
import java.util.Hashtable;

/**
 *
 * @author Dixanta Bahadur Shrestha
 */
public class ClientHandler {
    private final static ClientHandler instance =
            new ClientHandler();
    
    private Hashtable clients = new Hashtable();
    
    public static ClientHandler getInstance()
    {
        return instance;
    }
    
    public void addClient(String name)
    {
        clients.put(name, new Client(name));
    }
    
    public void removeClient(Socket client)
    {
        String name="";
        Enumeration e = clients.elements();
        while(e.hasMoreElements())
        {
            Client obj = (Client)e.nextElement();
            if(obj.getClient()==client)
                name=obj.getName();
        }
        if(!name.equals(""))
            clients.remove(name);
    }
    
    public void setAvatarImage(String name, String avatarImage) {
        ((Client)clients.get(name)).setAvatarImage(avatarImage);
    }
    
    public void setMemberType(String name, int memberType) {
        ((Client)clients.get(name)).setMemberType(memberType);
    }
    
    public void setRoomID(String name, int roomID) {
        ((Client)clients.get(name)).setRoomID(roomID);
    }
    
    public int getRoomID(String name)
    {
        return ((Client)clients.get(name)).getRoomID();
    }
    
    public void setClientPosition(String name, String pos)
    {
        ((Client)clients.get(name)).setPosition(pos);
    }
    
    public String getClientPosition(String name)
    {
        return ((Client)clients.get(name)).getPosition();
    }
    
    public Socket getClient(String name) {
        return ((Client)clients.get(name)).getClient();
    }

    public void setClient(String name, Socket client) {
        ((Client)clients.get(name)).setClient(client);
    }
    
    public Hashtable getClients(int roomID)
    {
        Hashtable hashTable = new Hashtable();
        Enumeration e = clients.elements();
        while(e.hasMoreElements())
        {
            Client obj = (Client)e.nextElement();
            if(obj.getRoomID()==roomID)
                hashTable.put(obj.getName(), obj);
        }
        return hashTable;
    }
    
    public Hashtable getClientsInMyRoom(String name)
    {
        Client me = (Client)clients.get(name);
        
        Hashtable hashTable = new Hashtable();
        Enumeration e = clients.elements();
        while(e.hasMoreElements())
        {
            Client obj = (Client)e.nextElement();
            if(obj.getRoomID()==me.getRoomID() && !obj.equals(me))
                hashTable.put(obj.getName(), obj);
        }
        return hashTable;
    }
    
    public Client getClientObject(String name)
    {
        return (Client)clients.get(name);
    }
    
    public String getClientName(Socket client)
    {
        String name="";
        Enumeration e = clients.elements();
        while(e.hasMoreElements())
        {
            Client obj = (Client)e.nextElement();
            if(obj.getClient().equals(client))
            {
                name = obj.getName();
                break;
            }
        }
        return name;
    }
}
