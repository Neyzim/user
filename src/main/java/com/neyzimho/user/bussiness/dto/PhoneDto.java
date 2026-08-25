package com.neyzimho.user.bussiness.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PhoneDto {

    private Long id;
    private String number;
    private String ddd;
}
