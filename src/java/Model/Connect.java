/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.naming.Context;
import javax.naming.InitialContext;

/**
 *
 * @author ADMIN
 */
public class Connect {
    
    public Connection getConnection() throws Exception{
        Connection con = null;
        try{
            InitialContext intContext = new InitialContext();
            Context evnContext = (Context) intContext.lookup("java:comp/env");
            String serverName = (String) evnContext.lookup("local");
            String dbName = (String) evnContext.lookup("dbName");
            String portNumber = (String) evnContext.lookup("portNumber");
            String userID = (String) evnContext.lookup("userID");
            String password = (String) evnContext.lookup("password");
            String url = "jdbc:sqlserver://" + serverName + ":" + portNumber +
                    ";databaseName=" + dbName;
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").getInterfaces();
            con = DriverManager.getConnection(url, userID, password);
            
        } catch (Exception e){
            throw e;
        }
        return con;
    }
    
}
