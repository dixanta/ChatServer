/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chat.commands;

import chat.data.object.DataObject;
import chat.message.CommandHandler;
import chat.ui.LayoutManager;
import chat.userData.UserData;
import chat.util.MessageBox;
import java.net.URL;
import javax.swing.JApplet;

/**
 *
 * @author Admin
 */
public class KickOutCommand extends CommandHandler {    
    public void execute(DataObject msg)
    {
        LayoutManager display = new LayoutManager();
        display.removeAllAvatar();
        UserData.getInstance().setIsKickedOut(true);        
        
        JApplet applet = UserData.getInstance().getUserApplet();        
        try
        {                        
            applet.getAppletContext().showDocument(new URL(applet.getCodeBase() + "../lib/kickuser.php?username=" + msg.getUserName()),"function");
        }
        catch(Exception ex)
        {                        
            MessageBox msgbox = new MessageBox();
            msgbox.displayMessage(ex.getMessage());
        }
        display.refreshDisplay();
    }
}
