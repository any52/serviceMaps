package ru.sample2.client;

import com.google.common.base.Function;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.SuggestOracle;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;
import ru.sample2.shared.AddressDTO;
import ru.sample2.shared.SuggestionDTO;

import javax.annotation.Nullable;
import javax.inject.Inject;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Lists.transform;


/**
 * Created by Anna on 12.01.2017.
 */
public class RemoteSuggestOracle extends SuggestOracle {
    private EventBus eventBus;
    private EndPoint addressesService;

    private static final String SERVER_ERROR = "An error occurred while "
            + "attempting to contact the server. Please check your network "
            + "connection and try again.";


    @Inject
    public RemoteSuggestOracle( EventBus eventBus, EndPoint addressesService) {

        this.eventBus = eventBus;
        this.addressesService = addressesService;
    }

    @Override
    public void requestSuggestions(Request request, Callback callback) {
        addressesService.getAddressesList(request.getQuery(), new LoadSuggestionsCallback(callback, request));

    }

    private static class LoadSuggestionsCallback implements MethodCallback<SuggestionDTO> {

        private Callback callback;
        private Request request;


        public LoadSuggestionsCallback(Callback callback, Request request) {
            this.callback = callback;
            this.request = request;
        }


        @Override
        public void onFailure(Method method, Throwable throwable) {
            Window.alert("Server error!");
        }

        @Override
        public void onSuccess(Method method, SuggestionDTO results) {

                List<Suggestion> suggestions = transform(newArrayList(results.getSuggestions()), new Function<AddressDTO, Suggestion>() {
                    @Nullable
                    @Override
                    public Suggestion apply(@Nullable final AddressDTO address) {
                        return new Suggestion() {
                            @Override
                            public String getDisplayString() {
                                return address.toString();
                            }

                            @Override
                            public String getReplacementString() {
                                return address.toString();
                            }
                        };
                    }

                });

                callback.onSuggestionsReady(request, new Response(suggestions));

        }

    }
}

