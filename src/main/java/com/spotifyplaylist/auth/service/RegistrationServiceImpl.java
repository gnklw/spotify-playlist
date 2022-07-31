package com.spotifyplaylist.auth.service;

import com.spotifyplaylist.role.enumeration.Roles;
import com.spotifyplaylist.role.service.RoleService;
import com.spotifyplaylist.user.service.UserService;
import com.spotifyplaylist.auth.model.dto.RegistrationDTO;
import com.spotifyplaylist.user.model.entity.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    private final UserService userService;
    private final UserDetailsService userDetailsService;
    private final RoleService roleService;
    private final PasswordEncoder encoder;

    public RegistrationServiceImpl(UserService userService, UserDetailsService userDetailsService,
                                   RoleService roleService, PasswordEncoder encoder) {
        this.userService = userService;
        this.userDetailsService = userDetailsService;
        this.roleService = roleService;
        this.encoder = encoder;
    }

    @Override
    public void createAccount(RegistrationDTO registrationDTO) {
        User user = new User()
                .setUsername(registrationDTO.getUsername())
                .setPassword(this.encoder.encode(registrationDTO.getPassword()))
                .setEmail(registrationDTO.getEmail());

        user.addRole(this.roleService.getRole(Roles.USER));

        this.userService.save(user);
        this.login(user.getUsername());
    }

    private void login(String username) {
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetails,
                userDetails.getPassword(),
                userDetails.getAuthorities()
        );

        SecurityContextHolder
                .getContext()
                .setAuthentication(authentication);
    }
}
