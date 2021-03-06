package negocio;

import org.springframework.stereotype.Component;

/**
 * Created by juanb on 9/26/2016.
 */
@Component
public class DiaActual implements Comparable<DiaActual> {
    private String fecha;
    private float temp;
    private String descripcion;


    public DiaActual(String fec, float temp, String descripcion) {
        fecha = fec;
        this.temp = temp;
        this.descripcion = descripcion;
    }

    public float getTemperatura() {
        return temp;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    @Override
    public String toString() {
        return "DiaActual{" +
                "Fecha= "+fecha +
                "Temp= " + temp + "F" +
                ", Descripcion='" + descripcion + '\'' +
                '}';
    }

    public int compareTo(DiaActual o) {
        float res =0;
        res = fecha.compareTo(o.getFecha());
        if(res==0){
            res=temp-o.getTemperatura();
            if(res==0){
                res= descripcion.compareTo(o.getDescripcion());
                if(res==0){
                    return 0;
                }
            }
        }
        return 1;
    }
}
