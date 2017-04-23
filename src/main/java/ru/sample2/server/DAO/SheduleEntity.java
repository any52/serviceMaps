package ru.sample2.server.DAO;

import javax.persistence.*;

/**
 * Created by Anna on 19.04.2017.
 */
@Entity
@Table(name = "shedule", schema = "public", catalog = "postgres")
public class SheduleEntity {
    private int id;
    private String dayweek;
    private String time;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "dayweek", nullable = false, length = 20)
    public String getDayweek() {
        return dayweek;
    }

    public void setDayweek(String dayweek) {
        this.dayweek = dayweek;
    }

    @Basic
    @Column(name = "time", nullable = false, length = 50)
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SheduleEntity that = (SheduleEntity) o;

        if (id != that.id) return false;
        if (dayweek != null ? !dayweek.equals(that.dayweek) : that.dayweek != null) return false;
        if (time != null ? !time.equals(that.time) : that.time != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (dayweek != null ? dayweek.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        return result;
    }
}
