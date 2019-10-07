package com.project.freeq.model;

import lombok.Data;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.time.Duration;
import java.util.List;

@Data
@Entity
@Table(name = "Queue")
public class Queue{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="service_id")
    private Long serviceID;

    @Column(name="current_ticket_id")
    private Long currentTicketID;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "average_time")
    private Duration averageTime;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_id", insertable = false, updatable = false)
    private Service service;
}