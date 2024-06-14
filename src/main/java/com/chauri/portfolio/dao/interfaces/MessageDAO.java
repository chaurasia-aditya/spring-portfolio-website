package com.chauri.portfolio.dao.interfaces;

import com.chauri.portfolio.entity.Message;

import java.util.List;

public interface MessageDAO {
    List<Message> getAllMessages();

    Message findByEmail(String theEmail);

    Message save(Message message);

    void deleteById(Integer id);
}
