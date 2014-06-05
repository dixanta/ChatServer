/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chat.util;

import java.util.StringTokenizer;

/**
 *
 * @author Admin
 */
public class MessageTokenizer {

    public String[] tokenizeMsg(String msg, String separator)
    {
        StringTokenizer st = new StringTokenizer(msg, separator);
        if(st.countTokens()==0)
            return null;
        else
        {
            String[] returnStr = new String[st.countTokens()];
            int count = 0;
            while (st.hasMoreTokens()) {
                returnStr[count++] = st.nextToken();
            }
            return returnStr;
        }        
    }
    
    public Object[] tokenizeMsg(String msg, String separator1, String separator2)
    {
        StringTokenizer st = new StringTokenizer(msg, separator1);
        if(st.countTokens()==0)
            return null;
        else
        {
            Object[] returnStr = new Object[st.countTokens()];
            int count = 0;
            while (st.hasMoreTokens()) {
                StringTokenizer st1 = new StringTokenizer(st.nextToken(), separator2);
                if(st1.countTokens()==0)
                    return null;
                else
                {
                    Object[] innerStr = new Object[st1.countTokens()];
                    int innerCounter = 0;
                    while(st1.hasMoreElements())
                    {
                        innerStr[innerCounter++] = st1.nextToken();
                    }
                    returnStr[count++] = innerStr;
                }
            }
            return returnStr;
        }        
    }
}
