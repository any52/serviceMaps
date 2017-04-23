package ru.sample2.server.DAO;

import javax.persistence.*;

/**
 * Created by Anna on 09.03.2017.
 */
@Entity
@Table(name = "country", schema = "public", catalog = "postgres")
public class CountryEntity {
    private int id;
    private String countryname;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "countryname", nullable = true, length = 50)
    public String getCountryname() {
        return countryname;
    }

    public void setCountryname(String countryname) {
        this.countryname = countryname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CountryEntity that = (CountryEntity) o;

        if (id != that.id) return false;
        if (countryname != null ? !countryname.equals(that.countryname) : that.countryname != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (countryname != null ? countryname.hashCode() : 0);
        return result;
    }
}
