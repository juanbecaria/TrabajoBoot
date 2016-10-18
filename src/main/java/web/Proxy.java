package web;

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

    private static AtmosferaBuilder atmosferaBuilder = new AtmosferaBuilder();

    private static DiaActualBuilder diaActualBuilder = new DiaActualBuilder();

    private static LocalidadBuilder localidadBuilder = new LocalidadBuilder();

    private static PronosticoBuilder pronosticoBuilder = new PronosticoBuilder();

    private static PronosticoExtendidoBuilder pronosticoExtendidoBuilder = new PronosticoExtendidoBuilder();

    private  static VientoBuilder vientoBuilder = new VientoBuilder();





    public static float aCelcius(float f){
        return 5/9*(f-32);
    }


    public static String pedir(String ciudad, String region){
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

            Viento v = vientoBuilder.withVelocidad((float)channel.getJSONObject("wind").getDouble("speed"))
                    .withDireccion((float)channel.getJSONObject("wind").getDouble("direction")).createViento();



            JSONArray jArray=channel.getJSONObject("item").getJSONArray("forecast");
            List<PronosticoExtendido> list=new ArrayList<>();

            DiaActual da = diaActualBuilder.withTemp((float)channel.getJSONObject("item").getJSONObject("condition").getDouble("temp"))
                    .withDescripcion(channel.getJSONObject("item").getJSONObject("condition").getString("text"))
                    .withFecha(jArray.getJSONObject(0).getString("date")).createDiaActual();


            for(int i = 1; i<jArray.length();i++)
            {
                JSONObject jo = jArray.getJSONObject(i);
                PronosticoExtendido p = pronosticoExtendidoBuilder.withDescripcion(jo.getString("text"))
                        .withDia(jo.getString("day"))
                        .withFecha(jo.getString("date"))
                        .withTempMax((float)jo.getDouble("high"))
                        .withTempMin((float)jo.getDouble("low")).createPronosticoExtendido();
                list.add(p);
            }

            Pronostico p = pronosticoBuilder.withAtmosfera(at)
                    .withDiaActual(da)
                    .withViento(v)
                    .withLocalidad(l)
                    .withPronosticoExtendido(list).createPronostico();

            return p.toString();
        }
        return "nope";
    }

    public static boolean guardar(Pronostico p){
        boolean ban = false;


        return ban;

    }



}

