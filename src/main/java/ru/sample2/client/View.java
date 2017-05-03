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
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.NoSelectionModel;
import com.google.gwt.view.client.SelectionChangeEvent;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;
import ru.sample2.client.model.TextModel;
import ru.sample2.shared.Route;
import ru.sample2.shared.RouteDTO;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

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
    final Button getRoute = new Button("My routes");
    final Button addRoute = new Button("Add");
//    final Button updateRoute = new Button("Edit");
//    final Button deleteRoute = new Button("Delete");
    final Button findRoute = new Button("Find route");
//    final CheckBox bound = new CheckBox("Add routes around 5 kilometers.");
    final Label results = new Label("Results:");
    final static CellTable table = new CellTable();
    private int countRow =0;

    MapWidget map;
    InfoWindow infoWin;
    InfoWindow infoWinStartPoint;
    InfoWindow infoWinEndPoint;
    Marker marker;
    Marker markerStartPoint;
    Marker markerEndPoint;


    private static List<Route> ROUTES = new ArrayList();
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
        HorizontalPanel hp4  = new HorizontalPanel();

//        TextColumn<String> numberColumn = new TextColumn<String>() {
//            @Override
//            public String getValue(String object) {
//                return String.valueOf(++countRow);
//            }
//
//        };

        TextColumn<Route> startStopColumn = new TextColumn<Route>() {
            @Override
            public String getValue(Route route) {
                return route.getStartPoint();
            }
        };
        TextColumn<Route> endStopColumn = new TextColumn<Route>() {
            @Override
            public String getValue(Route route) {
                return route.getEndPoint();
            }
        };
        TextColumn<Route> intetmStop1Column = new TextColumn<Route>() {
            @Override
            public String getValue(Route route) {
                return route.getIntermediatePoint1();
            }
        };
        TextColumn<Route> intetmStop2Column = new TextColumn<Route>() {
            @Override
            public String getValue(Route route) {
                return route.getIntermediatePoint2();
            }
        };
        TextColumn<Route> dayWeekColumn = new TextColumn<Route>() {
            @Override
            public String getValue(Route route) {
                return route.getDayWeek();
            }
        };
        TextColumn<Route> timeColumn = new TextColumn<Route>() {
            @Override
            public String getValue(Route route) {
                return route.getTime();
            }
        };

        // Add the columns.

//        table.addColumn(numberColumn, "№");
        table.addColumn(startStopColumn, "Startstop");
        table.addColumn(endStopColumn, "Endstop");
        table.addColumn(intetmStop1Column, "Intermediate point1");
        table.addColumn(intetmStop2Column, "Intermediate point2");
        table.addColumn(dayWeekColumn, "Day week");
        table.addColumn(timeColumn, "Time");
        table.setRowCount(ROUTES.size(), true);

        LatLng center = LatLng.newInstance(56.32867, 44.00205);
        MapOptions opts = MapOptions.newInstance();
        opts.setZoom(10);
        opts.setCenter(center);
        opts.setMapTypeId(MapTypeId.ROADMAP);
        opts.setMapMaker(true);
        map = new MapWidget(opts);
        map.setSize("500px", "500px");

        MarkerOptions markerOptions = MarkerOptions.newInstance();
        InfoWindowOptions infoWindowOptions = InfoWindowOptions.newInstance();
        infoWindowOptions.setContent("Нижний Новгорд");
        marker = Marker.newInstance(markerOptions);
        markerStartPoint = Marker.newInstance(markerOptions);
        markerEndPoint= Marker.newInstance(markerOptions);
        marker.setPosition(center);
//        marker.setMap(map);
        infoWin = InfoWindow.newInstance(infoWindowOptions);
        infoWinStartPoint = InfoWindow.newInstance(infoWindowOptions);
        infoWinEndPoint =InfoWindow.newInstance(infoWindowOptions);
        infoWin.setPosition(center);
        infoWin.open(map);

        vp1.add(suggestbox);
        vp1.add(label);
        vp2.add(map);
        vp1.add(hp4);
        vp1.add(vp3);
        hp4.add(getRoute);
        hp4.add(addRoute);
//        vp3.add(updateRoute);
//        vp3.add(deleteRoute);
        hp4.add(findRoute);
//        vp3.add(bound);
        vp3.add(results);
        vp3.add(table);
        hp.add(vp1);
        hp.add(vp2);
        RootPanel.get("ListContainer").add(hp);

        suggestbox.addSelectionHandler(new SelectionHandler<SuggestOracle.Suggestion>() {
            @Override
            public void onSelection(SelectionEvent<SuggestOracle.Suggestion> event) {

                String selected = suggestbox.getValue();
                presenter.changeModel(selected);
                presenter.updateLabel();
                presenter.geolocationForAddressBox();
            }
        });
        getRoute.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                EndPoint endPoint = GWT.create(EndPoint.class);
                endPoint.getRoute(new LoadRoutesCallback());
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
                final Button noButton = new Button("No");
                final TextBox startStopBox = new TextBox();
                final TextBox endStopBox = new TextBox();
                final TextBox intermediatePointBox1 = new TextBox();
                final TextBox intermediatePointBox2 = new TextBox();
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
                intermediatePointBox1.setText("Enter first intermediate point");
                intermediatePointBox2.setText("Enter second intermediate point");
                dayWeekBox.setText("Check day of week");
                timeBox.setText("Enter time");
                horizontalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
                horizontalPanel.add(panel);
                panel.add(okButton);
                panel.add(noButton);
                dialogVPanel.add(title);
                dialogVPanel.add(startStopBox);
                dialogVPanel.add(endStopBox);
                dialogVPanel.add(intermediatePointBox1);
                dialogVPanel.add(intermediatePointBox2);
                dialogVPanel.add(dayWeekBox);
                dialogVPanel.add(timeBox);
                dialogVPanel.add(horizontalPanel);
                dialogBox.setWidget(dialogVPanel);
                dialogBox.center();

                okButton.addClickHandler(new ClickHandler() {
                    public void onClick(ClickEvent event) {
                        if (checkIsNull(startStopBox.getText(), endStopBox.getText(),
                                dayWeekBox.getText(), timeBox.getText())) {
                                EndPoint endPoint = GWT.create(EndPoint.class);
                                endPoint.sendRoute(startStopBox.getText(),
                                        endStopBox.getText(),
                                        intermediatePointBox1.getText(),
                                        intermediatePointBox2.getText(),
                                        dayWeekBox.getText(),
                                        timeBox.getText(),
                                        new LoadRoutesCallback());
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
                            EndPoint endPoint = GWT.create(EndPoint.class);
                            endPoint.findRoute(startStopBox.getText(), endStopBox.getText(),
                                    new LoadRoutesCallback());
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

        final NoSelectionModel<Route> selectionModelMyObj = new NoSelectionModel<Route>();
        SelectionChangeEvent.Handler tableHandler = new SelectionChangeEvent.Handler() {

            @Override
            public void onSelectionChange(SelectionChangeEvent event) {
                final Route clickedObj = selectionModelMyObj.getLastSelectedObject();
                final DialogBox dialogBox = new DialogBox();
//                dialogBox.setStyleName("dialogBox");
                final Label title = new Label("Are you sure to delete route?");
                final Label object = new Label(clickedObj.toString());
//                title.addStyleName("gwt-LabelTitle");
//                object.addStyleName("gwt-LabelOblect");
                final Button okButton = new Button("Yes");
                okButton.setStyleName("gwt-Button");
                final Button noButton = new Button("No");
                noButton.setStyleName("gwt-Button");
                VerticalPanel dialogVPanel = new VerticalPanel();
                HorizontalPanel horizontalPanel = new HorizontalPanel();
                horizontalPanel.add(okButton);
                horizontalPanel.add(noButton);
                dialogVPanel.add(title);
                dialogVPanel.add(object);
                dialogVPanel.add(horizontalPanel);
                dialogBox.setWidget(dialogVPanel);
                dialogBox.center();
                dialogBox.show();
                presenter.geolocationForRoute(clickedObj.getStartPoint(), clickedObj.getEndPoint(), markerStartPoint, markerEndPoint);

                okButton.addClickHandler(new ClickHandler() {
                    @Override
                    public void onClick(ClickEvent event) {
                        EndPoint endPoint = GWT.create(EndPoint.class);
                        endPoint.deleteRoute(clickedObj.getStartPoint(),
                                clickedObj.getEndPoint(),
                                clickedObj.getDayWeek(),
                                clickedObj.getTime(),
                                new LoadRoutesCallback());
                        dialogBox.hide();
                    }
                });
                noButton.addClickHandler(new ClickHandler() {
                    @Override
                    public void onClick(ClickEvent event) {
                        dialogBox.hide();
                    }
                });
//                Window.alert("Object selected: " + clickedObj);
            }
        };
        // Add the handler to the selection model
        selectionModelMyObj.addSelectionChangeHandler(tableHandler);
        // Add the selection model to the table
        table.setSelectionModel(selectionModelMyObj);
    }
        public boolean checkIsNull (String startStop, String endStop, String dayWeek, String time){
            if (startStop.equals("") || endStop.equals("") || dayWeek.equals("") || time.equals("")) return false;
            return true;
        }

        public boolean checkIsNull (String startStop, String endStop){
            if (startStop.equals("") || endStop.equals("")) return false;
            return true;
        }

    private static class LoadRoutesCallback implements MethodCallback<RouteDTO> {
        @Override
        public void onFailure(Method method, Throwable throwable) {
            Window.alert("Route did not added!");
        }

        @Override
        public void onSuccess(Method method, RouteDTO result){
            ROUTES = result.getRoutes();

            // Push the data into the widget.
            table.setRowData(0, ROUTES);

            ListDataProvider<Route> dataProvider = new ListDataProvider();
            dataProvider.addDataDisplay(table);
            dataProvider.setList(ROUTES);

        }
    }


}