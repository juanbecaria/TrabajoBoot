package datos;


import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by juanb on 10/11/2016.
 */

public class DBConnection {

    private  Connection connection;
    private  String conString="jdbc:sqlserver://DESKTOP-OBB6MVN;databaseName=Bootcamp;integratedSecurity=true;";
    private static DBConnection dbConnection;

    private DBConnection(){
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(conString);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static DBConnection getInstance(){
        try {
            if (dbConnection == null || dbConnection.getConnection().isClosed()) {
                dbConnection = new DBConnection();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dbConnection;

    }

    public Connection getConnection() {return connection;  }

   /* public static Connection create(){
        Connection con = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-OBB6MVN;databaseName=Bootcamp;integratedSecurity=true;");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;

    }*/






   /* public Connection getConnection(){return connection;}*/



}
