package eu.needtocode.dao;

import eu.needtocode.model.User;

import java.util.List;

public interface UserDao {
    List<User> getAllUsers();

    void addNewUser(User user);

    void updateUser(User user);
}
