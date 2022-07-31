package com.spotifyplaylist.auth.service;

import com.spotifyplaylist.auth.model.dto.RegistrationDTO;

public interface RegistrationService {

     void createAccount(RegistrationDTO registrationDTO);
}
