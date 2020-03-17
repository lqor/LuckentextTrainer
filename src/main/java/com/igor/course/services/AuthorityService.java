package com.igor.course.services;

import com.igor.course.dao.RoleDao;
import com.igor.course.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class AuthorityService implements ServiceInterface<Role> {
    private final RoleDao roleDao;

    @Autowired
    public AuthorityService(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    @Transactional
    public Role get(String id) {
        return roleDao.get(id);
    }

    @Override
    @Transactional
    public List<Role> getAll() {
        return roleDao.getAll();
    }

    @Override
    @Transactional
    public void add(Role role) {
        roleDao.save(role);
    }

    @Override
    @Transactional
    public void delete(Role role) {
        roleDao.delete(role);
    }

    @Override
    @Transactional
    public void update(String id, Role role) {
        roleDao.update(id, role);
    }

    @Transactional
    public void setAuthorityByRole(String id, String authority) {
        get(id).setAuthority(authority);
    }
}
