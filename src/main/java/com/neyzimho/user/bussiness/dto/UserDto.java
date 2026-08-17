package com.neyzimho.user.bussiness.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private String name;
    private String email;
    private String password;
    private List<AddressDto> addresses;
    private List<PhoneDto> phones;
}
