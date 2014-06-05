/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chat.userData;

import javax.swing.JApplet;

/**
 *
 * @author Admin
 */
public class UserData {
    private String avatarName;
    private int avatarID;
    private int roomID;
    private JApplet userApplet;
    private int namePlateSize;
    private boolean isKickedOut = false;
    
    private static final UserData instance = 
            new UserData();
    
    public static UserData getInstance()
    {
        return instance;
    }
    
    public int getAvatarID() {
        return avatarID;
    }

    public void setAvatarID(int avatarID) {
        this.avatarID = avatarID;
    }

    public String getAvatarName() {
        return avatarName;
    }

    public void setAvatarName(String avatarName) {
        this.avatarName = avatarName;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public JApplet getUserApplet() {
        return userApplet;
    }

    public void setUserApplet(JApplet userApplet) {
        this.userApplet = userApplet;
    }

    public int getNamePlateSize() {
        return namePlateSize;
    }

    public void setNamePlateSize(int namePlateSize) {
        this.namePlateSize = namePlateSize;
    }

    public boolean getIsKickedOut() {
        return isKickedOut;
    }

    public void setIsKickedOut(boolean isKickedOut) {
        this.isKickedOut = isKickedOut;
    }
    
}
