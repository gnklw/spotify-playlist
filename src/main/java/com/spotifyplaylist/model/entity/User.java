package com.spotifyplaylist.model.entity;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table
public class User extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "users_songs",
            joinColumns = @JoinColumn(name = "fk_user"),
            inverseJoinColumns = @JoinColumn(name = "fk_song"))
    private Set<Song> playlist;

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public Set<Song> getPlaylist() {
        return playlist;
    }

    public User setPlaylist(Set<Song> playlist) {
        this.playlist = playlist;
        return this;
    }

    public boolean addSongToPlaylist(Song song) {
        if (this.playlist.stream().noneMatch(s -> s.getId().equals(song.getId()))) {
            return this.playlist.add(song);
        }

        return false;
    }

    public boolean removeSongFromPlaylist(Long songId) {
        return this.playlist.removeIf(s -> s.getId().equals(songId));
    }

    public boolean deleteAllSongFromPlaylist() {
        if (this.playlist.size() > 0) {
            this.playlist.clear();
            return true;
        }

        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return getId().equals(user.getId())
                && getUsername().equals(user.getUsername())
                && getPassword().equals(user.getPassword())
                && getEmail().equals(user.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUsername(), getPassword(), getEmail());
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
