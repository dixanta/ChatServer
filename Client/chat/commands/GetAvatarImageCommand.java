/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chat.commands;

import chat.constants.ChatConstant;
import chat.data.object.Command;
import chat.data.object.DataObject;
import chat.message.CommandHandler;
import chat.message.Responder;
import chat.ui.LayoutManager;
import chat.userData.UserData;
import java.util.Random;

/**
 *
 * @author Admin
 */
public class GetAvatarImageCommand extends CommandHandler {    
    public void execute(DataObject msg)
    {
        LayoutManager display = LayoutManager.getInstance();
        //MessageTokenizer msgTokenizer = new MessageTokenizer();
        /*display.addAvatar(msgTokenizer.tokenizeMsg(msg.getMessage(), 
                ChatConstant.FIRST_SEPARATOR));*/
        //display.removeAvatar(msg.getUserName());
        
        
        if(display.isUserExist(msg.getUserName()))
        {
            display.changeAvatarImage(msg.getUserName(), msg.getMessage());
            display.refreshDisplay();
        }
        else
        {
            display.addAvatar(msg.getUserName(), msg.getMessage());

            int x = getRandomNo(300);
            int y = getRandomNo(300);
            display.changePosition(UserData.getInstance().getAvatarName(), x, y);
            Responder.getInstance().sendMessage(Command.MOVEAVATAR, x + ChatConstant.FIRST_SEPARATOR + y);            
        }        
    }
    
    private int getRandomNo(int no)
    {
        Random randomGenerator = new Random();
        return randomGenerator.nextInt(no);
    }
}
