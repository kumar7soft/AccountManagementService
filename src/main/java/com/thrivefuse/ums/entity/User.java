package com.thrivefuse.ums.entity;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "user")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "primary_phone")
    private String primaryPhone;

    @Column(name = "secondary_phone")
    private String secondaryPhone;

    @Column(name = "address")
    private String address;

    @Column(name = "state")
    private String state;

    @Column(name = "country")
    private String country;

    @Column(name = "city")
    private String city;

    @Column(name = "zip")
    private String zip;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pan_adhar_id", referencedColumnName = "id")
    private UserPanCardAndAdharDetailsEntity userPanAndAdharDetails;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="",referencedColumnName="id")
    private UserCredentials userCredentials;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "is_verified")
    private boolean isVerified;

    @Column(name = "is_active")
    private boolean isActive;

    // Getters and Setters
}