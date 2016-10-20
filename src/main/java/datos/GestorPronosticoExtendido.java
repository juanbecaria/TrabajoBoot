package datos;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import negocio.Localidad;
import negocio.PronosticoExtendido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import soporte.PronosticoExtendidoBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by juanb on 9/28/2016.
 */
@Repository
public class GestorPronosticoExtendido {
    @Autowired
    private DBConnection dbConnection;



    public void guardar(Localidad loca, String fecha, List<PronosticoExtendido> pe){

        String insert= "INSERT INTO PronosticoExtendido (fechadia, ciudad, pais, region, fechaPronostico, minima, maxima, descripcion, nombre) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) ";

        try {

            Connection con = dbConnection.getConnection();

            for(int i = 0; i < pe.size();i++) {

                 PreparedStatement st = con.prepareStatement(insert);

                 st.setString(1,fecha);
                 st.setString(2, loca.getCiudad());
                 st.setString(3, loca.getPais());
                 st.setString(4, loca.getRegion());
                 st.setString(5, pe.get(i).getFecha());
                 st.setFloat(6, pe.get(i).getTempMin());
                 st.setFloat(7, pe.get(i).getTempMax());
                 st.setString(8, pe.get(i).getDescripcion());
                 st.setString(9, pe.get(i).getDia());

                 st.execute();
                 st.close();

             }






        }catch (SQLServerException e){
            System.out.println(e.getMessage());
        }
        catch (SQLException e) {
            e.printStackTrace();
        }


    }


    public List<PronosticoExtendido> buscarPorId(Localidad l, String fecha){


        List<PronosticoExtendido> list = new ArrayList<>();
        Connection con = null;

        try {
            con = dbConnection.getConnection();
            String search= "SELECT minima, maxima, descripcion, fechaPronostico, nombre FROM PronosticoExtendido WHERE ciudad=? AND region=? AND pais=? AND fechaDia=?";

            PreparedStatement st = con.prepareStatement(search);

            st.setString(1,l.getCiudad());
            st.setString(2,l.getRegion());
            st.setString(3,l.getPais());
            st.setString(4,fecha);


            ResultSet resultSet = st.executeQuery();
            PronosticoExtendido p = null;
            while (resultSet.next()) {
                p = new PronosticoExtendidoBuilder().withTempMin(resultSet.getFloat(1)).withTempMax(resultSet.getFloat(2)).withDescripcion(resultSet.getString(3)).withFecha(resultSet.getString(4)).withDia(resultSet.getString(5)).createPronosticoExtendido();
                list.add(p);
            }

            resultSet.close();
            st.close();


        } catch (SQLException e) {
            e.printStackTrace();
        }


        return  list;
    }


}
