package com.igor.course.dao;

import com.igor.course.entity.Exercise;
import com.igor.course.entity.User;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@EnableAspectJAutoProxy
@EnableTransactionManagement
public class ExerciseDao implements Dao<Exercise>{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Exercise get(String id) {
        return entityManager.find(Exercise.class, id);
    }

    @Override
    public List<Exercise> getAll() {
        return entityManager.createQuery("from Exercise ", Exercise.class).getResultList();
    }

    @Override
    public void save(Exercise exercise) {
        entityManager.persist(exercise);
    }

    public void update(String id, Exercise exercise) throws Exception {
        throw new Exception("update is not implemented now in TextDao");
    }

    @Override
    public void delete(Exercise exercise) {
        entityManager.remove(exercise);
    }

    public void addUser(String id, User user) {
        get(id).addUser(user);
    }

    public String getLastId() {
        return String.valueOf(getAll().size());
    }
}
