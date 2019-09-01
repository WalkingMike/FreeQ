package com.project.freeq.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "User")
public class User{
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String phone;

    @Column
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(unique = true)
    private String email;

    @Column
    private Double longitude;

    @Column
    private Double latitude;

}