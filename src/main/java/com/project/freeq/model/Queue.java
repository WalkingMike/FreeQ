package com.project.freeq.model;

import lombok.Data;

import javax.persistence.*;
import java.time.Duration;
import java.util.Timer;

@Data
@Entity
@Table(name = "Queue")
public class Queue{
    @Id
    @GeneratedValue
    private Long id;

    @Column(name="service_id")
    private Long serviceID;

    @Column(name="current_ticket_id")
    private Long currentTicketID;

    @Column(unique = true)
    private String phone;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "average_time")
    private Duration averageTime;
}