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
    @GeneratedValue
    private Long id;

    @Column(name = "branch_id")
    private Long branchID;

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
    @JoinColumn(name = "branch_id")
    private Branch branch;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_type_id")
    private ServiceType serviceType;

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
            latitude = getBranch().getLatitude();
        }
        return latitude.doubleValue();
    }

    public double getLongitude() {
        if (longitude == null) {
            longitude = getBranch().getLatitude();
        }
        return longitude.doubleValue();
    }
}