package negocio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by juanb on 9/26/2016.
 */

public class Pronostico implements Comparable<Pronostico> {
    @Autowired
    private Viento viento;
    @Autowired
    private Atmosfera atmosfera;
    @Autowired
    private DiaActual diaActual;
    @Autowired
    private List<PronosticoExtendido> pronosticoExtendido;
    @Autowired
    private Localidad localidad;

    public Pronostico(Viento viento, Atmosfera atmosfera, DiaActual diaActual, List<PronosticoExtendido> pronosticoExtendido, Localidad localidad) {
        this.viento = viento;
        this.atmosfera = atmosfera;
        this.diaActual = diaActual;
        this.pronosticoExtendido = pronosticoExtendido;
        this.localidad = localidad;
    }

    public Viento getViento() {
        return viento;
    }

    public Atmosfera getAtmosfera() {
        return atmosfera;
    }

    public DiaActual getDiaActual() {
        return diaActual;
    }

    public List<PronosticoExtendido> getPronosticoExtendido() {
        return pronosticoExtendido;
    }

    public Localidad getLocalidad() {
        return localidad;
    }

    @Override
    public String toString() {

        StringBuffer sb = new StringBuffer("Pronostico{ ");
        sb.append(" Localidad=" + localidad);
        sb.append(", DiaActual= " + diaActual );
        sb.append(", Viento=" + viento );
        sb.append(", Atmosfera= " + atmosfera );
        sb.append(", Pronostico Extendido= " + pronosticoExtendido );
        sb.append('}');

        return sb.toString();

    }

    public int compareTo(Pronostico o) {

        if(viento.compareTo(o.getViento())==0){
            if(atmosfera.compareTo(o.getAtmosfera())==0){
               if(diaActual.compareTo(o.getDiaActual())==0) {
                   if(localidad.compareTo(o.getLocalidad())==0){
                       int i=0;
                       for (PronosticoExtendido x :pronosticoExtendido) {
                           if(o.getPronosticoExtendido().contains(x)){
                               i++;
                           }


                       }
                       if(i==pronosticoExtendido.size()){
                           return 0;
                       }

                   }


               }
            }

        }
        return 1;

    }
}
