package com.project.freeq.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "Service_type")
public class ServiceType{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "serviceType")
    private List<Service> services;
}