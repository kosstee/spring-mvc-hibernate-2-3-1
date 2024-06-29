package web.service;

import web.model.User;

import java.util.List;

public interface UsersService {

    void save(User user);

    List<User> getUsers();

    User getUserById(Long id);

    void update(Long id, User user);

    void delete(Long id);
}
