package com.project.freeq.model;

import lombok.Data;

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

    @Column
    private BigDecimal longitude;

    @Column
    private BigDecimal latitude;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "partner_id", insertable = false, updatable = false)
    private Partner partner;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "branch")
    private List<Service> services;
}