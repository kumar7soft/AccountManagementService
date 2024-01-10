package com.thrivefuse.ums.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "user_pan_adhar_details")
@Data
public class UserPanCardAndAdharDetailsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "adhar_card_number")
    private String adharCardNumber;

    @Column(name = "pan_card_number")
    private String panCardNumber;


    @OneToOne(mappedBy = "userPanAndAdharDetails")
    private User user;

    // Getters and Setters
}

