package com.thrivefuse.ums.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "user_credentials")
@Data
public class UserCredentials {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @OneToOne(mappedBy = "userCredentials")
    private User user;

}
