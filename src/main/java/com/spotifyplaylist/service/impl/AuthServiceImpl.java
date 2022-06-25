package com.spotifyplaylist.service.impl;

import com.spotifyplaylist.model.dto.UserDTO;
import com.spotifyplaylist.model.mapper.UserMapper;
import com.spotifyplaylist.service.AuthService;
import com.spotifyplaylist.session.LoggedUser;
import com.spotifyplaylist.model.dto.RegisterDTO;
import com.spotifyplaylist.model.entity.Song;
import com.spotifyplaylist.model.entity.User;
import com.spotifyplaylist.repo.UserRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepo repo;
    private final PasswordEncoder encoder;
    private final LoggedUser loggedUser;
    private final HttpSession session;
    private final UserMapper userMapper;

    public AuthServiceImpl(UserRepo repo, PasswordEncoder encoder, LoggedUser loggedUser, HttpSession session, UserMapper userMapper) {
        this.repo = repo;
        this.encoder = encoder;
        this.loggedUser = loggedUser;
        this.session = session;
        this.userMapper = userMapper;
    }

    @Override
    public UserDTO findUserByUsername(String username) {
        User user = this.getUserByUsername(username);
        if (user == null) {
            return null;
        }

        return this.userMapper.toUserDTO(user);
    }

    @Override
    public UserDTO findUserByEmail(String email) {
        User user = repo.findByEmail(email).orElse(null);
        if (user == null) {
            return null;
        }

        return this.userMapper.toUserDTO(user);
    }

    @Override
    public boolean checkCredentials(String username, String password) {
        User user = this.getUserByUsername(username);

        if (user == null) {
            return false;
        }

        return encoder.matches(password, user.getPassword());
    }

    @Override
    public void login(String username) {
        User user = this.getUserByUsername(username);
        this.loggedUser
                .setId(user.getId())
                .setUsername(user.getUsername());
    }

    @Override
    public void register(RegisterDTO registerDTO) {
        User user = this.userMapper.toUser(registerDTO);
        user.setPassword(this.encoder.encode(user.getPassword()));
        this.repo.save(user);
        this.login(registerDTO.getUsername());
    }

    @Override
    public void logout() {
        this.session.invalidate();
        this.loggedUser
                .setId(null)
                .setUsername(null);
    }

    @Override
    public void addSongToUser(Long userId, Song song) {
        User user = this.getUSerById(userId);
        if (user.getPlaylist().stream().noneMatch(s -> s.getId().equals(song.getId()))) {
            user.addSongToPlaylist(song);
            this.repo.save(user);
        }
    }

    @Override
    public void removeSongFromUser(Long userId, Long songId) {
        User user = getUSerById(userId);
        user.removeSongFromPlaylist(songId);
        this.repo.save(user);
    }

    @Override
    public void deleteAllSongs(Long userId) {
        User user = getUSerById(userId);
        user.deleteAllSongFromPlaylist();
        this.repo.save(user);
    }

    private User getUSerById(Long userId) {
        return this.repo.findById(userId).orElseThrow();
    }

    private User getUserByUsername(String username) {
        return this.repo.findByUsername(username).orElse(null);
    }

//    private User mapUser(RegisterDTO registerDTO) {
//        return new User()
//                .setUsername(registerDTO.getUsername())
//                .setEmail(registerDTO.getEmail())
//                .setPassword(encoder.encode(registerDTO.getPassword()));
//    }
//
//    private UserDTO mapUserDTO(User user) {
//        return new UserDTO()
//                .setId(user.getId())
//                .setEmail(user.getEmail())
//                .setUsername(user.getUsername());
//
//    }
}
