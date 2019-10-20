package com.project.freeq.model;

import lombok.Data;
import net.minidev.json.annotate.JsonIgnore;

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
    @JsonIgnore
    private Branch branch;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_type_id", insertable = false, updatable = false)
    @JsonIgnore
    private ServiceType serviceType;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "service", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Schedule> schedules;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "service", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Queue> queues;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "service", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<UsualBreak> uBreaks;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "service", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<TechnicalBreak> tBreaks;

    public String getPhone() {
        if (phone == null) {
            phone = getBranch().getPhone();
        }
        return phone;
    }
}