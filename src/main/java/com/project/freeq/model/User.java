package com.project.freeq.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
@Table(name = "User")
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    private BigDecimal longitude;

    @Column
    private BigDecimal latitude;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Ticket> tickets;
}