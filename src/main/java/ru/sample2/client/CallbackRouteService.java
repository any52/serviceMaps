package ru.sample2.client;

import com.google.gwt.user.client.Window;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;
import ru.sample2.shared.RouteDTO;

/**
 * Created by Anna on 19.04.2017.
 */
public class CallbackRouteService implements MethodCallback<RouteDTO> {
//    EventBus eventBus;
//    EndPoint addRoute;

//    @Inject
    public CallbackRouteService() {
//        this.eventBus = eventBus;
//        this.addRoute = addRoute;
    }

    @Override
    public void onFailure(Method method, Throwable throwable) {
        Window.alert("Route did not add!");
    }

    @Override
    public void onSuccess(Method method, RouteDTO routeDTO) {
        Window.alert("Route added succsesefully!");
    }

}
