package com.spotifyplaylist.service.impl;

import com.spotifyplaylist.model.entity.Role;
import com.spotifyplaylist.model.entity.Roles;
import com.spotifyplaylist.repo.RoleRepo;
import com.spotifyplaylist.service.RoleService;
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
