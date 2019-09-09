package com.project.freeq.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
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

    @Column
    private BigDecimal longitude;

    @Column
    private BigDecimal latitude;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id", insertable = false, updatable = false)
    private Branch branch;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_type_id", insertable = false, updatable = false)
    private ServiceType serviceType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id", insertable = false, updatable = false)
    private Schedule schedule;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "service")
    private List<Queue> queues;

    public String getPhone() {
        if (phone == null) {
            phone = getBranch().getPhone();
        }
        return phone;
    }

    public double getLatitude() {
        if (latitude == null) {
            latitude = this.getBranch().getLatitude();
        }
        return latitude.doubleValue();
    }

    public double getLongitude() {
        if (longitude == null) {
            longitude = this.getBranch().getLatitude();
        }
        return longitude.doubleValue();
    }
}