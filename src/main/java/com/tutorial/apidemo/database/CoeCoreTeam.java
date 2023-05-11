//package com.tutorial.apidemo.database;
//
//import com.hitachi.coe.fullstack.entity.base.BaseAudit;
//import com.hitachi.coe.fullstack.entity.base.BaseReadonlyEntity;
//import lombok.Getter;
//import lombok.Setter;
//
//import javax.persistence.*;
//import java.util.List;
//
//@Getter
//@Setter
//@Entity
//@Table(name = "coe_core_team")
//public class CoeCoreTeam extends BaseAudit implements BaseReadonlyEntity<Integer> {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
//
//    private String code;
//
//    private String name;
//
//    private Integer status;
//
//    @Column(name = "sub_leader_id")
//    private Integer subLeaderId;
//
//    // bi-directional many-to-one association to CenterOfExcellence
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "coe_id")
//    private CenterOfExcellence centerOfExcellence;
//
//    // bi-directional many-to-one association to Employee
//    @OneToMany(mappedBy = "coeCoreTeam", fetch = FetchType.LAZY)
//    private List<Employee> employees;
//
//}
