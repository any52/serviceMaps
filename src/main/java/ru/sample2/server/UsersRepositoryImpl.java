package ru.sample2.server;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import ru.sample2.shared.UserEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anna on 16.04.2017.
 */
public class UsersRepositoryImpl implements UserRepository {
    private  List<UserEntity> users;
    private Session session;

    @Override
    public List<UserEntity> getUsers() {
        if (users == null) {
            users = new ArrayList<>();
        }
        SessionFactory sessions = new Configuration().configure().buildSessionFactory();
        session = sessions.openSession();
        Query query = session.createQuery("FROM UserEntity");
        users = query.list();
        session.close();
        return users;
    }

    @Override
    public void addUser(String login, String password) {
        if (users == null) {
            users = getUsers();
        }

        int index = users.size()+1;
        SessionFactory sessions = new Configuration().configure().buildSessionFactory();
        session = sessions.openSession();
        Transaction tx = session.beginTransaction();

        UserEntity newUser  = new UserEntity();
        newUser.setId(index);
        newUser.setLogin(login);
        newUser.setPassword(password);

        users.add(newUser);

        session.save(newUser);
        tx.commit();
        session.close();
    }


}
