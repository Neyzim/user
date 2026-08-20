package com.neyzimho.user.bussiness;

import com.neyzimho.user.bussiness.converter.UserConverter;
import com.neyzimho.user.bussiness.dto.UserDto;
import com.neyzimho.user.infrastructure.entities.UserEntity;
import com.neyzimho.user.infrastructure.exception.ConflictException;
import com.neyzimho.user.infrastructure.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserConverter userConverter;
    private final PasswordEncoder passwordEncoder;


    public UserDto saveUser(UserDto userDto){
        existsEmail(userDto.getEmail());
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        UserEntity user = userConverter.toUserEntity(userDto);
        return userConverter.toUserDto(userRepository.save(user));
    }

    public void existsEmail(String email){
        try {
            boolean exists = verifyExistsEmail(email);

            if(exists) {
                throw new ConflictException("Email já cadastrado!");
            }
        } catch (ConflictException e) {
            throw new ConflictException("Email já cadastrado!" + e.getCause());
        }
    }

    public boolean verifyExistsEmail(String email){
        return userRepository.existsByEmail(email);
    }
}
