package miInterfaz;

import web.Proxy;

/**
 * Created by juanb on 10/19/2016.
 */
public class Main {
    public static void main(String[] args) {


        Proxy prox = new Proxy();

        String str =prox.pedir("cordoba","Cba");

        System.out.println(str);

    }
}
