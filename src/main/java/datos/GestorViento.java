package datos;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import negocio.Localidad;
import negocio.Viento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import soporte.VientoBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by juanb on 9/28/2016.
 */
@Repository
public class GestorViento {

   @Autowired
   private DBConnection dbConnection;

   public void guardar(Localidad loca, String fecha, Viento vien){

      String insert= "INSERT INTO Viento (fecha, ciudad, pais, region, velocidad, direccion) VALUES (?, ?, ?, ?, ?, ?) ";

      try {

         Connection con = dbConnection.getConnection();


         PreparedStatement st = con.prepareStatement(insert);
         st.setString(1,fecha);
         st.setString(2,loca.getCiudad());
         st.setString(3,loca.getPais());
         st.setString(4,loca.getRegion());
         st.setFloat(5,vien.getVelocidad());
         st.setFloat(6,vien.getDireccion());


         st.execute();
         st.close();


      }catch (SQLServerException e){
         System.out.println(e.getMessage());

      }
      catch (SQLException e) {
         e.printStackTrace();
      }


   }
   public Viento buscarPorId(Localidad l, String fecha){
      Viento v= null;

      Connection con = null;
      try {
         con = dbConnection.getConnection();
         String search= "SELECT direccion, velocidad FROM Viento WHERE ciudad=? AND region=? AND pais=? AND fecha=?";

         PreparedStatement st = con.prepareStatement(search);

         st.setString(1,l.getCiudad());
         st.setString(2,l.getRegion());
         st.setString(3,l.getPais());
         st.setString(4,fecha);

         ResultSet resultSet = st.executeQuery();


         v = new VientoBuilder().withDireccion(resultSet.getFloat(1)).withVelocidad(resultSet.getFloat(2)).createViento();

         resultSet.close();
         st.close();


      } catch (SQLException e) {
         e.printStackTrace();
      }


      return  v;
   }



}
