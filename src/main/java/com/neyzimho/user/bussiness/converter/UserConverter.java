package com.neyzimho.user.bussiness.converter;

import com.neyzimho.user.bussiness.dto.AddressDto;
import com.neyzimho.user.bussiness.dto.PhoneDto;
import com.neyzimho.user.bussiness.dto.UserDto;
import com.neyzimho.user.infrastructure.entities.AddressEntity;
import com.neyzimho.user.infrastructure.entities.PhoneEntity;
import com.neyzimho.user.infrastructure.entities.UserEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserConverter {


    public UserEntity toUserEntity(UserDto userDto){
        return UserEntity.builder()
                .name(userDto.getName())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .addresses(toAddressListEntity(userDto.getAddresses()))
                .phones(toPhoneListEntity(userDto.getPhones()))
            .build();
    }


    public List<AddressEntity> toAddressListEntity(List<AddressDto> addressDto){
        return addressDto.stream().map(this::toAddressEntity).toList();
    }

    public AddressEntity toAddressEntity(AddressDto addressDto){
        return AddressEntity.builder()
                .road(addressDto.getRoad())
                .number(addressDto.getNumber())
                .info(addressDto.getInfo())
                .city(addressDto.getCity())
                .state(addressDto.getState())
                .code(addressDto.getCode())
            .build();
    }

    public List<PhoneEntity> toPhoneListEntity(List<PhoneDto> phoneDto){
        return phoneDto.stream().map(this::toPhoneEntity).toList();
    }

    public PhoneEntity toPhoneEntity(PhoneDto phoneDto){
        return PhoneEntity.builder()
                .number(phoneDto.getNumber())
                .ddd(phoneDto.getDdd())
            .build();
    }

    public UserDto toUserDto(UserEntity userEntity){
        return UserDto.builder()
                .name(userEntity.getName())
                .email(userEntity.getEmail())
                .password(userEntity.getPassword())
                .addresses(toAddressListDto(userEntity.getAddresses()))
                .phones(toPhoneListDto(userEntity.getPhones()))
                .build();
    }


    public List<AddressDto> toAddressListDto(List<AddressEntity> addressDto){
        return addressDto.stream().map(this::toAddressDto).toList();
    }

    public AddressDto toAddressDto(AddressEntity addressDto){
        return AddressDto.builder()
                .road(addressDto.getRoad())
                .number(addressDto.getNumber())
                .info(addressDto.getInfo())
                .city(addressDto.getCity())
                .state(addressDto.getState())
                .code(addressDto.getCode())
                .build();
    }

    public List<PhoneDto> toPhoneListDto(List<PhoneEntity> phoneDto){
        return phoneDto.stream().map(this::toPhoneDto).toList();
    }

    public PhoneDto toPhoneDto(PhoneEntity phoneDto){
        return PhoneDto.builder()
                .number(phoneDto.getNumber())
                .ddd(phoneDto.getDdd())
                .build();
    }

    public UserEntity updateUser(UserDto userDto, UserEntity userEntity){
        return UserEntity.builder()
                .name(userDto.getName() != null ? userDto.getName() : userEntity.getName())
                .id(userEntity.getId())
                .password(userDto.getPassword() != null ? userDto.getPassword() : userEntity.getPassword())
                .addresses(userEntity.getAddresses())
                .phones(userEntity.getPhones())
                .email(userDto.getEmail() != null ? userDto.getEmail() : userEntity.getEmail())
            .build();
    }
}
