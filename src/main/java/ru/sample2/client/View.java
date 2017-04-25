package ru.sample2.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.maps.client.MapOptions;
import com.google.gwt.maps.client.MapTypeId;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.base.LatLng;
import com.google.gwt.maps.client.overlays.InfoWindow;
import com.google.gwt.maps.client.overlays.InfoWindowOptions;
import com.google.gwt.maps.client.overlays.Marker;
import com.google.gwt.maps.client.overlays.MarkerOptions;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import ru.sample2.client.model.TextModel;
import ru.sample2.shared.Route;

import javax.inject.Inject;

/**
 * Created by Anna on 02.01.2017.
 */
public class View {
    private EventBus eventBus;
    private Presenter presenter = new Presenter(new TextModel(), eventBus, this);

    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    final SuggestBox suggestbox = new SuggestBox(new RemoteSuggestOracle(eventBus, (EndPoint) GWT.create(EndPoint.class)));
    final Label label = new Label("No selected!");
    final Button addRoute = new Button("Add route");
    final Button findRoute = new Button("Find route");
    MapWidget map;
    InfoWindow infoWin;
    Marker marker;

    @Inject
    View(EventBus eventbus) {
        this.eventBus = eventbus;
//        this.presenter = presenter;
    }

    public void createView() {

        suggestbox.setStyleName("gwt-SuggestBox");
        HorizontalPanel hp = new HorizontalPanel();
        VerticalPanel vp1 = new VerticalPanel();
        VerticalPanel vp2 = new VerticalPanel();
        VerticalPanel vp3 = new VerticalPanel();

        LatLng center = LatLng.newInstance(55.45, 37.361);
        MapOptions opts = MapOptions.newInstance();
        opts.setZoom(8);
        opts.setCenter(center);
        opts.setMapTypeId(MapTypeId.ROADMAP);
        opts.setMapMaker(true);
        map = new MapWidget(opts);
        map.setSize("500px", "500px");

        MarkerOptions markerOptions = MarkerOptions.newInstance();
        markerOptions.setTitle("Street");
        InfoWindowOptions infoWindowOptions = InfoWindowOptions.newInstance();
        marker = Marker.newInstance(markerOptions);
        marker.setPosition(center);
        marker.setMap(map);
        infoWin = InfoWindow.newInstance(infoWindowOptions);
        infoWin.setPosition(center);
        infoWin.open(map);

        vp1.add(suggestbox);
        vp1.add(label);
        vp2.add(map);
        vp1.add(vp3);
        vp3.add(addRoute);
        vp3.add(findRoute);
        hp.add(vp1);
        hp.add(vp2);
        RootPanel.get("ListContainer").add(hp);

        suggestbox.addSelectionHandler(new SelectionHandler<SuggestOracle.Suggestion>() {
            @Override
            public void onSelection(SelectionEvent<SuggestOracle.Suggestion> event) {

                String selected = suggestbox.getValue();
                presenter.changeModel(selected);
                presenter.updateLabel();
                presenter.geolocation();
            }
        });

        addRoute.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                final DialogBox dialogBox = new DialogBox();
                final VerticalPanel dialogVPanel = new VerticalPanel();
                final HorizontalPanel panel = new HorizontalPanel();
                final Label title = new Label("Add route");
                final Button okButton = new Button("Yes");
                Button noButton = new Button("No");
                final TextBox startStopBox = new TextBox();
                final TextBox endStopBox = new TextBox();
                final TextBox dayWeekBox = new TextBox();
                final TextBox timeBox = new TextBox();
                final HorizontalPanel horizontalPanel = new HorizontalPanel();

//                dialogBox.setStyleName("dialogBox");
                dialogBox.setAnimationEnabled(true);
//                dialogVPanel.addStyleName("dialogVPanel");
                okButton.addStyleName("gwt-Button");
                noButton.addStyleName("gwt-Button");
//                title.addStyleName("gwt-LabelTitle");
                startStopBox.setText("Enter start stop");
                endStopBox.setText("Enter end stop");
                dayWeekBox.setText("Check day of week");
                timeBox.setText("Enter time");
                horizontalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
                horizontalPanel.add(panel);
                panel.add(okButton);
                panel.add(noButton);
                dialogVPanel.add(title);
                dialogVPanel.add(startStopBox);
                dialogVPanel.add(endStopBox);
                dialogVPanel.add(dayWeekBox);
                dialogVPanel.add(timeBox);
                dialogVPanel.add(horizontalPanel);
                dialogBox.setWidget(dialogVPanel);
                dialogBox.center();

                okButton.addClickHandler(new ClickHandler() {
                    public void onClick(ClickEvent event) {
                        if (checkIsNull(startStopBox.getText(), endStopBox.getText(),
                                dayWeekBox.getText(), timeBox.getText())) {
                            Route data = new Route(startStopBox.getText(), endStopBox.getText());
                                EndPoint endPoint = GWT.create(EndPoint.class);
                                endPoint.sendRoute(data, new AsyncCallbackAddRouteService());
                            dialogBox.hide();
                        } else {
                            Window.alert("Entered invalid value!");
                        }
                    }
                });

                startStopBox.addClickHandler(new ClickHandler() {
                    public void onClick(ClickEvent event) {
                        startStopBox.setText("");
                    }
                });
                endStopBox.addClickHandler(new ClickHandler() {
                    @Override
                    public void onClick(ClickEvent event) {
                        endStopBox.setText("");
                    }
                });
                dayWeekBox.addClickHandler(new ClickHandler() {
                    public void onClick(ClickEvent event) {
                        dayWeekBox.setText("");
                    }
                });
                timeBox.addClickHandler(new ClickHandler() {
                    public void onClick(ClickEvent event) {
                        timeBox.setText("");
                    }
                });
                noButton.addClickHandler(new ClickHandler() {
                    @Override
                    public void onClick(ClickEvent event) {
                        dialogBox.hide();
                    }
                });
                dialogBox.show();
            }
        });

        findRoute.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                final DialogBox dialogBox = new DialogBox();
                final VerticalPanel dialogVPanel = new VerticalPanel();
                final HorizontalPanel panel = new HorizontalPanel();
                final Label title = new Label("Find route");
                final Button okButton = new Button("Yes");
                Button noButton = new Button("No");
                final TextBox startStopBox = new TextBox();
                final TextBox endStopBox = new TextBox();
                final HorizontalPanel horizontalPanel = new HorizontalPanel();

//                dialogBox.setStyleName("dialogBox");
                dialogBox.setAnimationEnabled(true);
//                dialogVPanel.addStyleName("dialogVPanel");
                okButton.addStyleName("gwt-Button");
                noButton.addStyleName("gwt-Button");
//                title.addStyleName("gwt-LabelTitle");
                startStopBox.setText("Enter start stop");
                endStopBox.setText("Enter end stop");
                horizontalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
                horizontalPanel.add(panel);
                panel.add(okButton);
                panel.add(noButton);
                dialogVPanel.add(title);
                dialogVPanel.add(startStopBox);
                dialogVPanel.add(endStopBox);

                dialogVPanel.add(horizontalPanel);
                dialogBox.setWidget(dialogVPanel);
                dialogBox.center();

                okButton.addClickHandler(new ClickHandler() {
                    public void onClick(ClickEvent event) {
                        if (checkIsNull(startStopBox.getText(), endStopBox.getText())) {
//                            Route data = new Route(Integer.parseInt(numberBox.getText()), startStopBox.getText(),
//                                    dayWeekBox.getText(), curStopBox.getText(), timeBox.getText());
//
//                            MySampleApplicationService.App.getInstance().addRoute(data, new MyAsyncCallback());
                            dialogBox.hide();
                        } else {
                            Window.alert("Entered invalid value!");
                        }


                    }
                });

                startStopBox.addClickHandler(new ClickHandler() {
                    public void onClick(ClickEvent event) {
                        startStopBox.setText("");
                    }
                });
                endStopBox.addClickHandler(new ClickHandler() {
                    @Override
                    public void onClick(ClickEvent event) {
                        endStopBox.setText("");
                    }
                });

                noButton.addClickHandler(new ClickHandler() {
                    @Override
                    public void onClick(ClickEvent event) {
                        dialogBox.hide();
                    }
                });
                dialogBox.show();

            }
        });
    }
        public boolean checkIsNull (String startStop, String endStop, String dayWeek, String time){
            if (startStop.equals("") || endStop.equals("") || dayWeek.equals("") || time.equals("")) return false;
            return true;
        }

        public boolean checkIsNull (String startStop, String endStop){
            if (startStop.equals("") || endStop.equals("")) return false;
            return true;
        }

}