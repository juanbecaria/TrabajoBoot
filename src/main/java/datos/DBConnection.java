package datos;


import org.springframework.stereotype.Component;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by juanb on 10/11/2016.
 */
@Component
public class DBConnection {

    private Connection connection = null;
    private String conString="jdbc:sqlserver://DESKTOP-OBB6MVN;databaseName=Bootcamp;integratedSecurity=true;";



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



    public Connection getConnection(){return connection;}



}
