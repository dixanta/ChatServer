/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chat.ui;

import chat.avatar.AvatarHandler;
import chat.avatar.AvatarType;
import chat.constants.ChatConstant;
import chat.data.object.Command;
import chat.message.Responder;
import chat.userData.UserData;
import chat.util.MessageBox;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

/**
 *
 * @author Admin
 */
public class LayoutManager {
    private static final LayoutManager instance = 
            new LayoutManager();
    
    private String roomImage = "";
    private RoomImage image = new RoomImage("");
    private AvatarHandler avatarHandler = 
            AvatarHandler.getInstance();
    private Responder msgHandler =
            Responder.getInstance();
    
    private JTextField txtMsg;
    private Timer idleTimer;     //To check for ideal user
    private boolean isIdle = false;
    private boolean isPrivateMessage = false;
    private String messageTo="";
    
    public static LayoutManager getInstance()
    {
        return instance;
    }

    public String getRoomImage() {
        return roomImage;
    }

    public void setRoomImage(String roomImage) {
        this.roomImage = roomImage;
    }

    public void setPrivateMessage(String messageTo)
    {
        isPrivateMessage=true;
        this.messageTo = messageTo;
    }

    public JPanel getDisplay()
    {
        
        JPanel mainDisplay = new JPanel();
        mainDisplay.setLayout(new BorderLayout());
        mainDisplay.add("Center",image);
        
        JPanel buttonPanel= new JPanel();
        txtMsg = new JTextField();
        txtMsg.setDocument(new FixedSizePlainDocument(100));
        txtMsg.setPreferredSize(new Dimension(375, 20));
        buttonPanel.add(txtMsg);
        mainDisplay.add("South",buttonPanel);
        
        txtMsg.addKeyListener(new KeyListener() {

            public void keyTyped(KeyEvent e) {
                //checkTimer();
            }

            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()== e.VK_ENTER)
                {
                    if(txtMsg.getText().length()>0)
                    {
                        if(!UserData.getInstance().getIsKickedOut())
                        {
                            checkTimer();
                            String formatedText = manageText(txtMsg.getText());
                            avatarHandler.setAvatarMsg(UserData.getInstance().getAvatarName(), formatedText);

                            if(isPrivateMessage)
                            {
                                msgHandler.sendMessage(Command.PRIVATE_MESSAGE,
                                            messageTo + ChatConstant.FIRST_SEPARATOR +
                                                txtMsg.getText());
                                isPrivateMessage=false;
                            }
                            else
                                msgHandler.sendMessage(Command.MESSAGE, txtMsg.getText());
                            Messages.getInstance().addMessage(UserData.getInstance().getAvatarName() + " : " + txtMsg.getText());
                            txtMsg.setText("");
                        }
                        else
                        {
                            MessageBox msgbox = new MessageBox();
                            msgbox.displayMessage("Your connection was closed");
                        }
                    }
                }
            }

            public void keyReleased(KeyEvent e) {
            }
        });
        
        idleTimer = new Timer(300000, new ActionListener() {
            
            public void actionPerformed(ActionEvent event) {
                Responder.getInstance().sendMessage(Command.AWAY, "1");
                awayAvatar(UserData.getInstance().getAvatarName(),true);
                refreshDisplay();
                isIdle = true;
            }
        });
        idleTimer.start();
        return mainDisplay;
    }

    public void checkTimer()
    {
        if(isIdle)
        {
            Responder.getInstance().sendMessage(Command.AWAY, "0");
            awayAvatar(UserData.getInstance().getAvatarName(),false);
            refreshDisplay();
            isIdle = false;            
        }
        idleTimer.restart();
    }

    public void setIsIdle(boolean isIdle)
    {
        this.isIdle = isIdle;
    }
    
    public String manageText(String text)
    {
        String returnValue="<HTML>";
        for(int i=0;i<text.length();i=i+22)
        {
            if(returnValue.length()>6)
                returnValue +="<BR/>";
            if(text.substring(i).length()>22)
                returnValue += text.substring(i,i+22);
            else
                returnValue += text.substring(i);
        }
        returnValue +="</HTML>";

        return returnValue;
    }

    public void changeRoomImage()
    {
        image.changePicture(roomImage);
    }
    
    public boolean isUserExist(String name)
    {
        return avatarHandler.isUserExist(name);
    }
    
    public void changeAvatarImage(String name, String image)
    {
        avatarHandler.changeAvatarImage(name, image);
    }
    
    public void setAvatarType(String name, AvatarType type)
    {
        avatarHandler.setAvatarType(name,type);
    }
    
    public void addAvatar(String name, String image)
    {
        avatarHandler.addAvatar(name,image,AvatarType.GENERAL);
    }
    
    public void addAvatar(Object[] avatar)
    {
        avatarHandler.addAvatar(avatar[0].toString(), 
                avatar[2].toString(),AvatarType.values()[Integer.parseInt(avatar[1].toString())]);
        
        changePosition(avatar[0].toString(), 
                Integer.parseInt(avatar[3].toString()), 
                Integer.parseInt(avatar[4].toString()));
    }
    
    public void addAvatars(Object[] avatar)
    {
        for(int i=0;i<avatar.length; i++)
            addAvatar((Object[])avatar[i]);
    }

    public void removeAllAvatarsExcept(String name)
    {
        avatarHandler.removeAllAvatarsExcept(name);
    }

    public void removeAvatar(String name)
    {
        avatarHandler.removeAvatar(name);
    }
    
    public void removeAllAvatar()
    {
        avatarHandler.removeAvatars();
    }
    
    public void refreshDisplay()
    {
        image.refreshDisplay();
    }
    
    public void changePosition(String userName, int x, int y)
    {
        avatarHandler.changePosition(userName, x, y);
    }
    
    public void ignoreAvatar(String name, boolean isIgnore)
    {
        avatarHandler.setAvatarIgnore(name,isIgnore);
    }

    public void awayAvatar(String name, boolean isAway)
    {
        avatarHandler.setAvatarAway(name,isAway);        
    }

    public void displayHistory()
    {
        MessageHistory msgHistory = new MessageHistory();
        Messages messages = Messages.getInstance();
        for(int i =0;i<messages.count();i++)
            msgHistory.addText(messages.getMessage(i));
        msgHistory.setVisible(true);
    }
}
