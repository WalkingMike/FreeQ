package com.project.freeq.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "Service")
public class Service{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "branch_id")
    private Long branchID;

    @Column(name = "schedule_id")
    private Long scheduleID;

    @Column(name = "service_type_id")
    private Long serviceTypeID;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private String phone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id", insertable = false, updatable = false)
    private Branch branch;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_type_id", insertable = false, updatable = false)
    private ServiceType serviceType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id", insertable = false, updatable = false)
    private Schedule schedule;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Queue> queues;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UsualBreak> uBreaks;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TechnicalBreak> tBreaks;

    public String getPhone() {
        if (phone == null) {
            phone = getBranch().getPhone();
        }
        return phone;
    }
}