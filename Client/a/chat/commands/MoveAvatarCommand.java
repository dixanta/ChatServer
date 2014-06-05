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
public class MoveAvatarCommand extends CommandHandler{
    public void execute(DataObject msg)
    {
        MessageTokenizer msgTokenizer = new MessageTokenizer();
        LayoutManager display = LayoutManager.getInstance();
        String[] pos = msgTokenizer.tokenizeMsg(msg.getMessage(), 
                                    ChatConstant.FIRST_SEPARATOR);
        display.changePosition(msg.getUserName(), Integer.parseInt(pos[0]),Integer.parseInt(pos[1]));
        display.refreshDisplay();
    }
}
