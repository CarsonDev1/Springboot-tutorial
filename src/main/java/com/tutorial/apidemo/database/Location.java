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
//@Table(name = "location")
//public class Location extends BaseAudit implements BaseReadonlyEntity<Integer> {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
//
//    @Column(name = "code")
//    private String code;
//
//    @Column(name = "name")
//    private String name;
//
//    // bi-directional many-to-one association to Branch
//    @OneToMany(mappedBy = "location", fetch = FetchType.LAZY)
//    private List<Branch> branches;
//
//}
