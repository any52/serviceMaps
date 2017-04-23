package ru.sample2.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anna on 16.02.2017.
 */
public class SuggestionDTO {
    private List<AddressDTO> suggestions = new ArrayList<>();


    public SuggestionDTO() {
        List<AddressDTO> suggestions = new ArrayList<>();
    }

    @JsonCreator
    public SuggestionDTO(@JsonProperty("suggestions") List<AddressDTO> suggestions) {
        this.suggestions = suggestions;
    }

    public List<AddressDTO> getSuggestions() {
        return suggestions;
    }

    public void setSuggestions(List<AddressDTO> suggestions) {
        this.suggestions = suggestions;
    }
}
