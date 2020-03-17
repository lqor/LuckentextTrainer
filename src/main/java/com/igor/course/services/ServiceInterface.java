package com.igor.course.services;

import com.igor.course.entity.User;

import java.util.List;

public interface ServiceInterface<T> {
    T get(String id);
    List<T> getAll();
    void add(T t);
    void delete(T t);
    void update(String id, T t) throws Exception;
}


/*
public interface ServiceInterface {
    User getUser(String id);
    List<User> getUsers();
    void addUser(User user);
    void deleteUser(User user);

    //Optional
    public void updateScoreByUser(User user, int newScore);
} */