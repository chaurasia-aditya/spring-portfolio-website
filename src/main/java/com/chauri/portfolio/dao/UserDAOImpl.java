package com.chauri.portfolio.dao;

import com.chauri.portfolio.dao.interfaces.UserDAO;
import com.chauri.portfolio.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl implements UserDAO {
    private EntityManager entityManager;

    @Autowired
    public UserDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public User findByUserName(String userName) {
        TypedQuery<User> query = entityManager.createQuery("FROM User WHERE userName=:userName and enabled=true", User.class);

        query.setParameter("userName", userName);

        User foundUser;
        try{
            foundUser = query.getSingleResult();
        }catch(Exception e){
            foundUser = null;
        }

        return foundUser;
    }
}
