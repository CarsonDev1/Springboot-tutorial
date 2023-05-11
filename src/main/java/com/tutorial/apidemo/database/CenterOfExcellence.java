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
//@Table(name = "center_of_excellence")
//public class CenterOfExcellence extends BaseAudit implements BaseReadonlyEntity<Integer> {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
//
//    private String code;
//
//    private String name;
//
//    // bi-directional many-to-one association to CoeCoreTeam
//    @OneToMany(mappedBy = "centerOfExcellence")
//    private List<CoeCoreTeam> coeCoreTeams;
//
//
//}
