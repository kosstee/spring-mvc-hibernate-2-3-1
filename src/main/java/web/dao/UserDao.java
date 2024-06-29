package web.dao;

import web.model.User;

import java.util.List;

public interface UserDao {

    void add(User user);

    List<User> findAll();

    User findById(Long id);

    void update(Long id, User user);

    void delete(Long id);
}
