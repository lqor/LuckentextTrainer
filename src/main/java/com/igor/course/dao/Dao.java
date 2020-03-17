package com.igor.course.dao;

import com.igor.course.entity.Role;

import java.util.List;

//todo: i dont need Dao-layer, because spring already provides one.
//But I still need dao layer, I just have to use spring for it
public interface Dao<T> {
    T get(String id);

    List<T> getAll();

    void save(T t);

    void delete(T t);
}