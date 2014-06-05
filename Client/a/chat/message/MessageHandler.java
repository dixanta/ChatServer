/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chat.message;

import chat.data.object.DataObject;
import java.util.Vector;

/**
 *
 * @author Admin
 */
public class MessageHandler extends Thread{
    private Vector serverData = new Vector();
    
    public void run() {
        while (true) {
            try
            {
                DataObject data = getData();
                if(CommandFactory.isCommand(data.getCommand()))
                {
                    CommandHandler command=(CommandHandler)CommandFactory.getCommand(data.getCommand());
                    command.execute(data);
                }
            }
            catch(Exception ex)
            {
            }
        }
    }
    
    public void queueData(Object data)
    {
        try
        {
            serverData.add(data);
            notify();
        }
        catch(Exception ex)
        {}
    }
    
    public DataObject getData() throws InterruptedException
    {
        if(serverData.size()==0)
            wait();
        DataObject obj = (DataObject)serverData.get(0);
        serverData.remove(0);
        return obj;
    }
}
