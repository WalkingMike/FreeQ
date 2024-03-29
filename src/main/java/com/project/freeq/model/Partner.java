package com.project.freeq.model;

import lombok.Data;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.List;


@Data
@Entity
@Table(name = "Partner")
public class Partner {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "partner", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Branch> branches;
}