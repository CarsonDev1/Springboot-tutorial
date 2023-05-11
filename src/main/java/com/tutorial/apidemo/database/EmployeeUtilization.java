//package com.tutorial.apidemo.database;
//
//import com.hitachi.coe.fullstack.entity.base.BaseAudit;
//import com.hitachi.coe.fullstack.entity.base.BaseReadonlyEntity;
//
//import javax.persistence.*;
//import java.util.Date;
//
//public class EmployeeUtilization extends BaseAudit implements BaseReadonlyEntity<Date> {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Temporal(TemporalType.DATE)
//    @Column(name = "ut_date")
//    private Date utDate;
//
//    @Column(name = "available_hours")
//    private Integer availableHours;
//
//    @Column(name = "billable_hours")
//    private Integer billableHours;
//
//    @Temporal(TemporalType.DATE)
//    @Column(name = "end_date")
//    private Date endDate;
//
//    @Column(name = "pto_oracle")
//    private Integer ptoOracle;
//
//    @Temporal(TemporalType.DATE)
//    @Column(name = "start_date")
//    private Date startDate;
//
//    // bi-directional many-to-one association to Employee
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "employee_id")
//    private Employee employee;
//
//}
