package com.project.PP312.dao;

import com.project.PP312.model.Role;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addRole(Role role) {
        entityManager.persist(role);
    }

    @Override
    public Role getRoleByName(String name) {
        return getAllRoles().stream()
                .filter(role -> role.getRole().equals(name))
                .findAny()
                .orElse(null);
    }

    @Override
    public List<Role> getAllRoles() {
        return entityManager.createQuery("select r from Role r", Role.class)
                .getResultList();
    }
}
