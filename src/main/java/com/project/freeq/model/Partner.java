package com.project.freeq.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "Partner")
public class Partner{
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String login;

    @Column
    private String password;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "legal_name")
    private String legalName;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String phone;

    @Column
    private String description;

    @Column
    private String tin;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "partner")
    private List<Branch> branches;
}