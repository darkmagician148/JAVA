/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package QLNS;
import static QLNS.ConnectionSQL.disConnection;
import static QLNS.ConnectionSQL.getConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;
/**
 *
 * @author admin
 */
public class QLNV {

    /**
     * @param args 
     * @throws java.sql.SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public static void main(String[] args) throws SQLException, ClassNotFoundException{
        Connection conn = getConnection();   
        Statement statement = conn.createStatement();
        /**
        * Init_table : creat table;
        * Make_Date : creat Date for table;
        */

        new Login().setVisible(true);
        disConnection(conn);
    }
    
}
