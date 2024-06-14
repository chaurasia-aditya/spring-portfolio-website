package com.chauri.portfolio.dao;

import com.chauri.portfolio.dao.interfaces.MessageDAO;
import com.chauri.portfolio.entity.Message;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class MessageDAOImpl implements MessageDAO {
    private EntityManager entityManager;

    @Autowired
    public MessageDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Message> getAllMessages() {
        TypedQuery<Message> query = entityManager.createQuery("FROM Message", Message.class);
        return query.getResultList();
    }

    @Override
    public Message findByEmail(String theEmail) {
        TypedQuery<Message> query = entityManager.createQuery("FROM Message WHERE email=:Email", Message.class);
        query.setParameter("Email", theEmail);

        Message foundMessage;
        try{
            foundMessage = query.getSingleResult();
        }catch(Exception e){
            foundMessage = null;
        }

        return foundMessage;
    }

    @Override
    public Message save(Message message) {
        return entityManager.merge(message);
    }

    @Override
    public void deleteById(Integer id) {
        entityManager.remove(entityManager.find(Message.class, id));
    }


}
