package ru.sample2.server;


import ru.sample2.shared.Route;
import ru.sample2.shared.RouteDTO;
import ru.sample2.shared.SuggestionDTO;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;


/**
 * The server side implementation of the Rest service.
 */

@Path("/country")
public class EndPointImpl {

  private static final long serialVersionUID = 1L;
  private AddressesRepository addressesRepository;
  private RouteRepository routeRepository;

  @Inject
  public EndPointImpl(AddressesRepository addressesRepository, RouteRepository routeRepository) {
    super();
    this.addressesRepository = addressesRepository;
    this.routeRepository = routeRepository;
  }

  @GET
  @Path("/{country}")
  @Produces("application/json")
  public SuggestionDTO getAddressesList(@PathParam("country") final String input)  {

    return addressesRepository.getAddressesList(input);

  }

  @GET
  @Path("/getRoute/{route}")
  @Produces("application/json")
  public RouteDTO sendRoute(@PathParam("route")Route route){
    return routeRepository.sendRoute(route);
  }
}
