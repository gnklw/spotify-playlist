package com.spotifyplaylist.user.model.mapper;

import com.spotifyplaylist.auth.model.dto.RegistrationDTO;
import com.spotifyplaylist.user.model.dto.UserDTO;
import com.spotifyplaylist.user.model.entity.User;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toUserDTO(User user);

    User toUser(RegistrationDTO registrationDTO);
}
