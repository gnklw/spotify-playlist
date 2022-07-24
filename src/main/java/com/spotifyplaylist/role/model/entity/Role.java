package com.spotifyplaylist.role.model.entity;

import com.spotifyplaylist.model.entity.BaseEntity;
import com.spotifyplaylist.role.enumeration.Roles;
import com.spotifyplaylist.user.model.entity.User;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table
public class Role extends BaseEntity {

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Roles roleName;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER)
    private Set<User> users;

    public Roles getRoleName() {
        return roleName;
    }

    public Role setRoleName(Roles role) {
        this.roleName = role;
        return this;
    }

    public Set<User> getUsers() {
        return users;
    }

    public Role setUsers(Set<User> users) {
        this.users = users;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role1 = (Role) o;
        return getRoleName() == role1.getRoleName();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRoleName());
    }

    @Override
    public String toString() {
        return "Role{" +
                "role=" + roleName +
                '}';
    }
}
