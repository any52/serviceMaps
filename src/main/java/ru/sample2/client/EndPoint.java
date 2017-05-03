package ru.sample2.client;

import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;
import ru.sample2.shared.RouteDTO;
import ru.sample2.shared.SuggestionDTO;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;


/**
 * The client side stub for the RPC service.
 */
@Path("/api/serviceMap")
public interface EndPoint extends RestService {

    @GET
    @Path("/{address}")

    void getAddressesList(@PathParam("address") String adress, MethodCallback<SuggestionDTO> callback);

    @GET
    @Path("/addRoute/{startpoint}/{endpoint}/{intermediatePoint1}/{intermediatePoint2}/{dayWeek}/{time}")
     void sendRoute(@PathParam("startpoint") String startPoint,
                    @PathParam("endpoint") String endPoint,
                    @PathParam("intermediatePoint1") String intermediatePoint1,
                    @PathParam("intermediatePoint2") String intermediatePoint2,
                    @PathParam("dayWeek") String dayweek,
                    @PathParam("time") String time,
                    MethodCallback<RouteDTO> callback);

    @GET
    @Path("/getRoute")
    void getRoute(MethodCallback<RouteDTO> callback);

    @GET
    @Path("/findRoute/{startpoint}/{endpoint}")
    void findRoute(@PathParam("startpoint") String startPoint,
                   @PathParam("endpoint") String endPoint,
                   MethodCallback<RouteDTO> callback);

    @DELETE
    @Path("/deleteRoute/{startpoint}/{endpoint}/{dayWeek}/{time}")
    void deleteRoute(@PathParam("startpoint") String startPoint,
                     @PathParam("endpoint") String endPoint,
                    @PathParam("dayWeek") String dayweek,
                     @PathParam("time") String time,
                     MethodCallback<RouteDTO> callback);

}
