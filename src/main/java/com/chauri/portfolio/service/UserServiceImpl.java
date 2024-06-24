package com.chauri.portfolio.service;

import com.chauri.portfolio.dao.interfaces.RoleDAO;
import com.chauri.portfolio.dao.interfaces.UserDAO;
import com.chauri.portfolio.entity.Role;
import com.chauri.portfolio.entity.User;
import com.chauri.portfolio.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private UserDAO userDAO;
    private RoleDAO roleDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO, RoleDAO roleDAO) {
        this.userDAO = userDAO;
        this.roleDAO = roleDAO;
    }

    @Override
    public User findByUserName(String userName) {
        return userDAO.findByUserName(userName);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User foundUser = findByUserName(username);
        if(foundUser == null)
            throw new UsernameNotFoundException("Invalid username or password.");

        return new org.springframework.security.core.userdetails.User(
                foundUser.getUserName(), foundUser.getPassword(), mapRolesToAuthorities(foundUser.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
