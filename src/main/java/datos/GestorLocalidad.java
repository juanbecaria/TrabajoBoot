package datos;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import negocio.Localidad;
import org.springframework.stereotype.Repository;
import soporte.LocalidadBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by juanb on 9/28/2016.
 */
@Repository
public  class GestorLocalidad {

    public void guardar(Localidad loca){


        String insert= "INSERT INTO Localidad (ciudad, pais, region) VALUES (?, ?, ?) ";

        try {

            Connection con = DBConnection.getInstance().getConnection();

            PreparedStatement st = con.prepareStatement(insert);
            st.setString(1,loca.getCiudad());
            st.setString(2,loca.getPais());
            st.setString(3,loca.getRegion());

            st.execute();
            st.close();


        }catch (SQLServerException e){

        }
        catch (SQLException e) {
            e.printStackTrace();
        }


    }


    public Localidad buscarPorId(Localidad l){
        Localidad loca= null;

        Connection con = null;
        try {
            con = DBConnection.getInstance().getConnection();
            String search= "SELECT ciudad, pais, region FROM Localidad WHERE ciudad=? AND region=? AND pais=?";

            PreparedStatement st = con.prepareStatement(search);

            st.setString(1,l.getCiudad());
            st.setString(2,l.getRegion());
            st.setString(3,l.getPais());


            ResultSet resultSet = st.executeQuery();


            loca = new LocalidadBuilder().withCiudad(resultSet.getString(1)).withPais(resultSet.getString(2)).withRegion(resultSet.getString(3)).createLocalidad();

            resultSet.close();
            st.close();


        } catch (SQLException e) {
            e.printStackTrace();
        }


        return  loca;
    }

}
