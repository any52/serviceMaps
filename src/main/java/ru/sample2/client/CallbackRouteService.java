package ru.sample2.client;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.view.client.ListDataProvider;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;
import ru.sample2.shared.Route;
import ru.sample2.shared.RouteDTO;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anna on 19.04.2017.
 */
public class CallbackRouteService implements MethodCallback<RouteDTO> {
    EventBus eventBus;
    View view;
    private List<Route> ROUTES = new ArrayList();

    @Inject
    public CallbackRouteService(EventBus eventBus, View view) {
        this.eventBus = eventBus;
        this.view = view;
    }

    @Override
    public void onFailure(Method method, Throwable throwable) {
        Window.alert("Route did not added!");
    }

    @Override
    public void onSuccess(Method method, RouteDTO result) {
        ROUTES = result.getRoutes();

        // Push the data into the widget.
        view.table.setRowData(0, ROUTES);

        ListDataProvider<Route> dataProvider = new ListDataProvider<Route>();
        dataProvider.addDataDisplay(view.table);
        dataProvider.setList(ROUTES);
        Window.alert("Route added succsesefully!" + result.getRoutes().toString());
    }

}
