/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chat.commands;
import chat.data.object.Command;
import java.util.Hashtable;
/**
 *
 * @author Dixanta Bahadur Shrestha
 */
public class CommandFactory {
    private static Hashtable commands=initialize();
    
    private static Hashtable initialize()
    {
       Hashtable commandTable=new Hashtable();
       commandTable.put(Command.LOGIN,new LoginCommand());
       commandTable.put(Command.GET_ROOM_IMAGE,new GetRoomImageCommand());
       commandTable.put(Command.GET_AVATAR_IMAGE,new GetAvatarImageCommand());
       commandTable.put(Command.MOVEAVATAR,new MoveAvatarCommand());
       commandTable.put(Command.GET_MEMBER_TYPE,new GetMemberTypeCommand());
       commandTable.put(Command.GET_ROOM_USERS,new GetRoomUsersCommand());
       commandTable.put(Command.MESSAGE,new MessageCommand());
       commandTable.put(Command.BLOCK_USER,new BlockUserCommand());
       commandTable.put(Command.UNBLOCK_USER,new UnblockUserCommand());
       commandTable.put(Command.KICK_USER,new KickOutCommand());
       commandTable.put(Command.LOGOUT,new LogoutCommand());
       commandTable.put(Command.CHANGE_AVATAR,new ChangeAvatarCommand());
       commandTable.put(Command.AWAY,new AwayCommand());
       commandTable.put(Command.PRIVATE_MESSAGE, new PrivateMessageCommand());
       return commandTable;
    }
    
    public static boolean isCommand(Command cmd)
    {
        return commands.containsKey(cmd);
    }
    
    public static ChatCommand getCommand(Command cmd)
    {
        return (ChatCommand)commands.get(cmd);
    }

}
