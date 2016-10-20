package web;

import datos.GestorPronostico;
import negocio.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import soporte.*;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



/**
 * Created by juanb on 10/18/2016.
 */
@Component
public class Proxy {


@Autowired
    private AtmosferaBuilder atmosferaBuilder ;
@Autowired
    private DiaActualBuilder diaActualBuilder ;
@Autowired
    private LocalidadBuilder localidadBuilder ;
@Autowired
    private  PronosticoBuilder pronosticoBuilder ;
@Autowired
    private  PronosticoExtendidoBuilder pronosticoExtendidoBuilder ;
@Autowired
    private   VientoBuilder vientoBuilder ;
@Autowired
    private   GestorPronostico gestorPronostico;






    public static float aCelcius(float f){
        return ((f-32)*(5f/9f));
    }
    public static float aKMH(float vel){return 1.609f*vel;}


    public String pedir(String ciudad, String region){
        JSONObject json = null;
        try {
            json = JsonReader.readJsonFromUrl(ciudad,region);
        } catch (IOException e) {
            return  e.getMessage();
        }
        if(json!=null) {

            JSONObject channel = json.getJSONObject("query").getJSONObject("results").getJSONObject("channel");



            Atmosfera at = atmosferaBuilder.withHumedad((float)channel.getJSONObject("atmosphere").getDouble("humidity"))
                    .withPresion((float)channel.getJSONObject("atmosphere").getDouble("pressure"))
                    .withAmbienteAscendente((float)channel.getJSONObject("atmosphere").getDouble("rising"))
                    .withVisibilidad((float)channel.getJSONObject("atmosphere").getDouble("visibility")).createAtmosfera();

            Localidad l = localidadBuilder.withCiudad(channel.getJSONObject("location").getString("city"))
                    .withPais(channel.getJSONObject("location").getString("country"))
                    .withRegion(channel.getJSONObject("location").getString("region")).createLocalidad();

            Viento v = vientoBuilder.withVelocidad(aKMH((float)channel.getJSONObject("wind").getDouble("speed")))
                    .withDireccion((float)channel.getJSONObject("wind").getDouble("direction")).createViento();



            JSONArray jArray=channel.getJSONObject("item").getJSONArray("forecast");
            List<PronosticoExtendido> list=new ArrayList<>();

            DiaActual da = diaActualBuilder.withTemp(aCelcius((float)channel.getJSONObject("item").getJSONObject("condition").getDouble("temp")))
                    .withDescripcion(channel.getJSONObject("item").getJSONObject("condition").getString("text"))
                    .withFecha(jArray.getJSONObject(0).getString("date")).createDiaActual();


            for(int i = 1; i<jArray.length();i++)
            {
                JSONObject jo = jArray.getJSONObject(i);
                PronosticoExtendido p = pronosticoExtendidoBuilder.withDescripcion(jo.getString("text"))
                        .withDia(jo.getString("day"))
                        .withFecha(jo.getString("date"))
                        .withTempMax(aCelcius((float)jo.getDouble("high")))
                        .withTempMin(aCelcius((float)jo.getDouble("low"))).createPronosticoExtendido();
                list.add(p);
            }

            Pronostico p = pronosticoBuilder.withAtmosfera(at)
                    .withDiaActual(da)
                    .withViento(v)
                    .withLocalidad(l)
                    .withPronosticoExtendido(list).createPronostico();
            /*guardar(p);*/

            return p.toString();
        }
        return "nope";
    }

    public  void guardar(Pronostico p){


            gestorPronostico.guardar(p);





    }



}

