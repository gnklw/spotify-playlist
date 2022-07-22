package com.spotifyplaylist.service;

import com.spotifyplaylist.model.dto.UserDTO;
import com.spotifyplaylist.model.entity.User;

public interface UserService {

    void save(User user);

    User getUserById(Long userId);

    UserDTO getUserByUsername(String username);

    UserDTO getUserByEmail(String email);
}
