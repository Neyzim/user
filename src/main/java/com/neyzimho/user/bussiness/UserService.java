package com.neyzimho.user.bussiness;

import com.neyzimho.user.bussiness.converter.UserConverter;
import com.neyzimho.user.bussiness.dto.UserDto;
import com.neyzimho.user.infrastructure.entities.UserEntity;
import com.neyzimho.user.infrastructure.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserConverter userConverter;

    public UserService(UserRepository userRepository, UserConverter userConverter) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
    }

    public UserDto saveUser(UserDto userDto){
        UserEntity user = userConverter.toUserEntity(userDto);
        return userConverter.toUserDto(userRepository.save(user));
    }
}
