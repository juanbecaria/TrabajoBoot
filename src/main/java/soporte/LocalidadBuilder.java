package soporte;

import negocio.Localidad;
import org.springframework.stereotype.Component;

/**
 * Created by juanb on 10/6/2016.
 */@Component
public class LocalidadBuilder {
    private String ciudad;
    private String region;
    private String pais;

    public LocalidadBuilder(){
        ciudad="Pilar";
        region="Buenos Aires";
        pais="Argentina";

    }

    public LocalidadBuilder withCiudad(String ciudad) {
        this.ciudad = ciudad;
        return this;
    }


    public LocalidadBuilder withRegion(String region) {
        this.region = region;
        return this;
    }


    public LocalidadBuilder withPais(String pais) {
        this.pais = pais;
        return this;
    }


    public Localidad createLocalidad(){
        return new Localidad(ciudad,region,pais);
    }
}
