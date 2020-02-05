
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Kelly
 */
public class Connectordb {

    public static Connection connectDb() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
          Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mystoredb", "root", "root");
            
                   System.out.println("Connection is established"); 
          return conn;
            
        }
        catch (ClassNotFoundException | SQLException e) {
          System.out.println(e);
            return null;
        }
    }
}
