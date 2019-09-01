package com.project.freeq.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "Ticket")
public class Ticket{
    @Id
    @GeneratedValue
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

    @Column(name = "first_name")
    private Date start;

    @Column(name = "last_name")
    private Date finish;
}