package com.neyzimho.user.bussiness;

import com.neyzimho.user.bussiness.converter.UserConverter;
import com.neyzimho.user.bussiness.dto.UserDto;
import com.neyzimho.user.infrastructure.entities.UserEntity;
import com.neyzimho.user.infrastructure.exception.ConflictException;
import com.neyzimho.user.infrastructure.exception.ResourceNotFoundException;
import com.neyzimho.user.infrastructure.repositories.UserRepository;
import com.neyzimho.user.infrastructure.security.JwtUtil;
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
    private final JwtUtil jwtUtil;


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

    public UserDto getUserByEmail(String email){
        try {
            return userConverter.toUserDto(
                    userRepository.findByEmail(email).orElseThrow(
                            () -> new ResourceNotFoundException("Email not Found")
                    )
            );
        } catch (ResourceNotFoundException e){
            throw new ResourceNotFoundException("Email not Found" + email);
        }
    }

    public void deleteUserByEmail(String email){
        userRepository.deleteByEmail(email);
    }

    public UserDto updateUserData(String token, UserDto userDto){
        //Busca o email do usuario através do Token (deixando de ser obrigatorio o email)
       String email = jwtUtil.extractUsername(token.substring(7));
       //criptografia de senha
       userDto.setPassword(userDto.getPassword() != null ?
               passwordEncoder.encode(userDto.getPassword()) : null);
       //Buscou os dados do usuario no Banco
       UserEntity user = userRepository.findByEmail(email).orElseThrow(
               () -> new ResourceNotFoundException("Email não localizado!")
       );
       //Mesclou os dados que recebemos na requisição com os dados do Banco
       UserEntity userEntity = userConverter.updateUser(userDto, user);
       //Criptografa a senha novamente
       userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
        //salva os dados do User convertidos e converte o retorno para o DTO
       return userConverter.toUserDto(userRepository.save(userEntity));
    }
}
