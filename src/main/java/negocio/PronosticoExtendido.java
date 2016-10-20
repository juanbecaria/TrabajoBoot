package negocio;

import org.springframework.stereotype.Component;



public class PronosticoExtendido implements Comparable<PronosticoExtendido> {

    private String fecha;
    private float tempMin;
    private float tempMax;
    private String descripcion;
    private String dia;


    public PronosticoExtendido(String fecha, float tempMin, float tempMax, String dia, String desc) {
        this.fecha = fecha;
        this.tempMin = tempMin;
        this.tempMax = tempMax;
        this.dia = dia;
        descripcion= desc;
    }

    public String getFecha() {
        return fecha;
    }

    public float getTempMin() {
        return tempMin;
    }

    public float getTempMax() {
        return tempMax;
    }

    public String getDescripcion(){return descripcion;}

    public String getDia() { return dia; }

    @Override
    public String toString() {
        return "PronosticoExtendido{" +
                "fecha= " + fecha +
                ", tempMin= " + tempMin +
                ", tempMax= " + tempMax +
                ", descripcion=' " + descripcion + '\'' +
                ", dia= " + dia +
                '}';
    }

    public int compareTo(PronosticoExtendido o) {
        double res= 0;
        res= fecha.compareTo(o.getFecha());
        if(res==0){
            res= tempMin-o.getTempMin();
            if(res==0){
                res=tempMax-o.getTempMax();
                if(res==0){
                    res=descripcion.compareTo(o.getDescripcion());
                    if(res==0){
                        res=dia.toString().compareTo(o.getDia());
                        if(res==0){
                            return 0;
                        }
                    }
                }
            }
        }
        return 1;
    }
}
