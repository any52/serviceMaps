package ru.sample2.server;

import ru.sample2.shared.UserEntity;

import java.util.List;

/**
 * Created by Anna on 16.04.2017.
 */
public interface UserRepository {
    List<UserEntity> getUsers();
    void addUser(String login, String password);
}
