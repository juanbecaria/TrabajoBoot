package soporte;

import negocio.Viento;
import org.springframework.stereotype.Component;

/**
 * Created by juanb on 10/6/2016.
 */
@Component
public class VientoBuilder {
    private float direccion;
    private float velocidad;

    public VientoBuilder(){
        direccion=45;
        velocidad=20;

    }

    public VientoBuilder withDireccion(float direccion) {
        this.direccion = direccion;
        return this;
    }


    public VientoBuilder withVelocidad(float velocidad) {
        this.velocidad = velocidad;
        return this;
    }


    public Viento createViento(){
        return new Viento(direccion,velocidad);
    }


}
