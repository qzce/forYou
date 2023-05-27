package com.qzce.forchae.member;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "M_ID")
    private Long mid;

    @Column(name = "M_NAME")
    private String mname;

    @Column(name = "PASSWORD")
    private String password;

}
