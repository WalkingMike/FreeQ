package com.project.freeq.model;

import lombok.Data;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
@Table(name = "Branch")
public class Branch{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="partner_id")
    private Long partnerID;

    @Column
    private String address;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private String phone;

    @Column(precision = 10, scale = 6)
    private BigDecimal longitude;

    @Column(precision = 10, scale = 6)
    private BigDecimal latitude;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "partner_id", insertable = false, updatable = false)
    @JsonIgnore
    private Partner partner;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "branch", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Service> services;
}