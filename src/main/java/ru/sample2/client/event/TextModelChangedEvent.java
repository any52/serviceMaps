package ru.sample2.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class TextModelChangedEvent extends GwtEvent<TextModelChangedEventHandler>
{
    public static final Type<TextModelChangedEventHandler> TYPE = new Type<>();

    @Override
    public Type<TextModelChangedEventHandler> getAssociatedType()
    {
        return TYPE;
    }

    @Override
    protected void dispatch(TextModelChangedEventHandler handler)
    {
        handler.onTextModelChanged(this);
    }
}
