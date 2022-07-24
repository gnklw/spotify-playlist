package com.spotifyplaylist.service;

import com.spotifyplaylist.model.entity.Role;
import com.spotifyplaylist.model.entity.Roles;

public interface RoleService {

    void initRoles();

    Role getRole(Roles role);
}
