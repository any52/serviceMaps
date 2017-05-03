package ru.sample2.server;


import org.fusesource.restygwt.client.MethodCallback;
import ru.sample2.server.service.AddressesRepository;
import ru.sample2.server.service.RouteRepository;
import ru.sample2.shared.RouteDTO;
import ru.sample2.shared.SuggestionDTO;

import javax.inject.Inject;
import javax.ws.rs.*;


/**
 * The server side implementation of the Rest service.
 */

@Path("/serviceMap")
public class EndPointImpl {

  private AddressesRepository addressesRepository;
  private RouteRepository routeRepository;

  @Inject
  public EndPointImpl(AddressesRepository addressesRepository, RouteRepository routeRepository) {
    super();
    this.addressesRepository = addressesRepository;
    this.routeRepository = routeRepository;
  }

  @GET
  @Path("/{address}")
  @Produces("application/json")
  public SuggestionDTO getAddressesList(@PathParam("address") final String input)  {

    return addressesRepository.getAddressesList(input);

  }

  @GET
  @Path("/addRoute/{startpoint}/{endpoint}/{intermediatePoint1}/{intermediatePoint2}/{dayWeek}/{time}")
  @Produces("application/json")
  public RouteDTO sendRoute(@PathParam("startpoint") String startPoint,
                            @PathParam("endpoint") String endPoint,
                            @PathParam("intermediatePoint1") String intermediatePoint1,
                            @PathParam("intermediatePoint2") String intermediatePoint2,
                            @PathParam("dayWeek") String dayweek,
                            @PathParam("time") String time){
    return routeRepository.addRoute(startPoint, endPoint, intermediatePoint1,
            intermediatePoint2, dayweek, time);
  }
  @GET
  @Path("/getRoute")
  @Produces("application/json")
  public RouteDTO getRoute(MethodCallback<RouteDTO> callback) {
    return routeRepository.sendRoute();
  }

  @DELETE
  @Path("/deleteRoute/{startpoint}/{endpoint}/{dayWeek}/{time}")
  @Produces("application/json")
  public RouteDTO deleteRoute(@PathParam("startpoint") String startPoint,
                              @PathParam("endpoint") String endPoint,
                              @PathParam("dayWeek") String dayweek,
                              @PathParam("time") String time) {
    return routeRepository.deleteRoute(startPoint,endPoint, dayweek, time);
  }

  @GET
  @Path("/findRoute/{startpoint}/{endpoint}")
  @Produces("application/json")
  public RouteDTO findRoute(@PathParam("startpoint")String startPoint,
                            @PathParam("endpoint") String endPoint) {
    return routeRepository.findRoutes(startPoint,endPoint);
  }
}
