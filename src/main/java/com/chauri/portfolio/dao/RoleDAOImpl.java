package com.chauri.portfolio.dao;

import com.chauri.portfolio.dao.interfaces.RoleDAO;
import com.chauri.portfolio.entity.Role;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDAOImpl implements RoleDAO {
    private EntityManager entityManager;

    @Autowired
    public RoleDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Role findRoleByName(String roleName) {
        TypedQuery<Role> query = entityManager.createQuery("FROM role WHERE name=:roleName", Role.class);

        query.setParameter("roleName", roleName);

        Role foundRole;
        try{
            foundRole = query.getSingleResult();
        }catch(Exception e){
            foundRole = null;
        }

        return foundRole;
    }
}
