package com.chauri.portfolio.service.interfaces;

import com.chauri.portfolio.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User findByUserName(String userName);
}
