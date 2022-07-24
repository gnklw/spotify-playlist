package com.spotifyplaylist.repo;

import com.spotifyplaylist.model.entity.Role;
import com.spotifyplaylist.model.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {

    Optional<Role> findByRoleName(Roles role);
}
