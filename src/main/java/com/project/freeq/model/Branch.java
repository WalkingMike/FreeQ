package com.project.freeq.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Branch")
public class Branch{
    @Id
    @GeneratedValue
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
    private Double longitude;

    @Column
    private Double latitude;
}