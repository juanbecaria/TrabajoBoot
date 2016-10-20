package interfaz;

import org.springframework.beans.factory.annotation.Autowired;

import web.Proxy;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;


@Path("/salida")
public class Salida {


    @Autowired
    private Proxy proxy ;

    @GET
    @Path("/echo/{input}")
    @Produces("text/plain")
    public String ping(@PathParam("input") String input) {
        return input;
    }

    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("/jsonBean")
    public Response modifyJson(JsonBean input) {
        input.setVal2(input.getVal1());
        return Response.ok().entity(input).build();
    }

    @GET
    @Path("/echo/{ciudad}/{region}")
    @Produces("text/plain")
    public String ping(@PathParam("ciudad") String ciudad,@PathParam("region") String region) {

        return proxy.pedir(ciudad,region);
    }
}

