package ru.sample2.client;

import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;
import ru.sample2.shared.Route;
import ru.sample2.shared.RouteDTO;
import ru.sample2.shared.SuggestionDTO;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;


/**
 * The client side stub for the RPC service.
 */
@Path("/api/country")
public interface EndPoint extends RestService {

    @GET
    @Path("/{country}")

    void getAddressesList(@PathParam("country") String request, MethodCallback<SuggestionDTO> callback);

    @GET
    @Path("/getRoute/{route}")
     void sendRoute(@PathParam("route") Route route, MethodCallback<RouteDTO> callback);

}
