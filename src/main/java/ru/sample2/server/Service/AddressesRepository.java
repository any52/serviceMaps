package ru.sample2.server.service;

import ru.sample2.shared.SuggestionDTO;

import java.io.Serializable;

/**
 * Created by Anna on 22.02.2017.
 */
public interface AddressesRepository extends Serializable {

    public SuggestionDTO getAddressesList(final String input);

}
