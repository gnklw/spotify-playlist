package com.spotifyplaylist.role.service;

import com.spotifyplaylist.role.model.entity.Role;
import com.spotifyplaylist.role.enumeration.Roles;

public interface RoleService {

    void initRoles();

    Role getRole(Roles role);
}
