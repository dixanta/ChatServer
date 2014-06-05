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
public class RoomProvider {
    private static DbConnection conn=null;
    private static String errMsg="";
    
    public static String getRoomImage(int pictureId)
    {
        try
        {
            String image="";
            String sql="select * from room where RoomID=" + pictureId;
            conn= new DbConnection();
            conn.connect();
            ResultSet rs=conn.executeQuery(sql);
            if(rs.next())
                image=rs.getString("RoomImage");
            conn.close();
            return image;
        }
        catch(Exception ex)
        {
            errMsg = ex.getMessage();
            return null;
        }
    }

    public static String getErrMsg() {
        return errMsg;
    }
}
