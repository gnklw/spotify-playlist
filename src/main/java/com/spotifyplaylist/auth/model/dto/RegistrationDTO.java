package com.spotifyplaylist.auth.model.dto;

import com.spotifyplaylist.user.vallidation.annotation.UniqueEmail;
import com.spotifyplaylist.user.vallidation.annotation.UniqueUsername;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RegistrationDTO {

    private Long id;

    @UniqueUsername
    @Size(min = 3, max = 20)
    @NotNull
    private String username;

    @UniqueEmail
    @Email
    @NotBlank
    private String email;

    @Size(min = 3, max = 20)
    @NotNull
    private String password;

    @Size(min = 3, max = 20)
    @NotNull
    private String confirmPassword;

    public RegistrationDTO() {
    }

    public Long getId() {
        return id;
    }

    public RegistrationDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public RegistrationDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public RegistrationDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public RegistrationDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public RegistrationDTO setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }
}
