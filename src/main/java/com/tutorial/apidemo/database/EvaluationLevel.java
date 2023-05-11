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
//
//@Getter
//@Setter
//@Entity
//@Table(name = "evaluation_level")
//public class EvaluationLevel extends BaseAudit implements BaseReadonlyEntity<Integer> {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
//
//    @Column(name = "code")
//    private String code;
//
//    @Column(name = "description")
//    private String description;
//
//    // bi-directional many-to-one association to EmployeeEvaluation
//    @OneToMany(mappedBy = "evaluationLevel", fetch = FetchType.LAZY)
//    private List<EmployeeEvaluation> employeeEvaluations;
//
//    // bi-directional many-to-one association to ProjectFeedback
//    @OneToMany(mappedBy = "evaluationLevel", fetch = FetchType.LAZY)
//    private List<ProjectFeedback> projectFeedbacks;
//
//}
