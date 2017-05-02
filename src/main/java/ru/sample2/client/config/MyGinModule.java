package ru.sample2.client.config;


import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.inject.client.AbstractGinModule;
import ru.sample2.client.CallbackRouteService;
import ru.sample2.client.Presenter;
import ru.sample2.client.View;
import ru.sample2.client.model.TextModel;

import javax.inject.Singleton;

/**
 * Created by Anna on 15.01.2017.
 */
public class MyGinModule extends AbstractGinModule {
    @Override
    protected void configure() {
        bind(EventBus.class).to(SimpleEventBus.class).in(Singleton.class);

        bind(Presenter.class).in(Singleton.class);

        bind(View.class);

        bind(CallbackRouteService.class);

        bind(TextModel.class).in(Singleton.class);
    }
}
