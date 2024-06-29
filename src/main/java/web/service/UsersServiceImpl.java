package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDao;
import web.model.User;

import java.util.List;

@Service
@Transactional
public class UsersServiceImpl implements UsersService {

    private final UserDao userDao;

    @Autowired
    public UsersServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void save(User user) {
        if (user.getLogin().isEmpty()) {
            throw new IllegalArgumentException("Login is required");
        }
        userDao.add(user);
    }

    @Override
    public List<User> getUsers() {
        return userDao.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userDao.findById(id);
    }

    @Override
    public void update(Long id, User user) {
        userDao.update(id, user);
    }

    @Override
    public void delete(Long id) {
        userDao.delete(id);
    }
}
