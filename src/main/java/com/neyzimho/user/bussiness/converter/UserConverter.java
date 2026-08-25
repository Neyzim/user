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


    public List<AddressDto> toAddressListDto(List<AddressEntity> addressesEntity){
        return addressesEntity.stream().map(this::toAddressDto).toList();
    }

    public AddressDto toAddressDto(AddressEntity addressEntity){
        return AddressDto.builder()
                .id(addressEntity.getId())
                .road(addressEntity.getRoad())
                .number(addressEntity.getNumber())
                .info(addressEntity.getInfo())
                .city(addressEntity.getCity())
                .state(addressEntity.getState())
                .code(addressEntity.getCode())
                .build();
    }

    public List<PhoneDto> toPhoneListDto(List<PhoneEntity> phonesEntity){
        return phonesEntity.stream().map(this::toPhoneDto).toList();
    }

    public PhoneDto toPhoneDto(PhoneEntity phoneEntity){
        return PhoneDto.builder()
                .id(phoneEntity.getId())
                .number(phoneEntity.getNumber())
                .ddd(phoneEntity.getDdd())
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

    public AddressEntity updateAddress(AddressDto addressDto, AddressEntity addressEntity){
        return AddressEntity.builder()
                .id(addressEntity.getId())
                .road(addressDto.getRoad() != null ? addressDto.getRoad() : addressEntity.getRoad())
                .city(addressDto.getCity()!= null ? addressDto.getCity() : addressEntity.getCity())
                .state(addressDto.getState() != null ? addressDto.getState() : addressEntity.getState())
                .number(addressDto.getNumber() != null ? addressDto.getNumber() : addressEntity.getState())
                .info(addressDto.getInfo() != null ? addressDto.getInfo() : addressEntity.getInfo())
                .code(addressDto.getCode() != null ? addressDto.getCode() : addressEntity.getCode())
                .user_id(addressEntity.getUser_id())
            .build();
    }

    public PhoneEntity updatePhone(PhoneDto phoneDto, PhoneEntity phoneEntity){
        return PhoneEntity.builder()
                .id(phoneEntity.getId())
                .number(phoneDto.getNumber() != null ? phoneDto.getNumber() : phoneEntity.getNumber())
                .ddd(phoneDto.getDdd() != null ? phoneDto.getDdd() : phoneEntity.getDdd())
                .user_id(phoneEntity.getUser_id())
            .build();
    }

    public AddressEntity toAddressEntity(AddressDto addressDto, Long userId){
        return AddressEntity.builder()
                .road(addressDto.getRoad())
                .city(addressDto.getCity())
                .state(addressDto.getState())
                .info(addressDto.getInfo())
                .number(addressDto.getNumber())
                .code(addressDto.getCode())
                .user_id(userId)
            .build();
    }

    public PhoneEntity toPhoneEntity(PhoneDto phoneDto, Long userId){
        return PhoneEntity.builder()
                .user_id(userId)
                .ddd(phoneDto.getDdd())
                .number(phoneDto.getNumber())
            .build();
    }
}
