package eu.needtocode.dao;

import eu.needtocode.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserRepositoryDao implements UserDao {

    private final UsersRepository usersRepository;

    @Autowired
    public UserRepositoryDao(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return usersRepository.findAll();
    }

    @Override
    public void addNewUser(User user) {
        usersRepository.save(user);
    }

    public void updateUser(User user) {
        usersRepository.save(user);
    }
}
