/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QLNS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author admin
 */
public class ConnectionSQL {
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        try{
        class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        }
        catch(Exception e){
            System.out.print(e);
        }
        String url = "jdbc:sqlserver://localhost:1433; databaseName=Java";
        String user = "tuanmiku";
        String password = "tuanmiku";
        Connection conn = DriverManager.getConnection(url, user, password);
        System.out.println("Connected !!");
        return conn;
    }
    public static void disConnection(Connection conn) throws SQLException{
        conn.close();
        System.out.println("Disconnect !!");
    }
}
