package com.chauri.portfolio.dao.interfaces;

import com.chauri.portfolio.entity.User;

public interface UserDAO {
    User findByUserName(String userName);
}
