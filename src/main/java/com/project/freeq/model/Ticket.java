package com.project.freeq.model;

import lombok.Data;
import net.minidev.json.annotate.JsonIgnore;

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
    private Long queueId;

    @Column(name="user_id")
    private Long userId;

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

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "queue_id", insertable = false, updatable = false)
    private Queue queue;
}