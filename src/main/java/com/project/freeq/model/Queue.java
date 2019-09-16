package com.project.freeq.model;

import lombok.Data;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_id", insertable = false, updatable = false)
    private Service service;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "queue", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ticket> tickets;
}