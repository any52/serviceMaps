package ru.sample2.server.DAO;

import ru.sample2.server.DAO.entity.UserEntity;

import java.util.List;

/**
 * Created by Anna on 16.04.2017.
 */
public interface UserDAO {
    List<UserEntity> getUsers();
    void addUser(String login, String password);
}
