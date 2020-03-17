package com.igor.course.services;

import com.igor.course.dao.UserDao;
import com.igor.course.entity.Exercise;
import com.igor.course.entity.Role;
import com.igor.course.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserService implements ServiceInterface<User> {
    private AuthorityService authorityService;
    private UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional
    public User get(String id) {
        return userDao.get(id);
    }

    @Override
    @Transactional
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    @Transactional
    public void add(User user) {
        userDao.save(user);
    }

    @Override
    @Transactional
    public void delete(User user) {
        userDao.delete(user);
    }

    @Override
    @Transactional
    public void update(String id, User user) throws Exception {
        throw new Exception("\n============> Methode update ist noch nicht implementirt!\n");
    }


    @Transactional
    public void setPoints(String id, int points) {
        get(id).setPoints(points);
    }

    @Transactional
    public Role getRoleByUser(String id) {
        return authorityService.get(id);
    }

    @Transactional
    public void addExercise(String id, Exercise exercise) {
        userDao.addExercise(id, exercise);
    }

    @Autowired
    public void setAuthorityService(AuthorityService authorityService) {
        this.authorityService = authorityService;
    }
}