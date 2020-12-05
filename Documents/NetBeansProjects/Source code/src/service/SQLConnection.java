/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controler;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author Vostro 3580
 */
public class SQLConnection {
    public SQLConnection(){
        
    }
    public Connection Connect() throws SQLException{ 
        Connection conn=null;
        String dbURL = "jdbc:sqlserver://127.0.0.1:1433;databaseName=QLThuVien";
        String dbUSER="sa";
        String dbPass="123456";
        conn=DriverManager.getConnection(dbURL,dbUSER,dbPass);
        return conn;
    }
   
}
