package ru.sample2.server.DAO;

import javax.persistence.*;

/**
 * Created by Anna on 19.04.2017.
 */
@Entity
@Table(name = "routes", schema = "public", catalog = "postgres")
public class RoutesEntity {
    private int id;
    private String startpoint;
    private String endpoint;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "startpoint", nullable = false, length = 50)
    public String getStartpoint() {
        return startpoint;
    }

    public void setStartpoint(String startpoint) {
        this.startpoint = startpoint;
    }

    @Basic
    @Column(name = "endpoint", nullable = false, length = 50)
    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoutesEntity that = (RoutesEntity) o;

        if (id != that.id) return false;
        if (startpoint != null ? !startpoint.equals(that.startpoint) : that.startpoint != null) return false;
        if (endpoint != null ? !endpoint.equals(that.endpoint) : that.endpoint != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (startpoint != null ? startpoint.hashCode() : 0);
        result = 31 * result + (endpoint != null ? endpoint.hashCode() : 0);
        return result;
    }
}
