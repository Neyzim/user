package com.neyzimho.user.infrastructure.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "address")
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;
    @Column(name = "road")
    private String road;
    @Column(name = "number")
    private String number;
    @Column(name = "info", length = 10)
    private String info;
    @Column(name = "city", length = 10)
    private String city;
    @Column(name = "state", length = 2)
    private String state;
    @Column(name = "code", length = 9)
    private String code;
    @Column(name = "user_id")
    private Long user_id;

}
