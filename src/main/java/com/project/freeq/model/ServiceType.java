package com.project.freeq.model;

import lombok.Data;

import javax.persistence.*;

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
}