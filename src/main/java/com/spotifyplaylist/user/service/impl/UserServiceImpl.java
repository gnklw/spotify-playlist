package com.spotifyplaylist.user.service.impl;

import com.spotifyplaylist.user.model.dto.UserDTO;
import com.spotifyplaylist.user.model.entity.User;
import com.spotifyplaylist.user.model.mapper.UserMapper;
import com.spotifyplaylist.user.repo.UserRepo;
import com.spotifyplaylist.user.service.UserService;
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
    public User getUserByUsername(String username) {
        return this.repo.findByUsername(username)
                .orElseThrow(() -> new NullPointerException("The user with username {" + username + "} was not found."));
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        return this.mapper.toUserDTO(repo.findByEmail(email)
                .orElseThrow(() -> new NullPointerException("The user with email {" + email + "} was not found.")));
    }
}
