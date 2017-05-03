package ru.sample2.server.DAO.Impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import ru.sample2.server.CreateUserServlet;
import ru.sample2.server.DAO.RouteDAO;
import ru.sample2.server.DAO.entity.RoutesEntity;
import ru.sample2.server.DAO.entity.SheduleEntity;
import ru.sample2.server.DAO.entity.UserEntity;
import ru.sample2.server.LoginServlet;
import ru.sample2.shared.Route;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anna on 21.04.2017.
 */
public class RouteDAOImpl implements RouteDAO {
    private Session session;
    private String login;
    private UsersDAOImpl repository;

    @Override
    public void addRoute(Route route) {
        SessionFactory sessions = new Configuration().configure().buildSessionFactory();
        session = sessions.openSession();
        Transaction tx = session.beginTransaction();

        RoutesEntity newRouteEntity = new RoutesEntity();
        newRouteEntity.setStartpoint(route.getStartPoint());
        newRouteEntity.setEndpoint(route.getEndPoint());
        newRouteEntity.setIntermediatePoint1(route.getIntermediatePoint1());
        newRouteEntity.setIntermediatePoint2(route.getIntermediatePoint2());
        UserEntity currentUser = getUserFromRepository();
        newRouteEntity.setUser(currentUser);
        newRouteEntity.setShedules(createShedule(route.getDayWeek(), route.getTime()));

        session.save(newRouteEntity);
        tx.commit();

        session.close();
    }

    @Override
    public List<Route> getAllRoute() {
        SessionFactory sessions = new Configuration().configure().buildSessionFactory();
        session = sessions.openSession();

        UserEntity currentUser = getUserFromRepository();

        Query query = session.createQuery("FROM RoutesEntity where user = :param");
        query.setParameter("param", currentUser);

        List <RoutesEntity> routesEntities =  query.list();
        List <Route> routes = new ArrayList<>();
        for (RoutesEntity routeEntity : routesEntities) {
            Route route = new Route();
            route.setStartPoint(routeEntity.getStartpoint());
            route.setEndPoint(routeEntity.getEndpoint());
            route.setIntermediatePoint1(routeEntity.getIntermediatePoint1());
            route.setIntermediatePoint2(routeEntity.getIntermediatePoint2());
            route.setDayWeek(routeEntity.getShedules().getDayweek());
            route.setTime(routeEntity.getShedules().getTime());
            routes.add(route);

        }
        session.close();
        return routes;
    }

    public  UserEntity getUserFromRepository() {

        login = LoginServlet.getUserLogin();
        if (login == null) {
            login = CreateUserServlet.getUserLogin();
        }
        repository = new UsersDAOImpl();
        return repository.getUser(login);
    }
    public SheduleEntity createShedule(String dayWeek, String time) {
        SheduleEntity newShedule = new SheduleEntity();
        newShedule.setDayweek(dayWeek);
        newShedule.setTime(time);
        session.save(newShedule);
        return newShedule;
    }
    @Override
    public List <Route> findRoute(String startpoint, String endpoint) {
        String param1 = startpoint.toLowerCase();
        String param2 = endpoint.toLowerCase();
        SessionFactory sessions = new Configuration().configure().buildSessionFactory();
        session = sessions.openSession();

        Query query = session.createQuery("FROM RoutesEntity where  " +
                "(lower(startpoint) like :param1 and lower(endpoint) like :param2) " +
                "or (lower(startpoint) like :param1 and lower( intermediatePoint1) like :param2)" +
                "or (lower(startpoint) like :param1 and lower( intermediatePoint2) like :param2)" +
                "or (lower(intermediatePoint1) like :param1 and lower( intermediatePoint2) like :param2)" +
                "or (lower(intermediatePoint1) like :param1 and lower(endpoint) like :param2) " +
                "or (lower(intermediatePoint2) like :param1 and lower(endpoint) like :param2)");
        query.setParameter("param1", param1);
        query.setParameter("param2", param2);

        List <RoutesEntity> routesEntities =  query.list();
        List <Route> routes = new ArrayList<>();
        for (RoutesEntity routeEntity : routesEntities) {
            Route route = new Route();
            route.setStartPoint(routeEntity.getStartpoint());
            route.setEndPoint(routeEntity.getEndpoint());
            route.setIntermediatePoint1(routeEntity.getIntermediatePoint1());
            route.setIntermediatePoint2(routeEntity.getIntermediatePoint2());
            route.setDayWeek(routeEntity.getShedules().getDayweek());
            route.setTime(routeEntity.getShedules().getTime());
            routes.add(route);

        }
        session.close();
        return  routes;
    }

    @Override
    public List<Route> deleteRoute(String startPoint, String endPoint, String dayweek, String time) {
        SessionFactory sessions = new Configuration().configure().buildSessionFactory();
        session = sessions.openSession();
        Transaction tx = session.beginTransaction();

        UserEntity currentUser = getUserFromRepository();

        Query queryShedule = session.createQuery("from SheduleEntity where lower(dayweek) like :param4 and lower(time) like :param5 ");
        queryShedule.setParameter("param4", dayweek);
        queryShedule.setParameter("param5",time);
        List <SheduleEntity> sheduleList = queryShedule.list();
        System.out.println("Shedulelist:" + sheduleList.toString());
        System.out.println("Size" + sheduleList.size());

        //first element
        ArrayList<Integer> idShedule = new ArrayList<>();
//        for (SheduleEntity shedule : sheduleList) {
//            idShedule.add(shedule.getId());
//        }
        SheduleEntity newShedule = new SheduleEntity();
        newShedule.setDayweek(dayweek);
        newShedule.setTime(time);

        Query query = session.createQuery("delete from RoutesEntity where user = :param " +
                "and lower(startpoint) like :param1 and lower(endpoint) like :param2 " +
                "and shedules in (:param3)" );
        query.setParameter("param", currentUser);
        query.setParameter("param1", startPoint);
        query.setParameter("param2", endPoint);
        query.setParameter("param3", sheduleList);
//        Query query = session.createQuery("delete from RoutesEntity where startpoint like :param");
//        query.setParameter("param", "afasf");
//        session.delete();
        query.executeUpdate();
//        List<RoutesEntity> result  = query.list();
//        System.out.println("Shedulelist:" + result.toString());
        System.out.println("Size result=" + sheduleList.size());
        System.out.println("Deleted"+ query.executeUpdate());

        tx.commit();

        session.close();
        return getAllRoute();
    }

}
