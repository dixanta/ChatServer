/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package db.util;
import java.io.*;
import java.util.Properties;
import java.sql.*;
/**
 *
 * @author Dixanta Bahadur Shrestha
 */
public class DbConnection {
    Connection sqlConnection=null;
    ResultSet resultSet=null;
    Statement stmt=null;

    public void connect() throws ClassNotFoundException,SQLException,IOException {
        Class.forName("com.mysql.jdbc.Driver");
        Properties configFile=new Properties();
        try{
        configFile.load(new FileInputStream("config.properties"));
        String hostname=configFile.getProperty("hostname");
        String username=configFile.getProperty("username");
        String password=configFile.getProperty("password");
        sqlConnection=DriverManager.getConnection(hostname,username,password);
        }catch(FileNotFoundException fe)
        {
            System.out.println(fe.getMessage());
        }
        
    }
    
    public boolean execute(String sql) throws SQLException
    {
        stmt=sqlConnection.createStatement();
        boolean result=stmt.execute(sql);
        return result;
    }
    
    public int executeInsert(String sql) throws SQLException
    {
        stmt=sqlConnection.createStatement();
        int insertId=0;
        stmt.executeUpdate(sql);
        ResultSet rs=stmt.getGeneratedKeys();
        if (rs != null && rs.next()) {
              insertId = rs.getInt(1);
        }
        return insertId;    
    }
    
    public ResultSet executeQuery(String sql) throws SQLException
    {
       stmt=sqlConnection.createStatement();
       ResultSet rs=stmt.executeQuery(sql);
       return rs;
    }
    
    public void close() throws SQLException
    {
        if(sqlConnection!=null)
        {
            sqlConnection.close();
            sqlConnection=null;
        }
    }
}
