package com.neyzimho.user.bussiness.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressDto {

    private Long id;
    private String road;
    private String number;
    private String info;
    private String city;
    private String state;
    private String code;
}
