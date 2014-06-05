/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chat.client;

import java.net.Socket;

/**
 *
 * @author Dixanta Bahadur Shrestha
 */
public class Client {
    private String name;
    private int roomID;
    private int memberType;
    private String avatarImage;
    private Socket client;
    private String position;
    
    public Client(String name)
    {
        this.name = name;
    }
    
    public String getAvatarImage() {
        return avatarImage;
    }

    public void setAvatarImage(String avatarImage) {
        this.avatarImage = avatarImage;
    }

    public int getMemberType() {
        return memberType;
    }

    public void setMemberType(int memberType) {
        this.memberType = memberType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public Socket getClient() {
        return client;
    }

    public void setClient(Socket client) {
        this.client = client;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
    
}
