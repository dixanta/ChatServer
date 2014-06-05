/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chat.avatar;

import chat.data.object.Command;
import chat.message.Responder;
import chat.ui.CustomMenuItem;
import chat.ui.LayoutManager;
import chat.ui.SimpleTipPositioner;
import chat.userData.UserData;
import chat.util.MessageBox;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import javax.swing.JApplet;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import net.java.balloontip.styles.BalloonTipStyle;
import net.java.balloontip.styles.RoundedBalloonStyle;
import net.java.balloontip.BalloonTip;
import javax.swing.Timer;

/**
 *
 * @author Admin
 */
public class Avatar {
    private String name;
    private AvatarImage image;
    private AvatarType myType;
    private BalloonTip msg;
    private boolean isBlocked;
    private boolean isIgnored;
    private boolean isAway;
    private Timer msgTimer;
    private int x;
    private int y;
    private JPopupMenu Pmenu;
    
    public Avatar(String name, AvatarImage image, AvatarType myType)
    {
        this.name=name;
        this.image=image;
        this.myType=myType;
        if(!UserData.getInstance().getAvatarName().equals(name))
            addPopup();
        else
        {
            Pmenu = new JPopupMenu();
            JCheckBoxMenuItem cbMenuItem = new JCheckBoxMenuItem("Away");
            ItemListener iListener = new ItemListener() {
              public void itemStateChanged(ItemEvent event) {
                int state = event.getStateChange();
                if (state == ItemEvent.SELECTED) {
                    setAway(true);
                }
                else
                {
                    setAway(false);
                }
              }
            };
            cbMenuItem.addItemListener(iListener);
        }


        
        BalloonTipStyle edgedLook = new RoundedBalloonStyle(6, 6, Color.WHITE, Color.BLACK);
        msg = new BalloonTip(this.image,  "This is testing", edgedLook, new SimpleTipPositioner(), false);
        msg.setPadding(0);
        //msg = new BalloonTip(this.image,  "This is testing", edgedLook, BalloonTip.Orientation.RIGHT_ABOVE, BalloonTip.AttachLocation.CENTER, 5, 5, false);
        //msg = new BalloonTip(this.image, "This is testing", edgedLook,false);
        msg.setVisible(false);
        msg.setMaximumSize(new Dimension(100, 50));
        msg.setFont(new Font("Tahoma", 0, 8));
        msgTimer = new Timer(5000, new ActionListener() {
            
            public void actionPerformed(ActionEvent event) {
                msg.setVisible(false);
                LayoutManager.getInstance().refreshDisplay();
            }
        });
    }

    private void setAway(boolean isAway)
    {
        Responder responder = Responder.getInstance();
        if(isAway)
            responder.sendMessage(Command.AWAY, "1");
        else
            responder.sendMessage(Command.AWAY, "0");
        this.isAway = isAway;
        this.image.setIsAway(isAway);
        this.image.repaint();
    }

    public AvatarType getMyType() {
        return myType;
    }

    public void setMyType(AvatarType myType) {
        this.myType = myType;
        this.image.setType(myType);
    }
    
    public String getMsg() {
        return msg.getText();
    }

    public void setMsg(String msg) {
        if(!msg.equals(""))
        {
            this.msg.setText(msg);
            this.msg.setVisible(true);
            if(msgTimer.isRunning())
                msgTimer.restart();
            else
                msgTimer.start();
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    public void removeMsgBox()
    {
        msg.setVisible(false);
        msg=null;
    }
    
    public AvatarImage getImage() {
        return image;
    }

    public void setImage(AvatarImage image) {
        this.image = image;
    }

    public boolean isIsBlocked() {
        return isBlocked;
    }

    public void setIsBlocked(boolean isBlocked) {
        this.isBlocked = isBlocked;
        image.setIsBlocked(isBlocked);
    }

    public boolean isIsIgnored() {
        return isIgnored;
    }

    public void setIsIgnored(boolean isIgnored) {
        this.isIgnored = isIgnored;
        image.setIsIgnored(isIgnored);
    }

    public boolean isIsAway() {
        return isAway;
    }

    public void setIsAway(boolean isAway) {
        this.isAway = isAway;
        image.setIsAway(isAway);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    private void addPopup()
    {
        Pmenu = new JPopupMenu();
        Pmenu.add(getMenuItem(name, true));
        Pmenu.addSeparator();
        //Pmenu.add(getMenuItem("Private message", false));
        Pmenu.add(getMenuItem("Ignore user", false));
        Pmenu.add(getMenuItem("Send chatmail" , false));
        Pmenu.add(getMenuItem("View profile" , false));
        
        AvatarType type = AvatarHandler.getInstance().getAvatar(
                UserData.getInstance().getAvatarName()).getMyType();
        
        switch(myType)
        {
            case ADMIN:
                /*Pmenu.add(getMenuItem("View Profile"));
                Pmenu.addSeparator();
                Pmenu.add(getMenuItem("Chat"));
                Pmenu.add(getMenuItem("Block"));
                Pmenu.add(getMenuItem("Kick out"));
                JMenu mnu = new JMenu("Invite to my");        
                rooms.beginIterrate();
                room = rooms.nextRooms();
                while(room!=null)
                {
                    mnu.add(getMenuItem(room.getName()));
                    room = rooms.nextRooms();
                }
                Pmenu.add(mnu);*/
                
                break;
            case SENIOR_MODERATOR:
                if(type==AvatarType.ADMIN)
                    Pmenu.add(getMenuItem("Kick user",false));
                break;
            case MODERATOR:
                if(type==AvatarType.ADMIN || type == AvatarType.SENIOR_MODERATOR)
                    Pmenu.add(getMenuItem("Kick user",false));
                break;
            case MEMBER:
                if(type==AvatarType.ADMIN || type == AvatarType.MODERATOR
                        || type == AvatarType.SENIOR_MODERATOR)
                    Pmenu.add(getMenuItem("Kick user",false));
                break;
            case GENERAL:
                if(type==AvatarType.ADMIN || type == AvatarType.MODERATOR
                        || type == AvatarType.SENIOR_MODERATOR)
                    Pmenu.add(getMenuItem("Kick user",false));
                break;
        }
        
        this.image.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent Me){
                if(Me.isPopupTrigger()){
                  Pmenu.show(Me.getComponent(), Me.getX(), Me.getY());
                }
            }
        });
    }
    
    private CustomMenuItem getMenuItem(String menuName, boolean  isHeader)
    {
        CustomMenuItem menuItem=new CustomMenuItem(menuName, isHeader);
        menuItem.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                
                MessageBox msgbox = new MessageBox();
                JApplet applet = UserData.getInstance().getUserApplet();
                
                if(e.getActionCommand().equals("Ignore user"))
                {
                    if(checkType())
                    {
                        //Responder responder = Responder.getInstance();
                        //responder.sendMessage(Command.BLOCK_USER, name);
                        isBlocked = true;
                        image.setIsBlocked(isBlocked);
                        image.repaint();
                        ((JMenuItem)e.getSource()).setText("Un-ignore user");
                    }
                }
                else if(e.getActionCommand().equals("Un-ignore user"))
                {
                    if(checkType())
                    {
                        //Responder responder = Responder.getInstance();
                        //responder.sendMessage(Command.UNBLOCK_USER, name);
                        isBlocked = false;
                        image.setIsBlocked(isBlocked);
                        image.repaint();
                        ((JMenuItem)e.getSource()).setText("Ignore user");
                    }
                }
                else if(e.getActionCommand().equals("Kick user"))
                {
                    Responder responder = Responder.getInstance();
                    responder.sendMessage(Command.KICK_USER, name);
                }                
                else if(e.getActionCommand().equals("View profile"))
                {
                    try
                    {                        
                        applet.getAppletContext().showDocument(new URL(applet.getCodeBase() + "../viewprofile.php?search=" + name),"frame");
                    }
                    catch(Exception ex)
                    {                        
                        msgbox.displayMessage(ex.getMessage());
                    }
                }
                else if(e.getActionCommand().equals("Send chatmail"))
                {
                    try
                    {
                        applet.getAppletContext().showDocument(new URL(applet.getCodeBase() + "../chatmail/sendmessage.php?search" + name),"frame");
                    }
                    catch(Exception ex)
                    {
                        msgbox.displayMessage(ex.getMessage());
                    }
                }
                else if(e.getActionCommand().equals("Private message"))
                {
                    LayoutManager display = LayoutManager.getInstance();
                    display.setPrivateMessage(name);
                }
            }
        });
        return menuItem;
    }
    
    private boolean checkType()
    {
        AvatarType type = AvatarHandler.getInstance().getAvatar(
                UserData.getInstance().getAvatarName()).getMyType();
        if(type==AvatarType.ADMIN)
            return true;
        else if(type == AvatarType.SENIOR_MODERATOR && myType !=AvatarType.ADMIN)
            return true;
        else if(type == AvatarType.MODERATOR && myType !=AvatarType.ADMIN &&
                myType !=AvatarType.SENIOR_MODERATOR)
            return true;
        else if(type == AvatarType.MEMBER && myType !=AvatarType.ADMIN &&
                myType !=AvatarType.SENIOR_MODERATOR && myType != AvatarType.MODERATOR)
            return true;
        else if(type == AvatarType.GENERAL && myType !=AvatarType.ADMIN &&
                myType !=AvatarType.SENIOR_MODERATOR && myType != AvatarType.MODERATOR)
            return true;
        else
            return false;
    }   
}
