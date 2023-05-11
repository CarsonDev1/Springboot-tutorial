//package com.tutorial.apidemo.database;
//
//import com.hitachi.coe.fullstack.entity.base.BaseAudit;
//import com.hitachi.coe.fullstack.entity.base.BaseReadonlyEntity;
//import lombok.Getter;
//import lombok.Setter;
//
//import javax.persistence.*;
//import java.util.Date;
//
//@Getter
//@Setter
//@Entity
//@Table(name = "employee_status")
//public class EmployeeStatus extends BaseAudit implements BaseReadonlyEntity<Date> {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Temporal(TemporalType.DATE)
//    @Column(name = "status_date")
//    private Date statusDate;
//
//    @Column(name = "status")
//    private Integer status;
//
//    // bi-directional many-to-one association to Employee
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "employee_id")
//    private Employee employee;
//
//}
