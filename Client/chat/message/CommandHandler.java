/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chat.message;

import chat.data.object.DataObject;

/**
 *
 * @author Admin
 */
public abstract class CommandHandler {
    public abstract void execute(DataObject dataObject);
}
