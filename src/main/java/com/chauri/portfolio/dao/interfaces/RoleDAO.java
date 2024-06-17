package com.chauri.portfolio.dao.interfaces;

import com.chauri.portfolio.entity.Role;

public interface RoleDAO {
    public Role findRoleByName(String roleName);
}
