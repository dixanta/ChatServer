/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chat.avatar;

import java.util.Enumeration;
import java.util.Hashtable;

/**
 *
 * @author Admin
 */
public class AvatarHandler {
    private final static AvatarHandler instance =
            new AvatarHandler();
    
    private Hashtable avatars = new Hashtable();
    private int pos;
    
    public static AvatarHandler getInstance()
    {
        return instance;
    }
    
    public void addAvatar(String name, String image, AvatarType myType)
    {
        avatars.put(name, 
                new Avatar(name, 
                new AvatarImage(image, name,myType), myType));
        //if(myType==AvatarType.OTHER)
        //    showHideKickOut(id, false);
    }
    
    public void setAvatarType(String name, AvatarType type)
    {
        ((Avatar)avatars.get(name)).setMyType(type);
    }
    
    public void showHideKickOut(int id, boolean status)
    {
        //((Avatar)avatars.get(id)).showHideKickoutMenu(status);
    }
    
    public void setAvatarMsg(String name,String msg)
    {
        ((Avatar)avatars.get(name)).setMsg(msg);
    }
    
    public void removeAvatars()
    {
        Enumeration e = avatars.elements();
        while(e.hasMoreElements())
        {
            Avatar obj = (Avatar)e.nextElement();
            obj.removeMsgBox();
        }
        avatars.clear();
    }

    public void removeAllAvatarsExcept(String name)
    {
        Enumeration e = avatars.elements();
        while(e.hasMoreElements())
        {
            Avatar obj = (Avatar)e.nextElement();
            if(!obj.getName().equals(name))
                obj.removeMsgBox();
        }
        Avatar me = (Avatar)avatars.get(name);
        avatars.clear();
        avatars.put(name, me);
    }
    
    public void removeAvatar(String name)
    {
        if(avatars.containsKey(name))
        {
            ((Avatar)avatars.get(name)).removeMsgBox();
            avatars.remove(name);
        }
    }
    
    public Avatar getAvatar(String name)
    {
        return (Avatar)avatars.get(name);
    }
    
    public void beginIterrate()
    {
        pos=0;
    }
    
    public Avatar nextAvatar()
    {
        Avatar avatar = null;
        if (pos < avatars.size())
        {
            Enumeration e = avatars.elements();
            int counter=0;
            while(e.hasMoreElements())
            {
                avatar =(Avatar)e.nextElement();                
                if(counter++==pos)
                    break;
            }
            pos++;
        }        
        return avatar;
    }
    
    public void changePosition(String name, int x, int y)
    {
        Avatar avatar= (Avatar)avatars.get(name);
        avatar.setX(x);
        avatar.setY(y);
    }
    
    public void setAvatarIgnore(String name, boolean isIgnore)
    {
        ((Avatar)avatars.get(name)).setIsIgnored(isIgnore);
    }

    public void setAvatarAway(String name, boolean isAway)
    {
        ((Avatar)avatars.get(name)).setIsAway(isAway);
    }
    
    public void setAvatarBlock(String name, boolean isBlock)
    {
        ((Avatar)avatars.get(name)).setIsIgnored(isBlock);
    }
    
    public boolean isUserExist(String name)
    {
        return avatars.containsKey(name);
    }
    
    public void changeAvatarImage(String name, String image)
    {
        ((Avatar)avatars.get(name)).getImage().changePicture(image);
    }
}
