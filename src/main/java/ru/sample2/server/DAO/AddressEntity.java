package ru.sample2.server.DAO;

import javax.persistence.*;

/**
 * Created by Anna on 10.04.2017.
 */
@Entity
@Table(name = "address", schema = "public", catalog = "postgres")
public class AddressEntity {
    private int id;
    private String city;
    private String street;
    private int numberhome;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "city", nullable = false, length = 50)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @Column(name = "street", nullable = false, length = 50)
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Basic
    @Column(name = "numberhome", nullable = false)
    public int getNumberhome() {
        return numberhome;
    }

    public void setNumberhome(int numberhome) {
        this.numberhome = numberhome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AddressEntity that = (AddressEntity) o;

        if (id != that.id) return false;
        if (numberhome != that.numberhome) return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        if (street != null ? !street.equals(that.street) : that.street != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (street != null ? street.hashCode() : 0);
        result = 31 * result + numberhome;
        return result;
    }
}
