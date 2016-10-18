package soporte;

import negocio.PronosticoExtendido;
import org.springframework.stereotype.Component;

/**
 * Created by juanb on 10/6/2016.
 */
@Component
public class PronosticoExtendidoBuilder {

    private String fecha;
    private float tempMin;
    private float tempMax;
    private String descripcion;
    private String dia;

    public PronosticoExtendidoBuilder(){
        fecha= "30/02/1980" ;
        tempMin=15;
        tempMax=27;
        descripcion="soleado";
        dia = "Monday";

    }

    public PronosticoExtendidoBuilder withFecha(String fecha) {
        this.fecha = fecha;
        return this;
    }


    public PronosticoExtendidoBuilder withTempMin(float tempMin) {
        this.tempMin = tempMin;
        return this;
    }

    public PronosticoExtendidoBuilder withTempMax(float tempMax) {
        this.tempMax = tempMax;
        return this;
    }

    public PronosticoExtendidoBuilder withDescripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }

    public PronosticoExtendidoBuilder withDia(String dia) {
        this.dia = dia;
        return this;
    }

    public PronosticoExtendido createPronosticoExtendido(){
        return new PronosticoExtendido(fecha,tempMin,tempMax,dia,descripcion);
    }


}
