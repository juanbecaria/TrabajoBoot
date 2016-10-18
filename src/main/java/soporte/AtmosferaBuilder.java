package soporte;

import negocio.Atmosfera;
import org.springframework.stereotype.Component;

/**
 * Created by juanb on 10/6/2016.
 */
@Component
public class AtmosferaBuilder {

    private float presion;
    private float visibilidad;
    private float humedad;
    private float ambienteAscendente;

    public AtmosferaBuilder() {
        presion=3;
        visibilidad=15;
        humedad=40;
        ambienteAscendente=0;
    }

    public AtmosferaBuilder withPresion(float presion) {
        this.presion = presion;
        return this;
    }



    public AtmosferaBuilder withVisibilidad(float visibilidad) {
        this.visibilidad = visibilidad;
        return this;
    }

    public AtmosferaBuilder withHumedad(float humedad) {
        this.humedad = humedad;
        return this;
    }



    public AtmosferaBuilder withAmbienteAscendente(float ambienteAscendente) {
        this.ambienteAscendente = ambienteAscendente;
        return this;
    }

    public Atmosfera createAtmosfera(){
        return new Atmosfera(presion,visibilidad,humedad,ambienteAscendente);
    }

}
