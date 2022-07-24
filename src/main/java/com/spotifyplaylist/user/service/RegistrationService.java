package com.spotifyplaylist.user.service;

import com.spotifyplaylist.user.model.dto.RegistrationDTO;

public interface RegistrationService {

     void createAccount(RegistrationDTO registrationDTO);
}
