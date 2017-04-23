package ru.sample2.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface TextModelChangedEventHandler extends EventHandler
{
    void onTextModelChanged(TextModelChangedEvent event);
}
