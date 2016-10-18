package web;

import negocio.Atmosfera;
import negocio.DiaActual;
import negocio.Pronostico;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import soporte.*;


import java.io.IOException;

/**
 * Created by juanb on 10/18/2016.
 */
@Component
public class Proxy {
    private AtmosferaBuilder atmosferaBuilder;
    private DiaActualBuilder diaActualBuilder;




    public static float aCelcius(float f){
        return 5/9*(f-32);
    }


    public static String pedir(String ciudad, String region){
        JSONObject json = null;
        try {
            json = JsonReader.readJsonFromUrl(ciudad,region);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(json!=null) {
            JSONObject channel = json.getJSONObject("query").getJSONObject("results").getJSONObject("channel");


        }
        return "nope";
    }

    public static boolean guardar(Pronostico p){
        boolean ban = false;


        return ban;

    }



}
