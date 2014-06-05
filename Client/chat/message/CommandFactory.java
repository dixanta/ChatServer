/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chat.message;

import chat.commands.*;
import chat.data.object.Command;
import java.util.Hashtable;

/**
 *
 * @author Admin
 */
public class CommandFactory {
    private static Hashtable registeredCommands = initialize();
    
    private static Hashtable initialize()
    {
        Hashtable commandTable=new Hashtable();        
        commandTable.put(Command.GET_ROOM_IMAGE,new GetRoomImageCommand());
        commandTable.put(Command.GET_AVATAR_IMAGE,new GetAvatarImageCommand());
        commandTable.put(Command.GET_MEMBER_TYPE,new GetMemberTypeCommand());
        commandTable.put(Command.GET_ROOM_USERS,new RoomUsersCommand());
        commandTable.put(Command.MOVEAVATAR,new MoveAvatarCommand());
        commandTable.put(Command.NEW_ROOM_USER,new NewRoomUserCommand());
        commandTable.put(Command.MESSAGE,new MessageCommand());
        commandTable.put(Command.BLOCK_USER,new BlockUserCommand());
        commandTable.put(Command.UNBLOCK_USER,new UnblockUserCommand());
        commandTable.put(Command.KICK_USER,new KickOutCommand());
        commandTable.put(Command.LOGOUT,new LogOutCommand());
        commandTable.put(Command.CHANGE_AVATAR,new ChangeAvatarCommand());
        commandTable.put(Command.AWAY,new AwayCommand());
        commandTable.put(Command.PRIVATE_MESSAGE, new PrivateMessageCommand());
        return commandTable;
    }
    
    public static boolean isCommand(Command cmd)
    {
        return registeredCommands.containsKey(cmd);
    }
    
    public static CommandHandler getCommand(Command cmd)
    {
        return (CommandHandler)registeredCommands.get(cmd);
    }
}
