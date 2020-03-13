package com.igor.course.dao;

import com.igor.course.entity.Role;
import com.igor.course.entity.User;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.function.Consumer;

@Repository
@EnableAspectJAutoProxy
@EnableTransactionManagement
public class RoleDao {

    @PersistenceContext
    private EntityManager entityManager;

    public Role get(String id) {
        return  entityManager.find(Role.class, id);
    }

    public List<Role> getAll() {
        List<Role> res
                = entityManager
                .createQuery("from Role", Role.class)
                .getResultList();
        return res;
    }


    public void save(Role role) {
        entityManager.persist(role);
    }

    public void update(String id, Role role) {
        Role temp = get(id);
        temp.setUsername(role.getUsername());
        temp.setAuthority(role.getAuthority());

        entityManager.getTransaction().commit();
    }

    public void setPoints(Role role, int newScore) {

    }

    public void delete(Role role) {
        entityManager.remove(role);
    }
}
