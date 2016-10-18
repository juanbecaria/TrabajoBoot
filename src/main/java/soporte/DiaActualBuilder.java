package soporte;

import negocio.DiaActual;
import org.springframework.stereotype.Component;

/**
 * Created by juanb on 10/6/2016.
 */@Component
public class DiaActualBuilder {

    private String fecha;
    private float temp;
    private String descripcion;

    public DiaActualBuilder(){
        fecha = "30/02/1980" ;
        temp=30;
        descripcion="Nublado";

    }

    public DiaActualBuilder withFecha(String fecha) {
        this.fecha = fecha;
        return this;
    }


    public DiaActualBuilder withTemp(float temp) {
        this.temp = temp;
        return this;
    }


    public DiaActualBuilder withDescripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }

    public DiaActual createDiaActual(){
        return new DiaActual(fecha,temp,descripcion);
    }
}
