package ru.sample2.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by Anna on 10.04.2017.
 */
public class AddressDTO implements Serializable {
    private String street;
    private int numberHouse;

    public AddressDTO() {
    }
    @JsonCreator
    public AddressDTO(@JsonProperty("street") String street, @JsonProperty("numberHouse") int numberHouse) {
        this.street = street;
        this.numberHouse = numberHouse;
    }

    public String getStreet() {
        return street;
    }

    public int getNumberHouse() {
        return numberHouse;
    }

    public void setNumberHouse(int numberHouse) {
        this.numberHouse = numberHouse;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AddressDTO)) return false;

        AddressDTO that = (AddressDTO) o;

        if (numberHouse != that.numberHouse) return false;
        return street.equals(that.street);

    }

    @Override
    public int hashCode() {
        int result = street.hashCode();
        result = 31 * result + numberHouse;
        return result;
    }

    @Override
    public String toString() {
        return "ул." + street + ", " +
                "д." + numberHouse;
    }
}
