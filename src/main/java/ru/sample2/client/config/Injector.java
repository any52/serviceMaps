package ru.sample2.client.config;

import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import ru.sample2.client.View;

/**
 * Created by Anna on 18.01.2017.
 */
@GinModules(MyGinModule.class)
public interface Injector extends Ginjector {

    View getView();

}
