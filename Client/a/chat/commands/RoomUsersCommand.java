/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chat.commands;

import chat.constants.ChatConstant;
import chat.data.object.DataObject;
import chat.message.CommandHandler;
import chat.ui.LayoutManager;
import chat.util.MessageTokenizer;

/**
 *
 * @author Admin
 */
public class RoomUsersCommand extends CommandHandler{
    public void execute(DataObject msg)
    {
        LayoutManager display = LayoutManager.getInstance();
        if(!msg.getMessage().equals(""))
        {            
            MessageTokenizer msgTokenizer = new MessageTokenizer();            
            display.addAvatars(msgTokenizer.tokenizeMsg(
                                    msg.getMessage(), ChatConstant.SECOND_SEPARATOR, 
                                    ChatConstant.FIRST_SEPARATOR));
        }
        display.refreshDisplay();
        try
        {
            Thread.sleep(200);
        }
        catch(InterruptedException IEx){}
        display.refreshDisplay();
    }
}
