package com.spotifyplaylist.service.impl;

import com.spotifyplaylist.model.entity.Role;
import com.spotifyplaylist.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.spotifyplaylist.model.entity.User user = this.userService.getUserByUsername(username);
        return new User(user.getUsername(), user.getPassword(), asGrantedAuthorities(user.getRoles()));
    }

    private Set<GrantedAuthority> asGrantedAuthorities(Set<Role> roleEntities) {
        return roleEntities.
                stream().
                map(r -> new SimpleGrantedAuthority("ROLE_" + r.getRoleName().name())).
                collect(Collectors.toSet());
    }
}
