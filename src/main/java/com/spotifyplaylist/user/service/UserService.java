package com.spotifyplaylist.user.service;

import com.spotifyplaylist.user.model.dto.UserDTO;
import com.spotifyplaylist.user.model.entity.User;

public interface UserService {

    void save(User user);

    User getUserById(Long userId);

    User getUserByUsername(String username);

    UserDTO getUserByEmail(String email);
}
