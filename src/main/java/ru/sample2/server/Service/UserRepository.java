package ru.sample2.server.service;

import ru.sample2.server.DAO.entity.UserEntity;

import java.util.List;

/**
 * Created by Anna on 29.04.2017.
 */
public interface UserRepository {
    List<UserEntity> getUsers();

    UserEntity getUser();

    void addUser(String login, String password);
}
