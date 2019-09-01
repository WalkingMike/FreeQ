package com.project.freeq.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Service")
public class Service{
    @Id
    @GeneratedValue
    private Long id;

    @Column(name="branch_id")
    private Long branchID;

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