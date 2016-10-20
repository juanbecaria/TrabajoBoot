package datos;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import negocio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import soporte.PronosticoBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by juanb on 9/28/2016.
 */
@Repository
public class GestorPronostico {


    @Autowired
    private GestorAtmosfera gestorAtmosfera;
    @Autowired
    private GestorPronosticoExtendido gestorPronosticoExtendido;
    @Autowired
    private GestorDiaActual gestorDiaActual;
    @Autowired
    private GestorViento gestorViento;
    @Autowired
    private GestorLocalidad gestorLocalidad;



    public void guardar(Pronostico pro){


        String declaracion = "INSERT INTO Pronostico (fecha, ciudad, pais, region) VALUES (?,?,?,?)";

        try {


            Connection con = DBConnection.getInstance().getConnection();
            con.setAutoCommit(false);
            String fecha= pro.getDiaActual().getFecha();


            gestorLocalidad.guardar(pro.getLocalidad());

            PreparedStatement pst = con.prepareStatement(declaracion);
            pst.setString(1,fecha);
            pst.setString(2,pro.getLocalidad().getCiudad());
            pst.setString(3,pro.getLocalidad().getPais());
            pst.setString(4,pro.getLocalidad().getRegion());
            pst.execute();
            pst.close();




            gestorAtmosfera.guardar( pro.getLocalidad(),fecha, pro.getAtmosfera());


            gestorDiaActual.guardar( pro.getLocalidad(),pro.getDiaActual());




            gestorPronosticoExtendido.guardar(pro.getLocalidad(),fecha,pro.getPronosticoExtendido());


            gestorViento.guardar(pro.getLocalidad(),fecha,pro.getViento());


            con.commit();
            con.close();


        }
        catch (SQLServerException e)
        {System.out.println(e.getMessage());

        } catch (SQLException e) {
            e.printStackTrace();
        }





    }

    public Pronostico buscarPorId(Localidad l, String fecha){
        Pronostico p = null;

        Connection con = null;
        try {
            con = DBConnection.getInstance().getConnection();
            String search= "SELECT fecha FROM Pronostico WHERE ciudad=? AND region=? AND pais=? AND fecha=?";

            PreparedStatement st = con.prepareStatement(search);

            st.setString(1,l.getCiudad());
            st.setString(2,l.getRegion());
            st.setString(3,l.getPais());
            st.setString(4,fecha);

            ResultSet resultSet = st.executeQuery();

            if(resultSet.getString(1)!=null) {
                GestorAtmosfera ga =new GestorAtmosfera();
                Atmosfera at = ga.buscarPorId(l,fecha);

                GestorViento gv = new GestorViento();
                Viento v = gv.buscarPorId(l, fecha);

                GestorDiaActual gda= new GestorDiaActual();
                DiaActual da = gda.buscarPorId(l,fecha);

                GestorPronosticoExtendido gpe= new GestorPronosticoExtendido();
                List<PronosticoExtendido> pe = gpe.buscarPorId(l,fecha);



                p = new PronosticoBuilder().withAtmosfera(at).withDiaActual(da).withLocalidad(l).withViento(v).withPronosticoExtendido(pe).createPronostico();
            }

            resultSet.close();
            st.close();


        } catch (SQLException e) {
            e.printStackTrace();
        }


        return  p;
    }



}
