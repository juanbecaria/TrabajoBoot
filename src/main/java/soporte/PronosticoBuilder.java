package soporte;

import negocio.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by juanb on 10/6/2016.
 */@Component
public class PronosticoBuilder {
    private Viento viento;
    private Atmosfera atmosfera;
    private DiaActual diaActual;
    private List<PronosticoExtendido> pronosticoExtendido;
    private Localidad localidad;

    public PronosticoBuilder(){
        viento = (new VientoBuilder()).createViento();
        atmosfera= (new AtmosferaBuilder()).createAtmosfera();
        diaActual = (new DiaActualBuilder().createDiaActual());
        pronosticoExtendido = new ArrayList<PronosticoExtendido>();
        pronosticoExtendido.add((new PronosticoExtendidoBuilder()).createPronosticoExtendido());
        localidad = (new LocalidadBuilder()).createLocalidad();

    }

    public PronosticoBuilder withViento(Viento viento) {
        this.viento = viento;
        return this;
    }


    public PronosticoBuilder withAtmosfera(Atmosfera atmosfera) {
        this.atmosfera = atmosfera;
        return this;
    }


    public PronosticoBuilder withDiaActual(DiaActual diaActual) {
        this.diaActual = diaActual;
        return this;
    }


    public PronosticoBuilder withPronosticoExtendido(List<PronosticoExtendido> pronosticoExtendido) {
        this.pronosticoExtendido = pronosticoExtendido;
        return this;
    }


    public PronosticoBuilder withLocalidad(Localidad localidad) {
        this.localidad = localidad;
        return this;
    }


    public Pronostico createPronostico(){
        return new Pronostico(viento,atmosfera,diaActual,pronosticoExtendido,localidad);
    }
}
