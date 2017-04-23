package ru.sample2.client.model;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.SimpleEventBus;
import ru.sample2.client.event.TextModelChangedEvent;
import ru.sample2.client.event.TextModelChangedEventHandler;

import javax.inject.Inject;

public class TextModel
{ private String text;
    private EventBus eventBus;

    //    private String text;
    @Inject
    public TextModel() {
        this.eventBus = new SimpleEventBus();
    }

    public HandlerRegistration addHandler(TextModelChangedEventHandler handler) {
        return eventBus.addHandler(TextModelChangedEvent.TYPE, handler);
    }

    public void updateText(String value) {
        if (!(text.equals(value))) {

            this.text = value;

        }
    }
    public void setText(String text){
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
