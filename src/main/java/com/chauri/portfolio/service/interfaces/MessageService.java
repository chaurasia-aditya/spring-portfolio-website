package com.chauri.portfolio.service.interfaces;

import com.chauri.portfolio.entity.Message;

import java.util.List;

public interface MessageService {
    List<Message> getAllMessages();

    Message findByEmail(String theEmail);

    Message save(Message message);

    void deleteById(Integer id);
}
