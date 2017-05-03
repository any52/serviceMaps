package ru.sample2.client;

import com.google.gwt.core.client.JsArray;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.maps.client.base.LatLng;
import com.google.gwt.maps.client.overlays.Marker;
import com.google.gwt.maps.client.services.*;
import com.google.gwt.user.client.Window;
import ru.sample2.client.model.TextModel;

import javax.inject.Inject;

/**
 * Created by Anna on 01.04.2017.
 */
public class Presenter {

    TextModel model;
    EventBus eventBus;
    View view;

    @Inject
    public Presenter(TextModel model, EventBus eventBus, View view) {
        this.model = model;
        this.eventBus = eventBus;
        this.view = view;
    }

    public void changeModel(String value) {
        model.updateText(value);
    }

    public void updateLabel() {
        view.label.setText("You choosed: " + model.getText());

    }
    public  void geolocationForAddressBox() {
        String address = "Россия, Нижний Новгород, " + model.getText();
        geolocation(address, view.markerStartPoint);
        geolocation(address, view.markerEndPoint);
    }
    public void geolocation(final String address, final Marker marker) {

        GeocoderRequest request = GeocoderRequest.newInstance();
        request.setAddress(address);
        final Geocoder geoCoder = Geocoder.newInstance();
        geoCoder.geocode(request, new GeocoderRequestHandler() {
            @Override
            public void onCallback(JsArray<GeocoderResult> jsArray, GeocoderStatus geocoderStatus) {
                if (geocoderStatus.equals(GeocoderStatus.OK)) {
                    GeocoderResult result = jsArray.shift();
                    LatLng cootdinates = result.getGeometry().getLocation();
                    view.map.setCenter(cootdinates);
                    marker.setPosition(cootdinates);
                    marker.setMap(view.map);

                }
                else {
                    Window.alert("Error by reason geocoding");
                }
            }
        });
    }

    public void geolocationForRoute(String startPoint, String endPoint, Marker markerSrartPoint, Marker markerEndPoint) {
        String addressStartPoint = "Россия, Нижний Новгород, " + startPoint;
        String addressEndPoint = "Россия, Нижний Новгород, " + endPoint;
        geolocation(addressStartPoint, markerSrartPoint);
        geolocation(addressEndPoint, markerEndPoint);
//        view.infoWinStartPoint.close();
//        view.infoWinEndPoint.close();
//        view.infoWinStartPoint.setContent(startPoint);
//        view.infoWinEndPoint.setContent(endPoint);
//        view.infoWinStartPoint.open(view.map);
//        view.infoWinEndPoint.open(view.map);
    }
}
