/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chat.data.object;

/**
 *
 * @author suman
 */
public class DataObject implements java.io.Serializable {
    private Command command;
    private String message;
    private String userName;

    public DataObject() {
    }

    public DataObject(Command command, String message,String userName) {
        this.command = command;
        this.message = message;
        this.userName=userName;
    }

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    
}
