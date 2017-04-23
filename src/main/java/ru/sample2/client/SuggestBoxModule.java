package ru.sample2.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import ru.sample2.client.config.Injector;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class SuggestBoxModule implements EntryPoint {

  public void onModuleLoad() {

    Injector injector = GWT.create(Injector.class);
    View view = injector.getView();
    view.createView();


  }
}
