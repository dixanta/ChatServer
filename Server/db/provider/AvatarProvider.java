/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package db.provider;

import java.sql.*;
import db.util.DbConnection;

/**
 *
 * @author Dixanta Bahadur Shrestha
 */
public class AvatarProvider {
    private static DbConnection conn=null;
    private static String errMsg="";
    
    public static String getAvatarImage(int avatarID)
    {
        try
        {
            String image="";
            String sql="select * from avatar where AvatarID=" + avatarID;
            conn= new DbConnection();
            conn.connect();
            ResultSet rs=conn.executeQuery(sql);
            if(rs.next())
                image=rs.getString("AvatarImage");
            conn.close();
            return image;
        }
        catch(Exception ex)
        {
            errMsg = ex.getMessage();
            return null;
        }
    }

    public static int getMemberType(String avatarName)
    {
        try
        {
            int memberType=-1;
            String sql="select * from member where UserName='" + avatarName + "'";
            conn= new DbConnection();
            conn.connect();
            ResultSet rs=conn.executeQuery(sql);
            if(rs.next())
                memberType= Integer.parseInt(rs.getString("MemberType"));
            conn.close();
            return memberType;
        }
        catch(Exception ex)
        {
            errMsg = ex.getMessage();
            return -1;
        }
    }
    
    public static String getErrMsg() {
        return errMsg;
    }
}
