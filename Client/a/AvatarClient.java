
import chat.data.object.Command;
import chat.message.Responder;
import chat.ui.LayoutManager;
import chat.userData.UserData;
import chat.util.MessageBox;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;
import javax.swing.JApplet;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Admin
 */
public class AvatarClient extends JApplet {
    public void init() {        
        MessageBox msgBox = new MessageBox();
        
        String userName = getParameter("userName");
        String avatarID = getParameter("avatarID");
        String roomID = getParameter("roomID");
        
        /*msgBox.displayMessage(userName);
        msgBox.displayMessage(avatarID);
        msgBox.displayMessage(roomID);*/
        
        /*String userName = "anil";
        String avatarID = "2";
        String roomID = "2";*/
        
        Responder responder = Responder.getInstance();
        responder.setMyName(userName);
        
        String server="Anil-PC";
        String port="9090";

        Properties configFile=new Properties();
        try{
            URL propSource = new URL (getDocumentBase(), "config.properties");
            InputStream propIS = propSource.openStream();
            configFile.load(propIS);            
            server=configFile.getProperty("server");
            port=configFile.getProperty("port");
            propIS.close();
        }catch(Exception ex)
        {
        }
        
        //logout user if already login
        responder.sendMessage(Command.LOGOUT, userName);
        
        responder.setHost(server);
        responder.setPort(Integer.parseInt(port));
        if(responder.login())
        {
            LayoutManager canvas = LayoutManager.getInstance();
            UserData userData = UserData.getInstance();            
            userData.setAvatarID(Integer.parseInt(avatarID));
            userData.setAvatarName(userName);
            userData.setRoomID(Integer.parseInt(roomID));
            
            userData.setUserApplet(this);
            
            getContentPane().add(canvas.getDisplay());
            
            responder.sendMessage(Command.GET_ROOM_IMAGE, roomID);
            responder.sendMessage(Command.GET_AVATAR_IMAGE, avatarID);
            responder.sendMessage(Command.GET_MEMBER_TYPE, userName);
            responder.sendMessage(Command.GET_ROOM_USERS, roomID);

            //canvas.refreshDisplay();
        }
        else
        {
            msgBox = new MessageBox();
            msgBox.displayMessage(responder.getErrMsg());
        }
    }
    
    public void avatarID(int id)
    {
        UserData.getInstance().setAvatarID(id);
        Responder responder = Responder.getInstance();
        responder.sendMessage(Command.GET_AVATAR_IMAGE, String.valueOf(id));
        responder.sendMessage(Command.CHANGE_AVATAR, String.valueOf(id));
    }
    
    public void roomID(int id)
    {
        UserData.getInstance().setRoomID(id);
        Responder responder = Responder.getInstance();
        responder.sendMessage(Command.GET_ROOM_IMAGE, String.valueOf(id));
        responder.sendMessage(Command.GET_ROOM_USERS, String.valueOf(id));
        //LayoutManager.getInstance().refreshDisplay();
    }

    public void showHistory()
    {
        LayoutManager.getInstance().displayHistory();
    }

    public void away()
    {
        Responder.getInstance().sendMessage(Command.AWAY, "1");
        LayoutManager display = LayoutManager.getInstance();
        display.setIsIdle(true);
        display.awayAvatar(UserData.getInstance().getAvatarName(), true);
        display.refreshDisplay();
    }

    public void arrive()
    {
        Responder.getInstance().sendMessage(Command.AWAY, "0");
        LayoutManager display = LayoutManager.getInstance();
        display.setIsIdle(false);
        display.awayAvatar(UserData.getInstance().getAvatarName(), false);
        display.refreshDisplay();
    }
}
