package com.project.freeq.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "Ticket")
public class Ticket{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="queue_id")
    private Long queueID;

    @Column(name="user_id")
    private Long userID;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "is_ready")
    private Boolean isReady;

    @Column
    private Float priority;

    @Column
    private Date start;

    @Column
    private Date finish;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "queue_id", insertable = false, updatable = false)
    private Queue queue;
}