package datos;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import negocio.DiaActual;
import negocio.Localidad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import soporte.DiaActualBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by juanb on 9/28/2016.
 */
@Repository
public class GestorDiaActual {
    @Autowired
    private DBConnection dbConnection;


    public void guardar(Localidad loca, DiaActual dia){


        String insert= "INSERT INTO DiaActual (fecha, ciudad, pais, region, temperatura, descripcion) VALUES (?, ?, ?, ?, ?, ?) ";

        try {

            Connection con = dbConnection.getConnection();

            PreparedStatement st = con.prepareStatement(insert);
            st.setString(1, dia.getFecha());
            st.setString(2,loca.getCiudad());
            st.setString(3,loca.getPais());
            st.setString(4,loca.getRegion());
            st.setFloat(5,dia.getTemperatura());
            st.setString(6,dia.getDescripcion());

            st.execute();
            st.close();


        }catch (SQLServerException e){

        }
        catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public DiaActual buscarPorId(Localidad l, String fecha){
        DiaActual da= null;

        Connection con = null;
        try {
            con = dbConnection.getConnection();
            String search= "SELECT fecha, descripcion, temperatura FROM DiaActual WHERE ciudad=? AND region=? AND pais=? AND fecha=?";

            PreparedStatement st = con.prepareStatement(search);

            st.setString(1,l.getCiudad());
            st.setString(2,l.getRegion());
            st.setString(3,l.getPais());
            st.setString(4,fecha);

            ResultSet resultSet = st.executeQuery();


            da = new DiaActualBuilder().withFecha(resultSet.getString(1)).withDescripcion(resultSet.getString(2)).withTemp(resultSet.getFloat(3)).createDiaActual();

            resultSet.close();
            st.close();


        } catch (SQLException e) {
            e.printStackTrace();
        }


        return  da;
    }
}
