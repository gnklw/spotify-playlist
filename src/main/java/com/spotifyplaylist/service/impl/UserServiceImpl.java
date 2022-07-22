package com.spotifyplaylist.service.impl;

import com.spotifyplaylist.model.dto.UserDTO;
import com.spotifyplaylist.model.entity.User;
import com.spotifyplaylist.model.mapper.UserMapper;
import com.spotifyplaylist.repo.UserRepo;
import com.spotifyplaylist.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepo repo;
    private final UserMapper mapper;

    public UserServiceImpl(UserRepo repo, UserMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    @Override
    public void save(User user) {
        this.repo.save(user);
    }

    @Override
    public User getUserById(Long userId) {
        return this.repo.findById(userId)
                .orElseThrow(() -> new NullPointerException("The user with id {" + userId + "} was not found."));
    }

    @Override
    public UserDTO getUserByUsername(String username) {
        return this.mapper.toUserDTO(this.repo.findByUsername(username)
                .orElseThrow(() -> new NullPointerException("The user with username {" + username + "} was not found."))
        );
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        return this.mapper.toUserDTO(repo.findByEmail(email)
                .orElseThrow(() -> new NullPointerException("The user with email {" + email + "} was not found."))
        );
    }
}
