package com.igor.course.dao;


import com.igor.course.entity.Exercise;
import com.igor.course.entity.User;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.function.Consumer;

@Repository
@EnableAspectJAutoProxy
@EnableTransactionManagement
public class UserDao implements Dao<User>{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User get(String id) {
        return  entityManager.find(User.class, id);
    }

    @Override
    public List<User> getAll() {
        List<User> res
                = entityManager
                    .createQuery("from User where points > 0 order by points desc ", User.class) //todo: use hql to display only first 20 leaders
                    .setMaxResults(20)
                    .getResultList();
        return res;
    }

    @Override
    public void save(User user) {
        entityManager.persist(user);
    }

    public void update(User user, String[] params) {
        user.setUsername(params[0]);
        user.setPassword(params[1]);
        user.setEmail(params[2]);
        user.setPoints(Integer.parseInt(params[3]));

        executeInsideTransaction(entityManager -> entityManager.merge(user));
    }

    public void setPoints(User user, int newScore) {
        user.setPoints(newScore);
    } //todo: refactor this to service layer

    @Override
    public void delete(User user) {
        executeInsideTransaction(entityManager -> entityManager.remove(user));
    }

    private void executeInsideTransaction(Consumer<EntityManager> action) {
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            action.accept(entityManager);
            tx.commit();
        }
        catch (RuntimeException e) {
            tx.rollback();
            throw e;
        }
    }

    public void addExercise(String id, Exercise exercise) {
        get(id).addExercise(exercise);
    }
}
