package datos;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import negocio.Atmosfera;
import negocio.Localidad;
import org.springframework.stereotype.Repository;
import soporte.AtmosferaBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by juanb on 9/28/2016.
 */
@Repository
public class GestorAtmosfera {

    public void guardar(Localidad loca, String fecha, Atmosfera at){
        try {

            Connection con = DBConnection.getInstance().getConnection();


            String insert= "INSERT INTO Atmosfera (fecha, ciudad, pais, region, presion, visivilidad, ambienteAscendente, humedad) VALUES (?, ?, ?, ?, ?, ?, ?, ?) ";


            PreparedStatement st = con.prepareStatement(insert);
            st.setString(1,fecha);
            st.setString(2,loca.getCiudad());
            st.setString(3,loca.getPais());
            st.setString(4,loca.getRegion());
            st.setFloat(5,at.getPresion());
            st.setFloat(6,at.getVisibilidad());
            st.setFloat(7,at.getAmbienteAscendente());
            st.setFloat(8,at.getHumedad());

            st.execute();
            st.close();


        }
        catch (SQLServerException e){
            System.out.println(e.getMessage());

        }

        catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public Atmosfera buscarPorId(Localidad l, String fecha){
        Atmosfera at= null;

        Connection con = null;
        try {
            con = DBConnection.getInstance().getConnection();
            String search= "SELECT presion, visibilidad, humedad, ambienteAscendente FROM Atmosfera WHERE ciudad=? AND region=? AND pais=? AND fecha=?";

            PreparedStatement st = con.prepareStatement(search);

            st.setString(1,l.getCiudad());
            st.setString(2,l.getRegion());
            st.setString(3,l.getPais());
            st.setString(4,fecha);

            ResultSet resultSet = st.executeQuery();


            at = new AtmosferaBuilder().withPresion(resultSet.getFloat(1)).withVisibilidad(resultSet.getFloat(2)).withHumedad(resultSet.getFloat(3)).withAmbienteAscendente(resultSet.getFloat(4)).createAtmosfera();

            resultSet.close();
            st.close();


        } catch (SQLException e) {
            e.printStackTrace();
        }


        return  at;
    }


}
