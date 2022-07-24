package com.spotifyplaylist.role.service.impl;

import com.spotifyplaylist.role.model.entity.Role;
import com.spotifyplaylist.role.enumeration.Roles;
import com.spotifyplaylist.role.repo.RoleRepo;
import com.spotifyplaylist.role.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepo repo;

    public RoleServiceImpl(RoleRepo repo) {
        this.repo = repo;
    }

    @Override
    public void initRoles() {
        if (this.repo.count() != 0) {
            return;
        }

        Role userRole = new Role().setRoleName(Roles.USER);
        Role adminRole = new Role().setRoleName(Roles.ADMIN);

        this.repo.saveAll(List.of(userRole, adminRole));
    }

    @Override
    public Role getRole(Roles role) {
        return this.repo.findByRoleName(role)
                .orElseThrow(() -> new IllegalStateException("USER role not found. Please seed the roles."));
    }
}
