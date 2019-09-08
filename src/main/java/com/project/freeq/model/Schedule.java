package com.project.freeq.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;


@Data
@Entity
@Table(name = "Schedule")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO.AUTO)
    private Long id;

    @Column(name = "week_day")
    @Enumerated
    private WeekDay weekDay;

    @Column(name = "begin_time")
    private Timestamp beginTime;

    @Column(name = "end_time")
    private Timestamp endTime;
}
