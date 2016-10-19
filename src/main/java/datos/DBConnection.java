package datos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by juanb on 10/11/2016.
 */
@Repository
public class DBConnection {

    private Connection connection = null;
    private String conString="jdbc:sqlserver://DESKTOP-OBB6MVN;databaseName=Bootcamp;integratedSecurity=true;";

    private static DBConnection instance= new DBConnection();

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

    public static DBConnection getInstance() throws SQLException {
        if(instance==null){
            instance = new DBConnection();
        }else{
            if(instance.getConnection().isClosed()){
                instance = new DBConnection();
            }
        }

        return instance;
    }

    public Connection getConnection(){return connection;}



}
