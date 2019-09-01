package com.project.freeq.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Service_type")
public class ServiceType{
    @Id
    @GeneratedValue
    private Long id;

    @Column(name="parent_id")
    private Long parentID;

    @Column
    private String name;

    @Column
    private String description;
}