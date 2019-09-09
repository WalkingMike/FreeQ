package com.project.freeq.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "usual_break")
public class UsualBreak {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "begin_time")
    private Timestamp beginTime;

    @Column(name = "end_time")
    private Timestamp endTime;

    @ManyToOne(fetch = FetchType.LAZY)
    private Service serviceId;
}
