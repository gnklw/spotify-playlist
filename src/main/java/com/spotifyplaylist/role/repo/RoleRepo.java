package com.spotifyplaylist.role.repo;

import com.spotifyplaylist.role.model.entity.Role;
import com.spotifyplaylist.role.enumeration.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {

    Optional<Role> findByRoleName(Roles role);
}
