package ru.sample2.server.DAO.entity;

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
    private String intermediatePoint1;
    private String intermediatePoint2;
    //OneToMany Example
    private UserEntity user;
    private SheduleEntity shedules;

    @ManyToOne
    @JoinColumn(name = "sheduleid", referencedColumnName = "id")
    public SheduleEntity getShedules() {
        return shedules;
    }

    public void setShedules(SheduleEntity shedules) {
        this.shedules = shedules;
    }
    @ManyToOne
    @JoinColumn(name = "userid", referencedColumnName = "id")
    public UserEntity getUser() {
        return this.user;
    }
    public void setUser(UserEntity user) {
        this.user = user;
    }

//      private Set<SheduleEntity> shedules = new HashSet<SheduleEntity>();
//    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL, orphanRemoval = true)
//    public Set<SheduleEntity> getShedules() {
//        return shedules;
//    }
//
//    public void setShedules(Set<SheduleEntity> shedules) {
//        this.shedules = shedules;
//    }
//    public void addShedule(SheduleEntity shedule) {
//         shedules.add(shedule);
//    }
//
//    public void deleteShedule(SheduleEntity shedule) {
//        getShedules().remove(shedule);
//    }
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

    @Basic
    @Column(name = "intermpoint2", nullable = true, length = 50)
    public String getIntermediatePoint2() {
        return intermediatePoint2;
    }

    public void setIntermediatePoint2(String intermediatePoint2) {
        this.intermediatePoint2 = intermediatePoint2;
    }

    @Basic
    @Column(name = "intermpoint1", nullable = true, length = 50)
    public String getIntermediatePoint1() {
        return intermediatePoint1;
    }

    public void setIntermediatePoint1(String intermediatePoint1) {
        this.intermediatePoint1 = intermediatePoint1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RoutesEntity)) return false;

        RoutesEntity that = (RoutesEntity) o;

        if (id != that.id) return false;
        if (!startpoint.equals(that.startpoint)) return false;
        if (!endpoint.equals(that.endpoint)) return false;
        if (!intermediatePoint1.equals(that.intermediatePoint1)) return false;
        if (!intermediatePoint2.equals(that.intermediatePoint2)) return false;
        if (!user.equals(that.user)) return false;
        return shedules.equals(that.shedules);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + startpoint.hashCode();
        result = 31 * result + endpoint.hashCode();
        result = 31 * result + intermediatePoint1.hashCode();
        result = 31 * result + intermediatePoint2.hashCode();
        result = 31 * result + user.hashCode();
        result = 31 * result + shedules.hashCode();
        return result;
    }
}
