package com.nnk.springboot.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

@Entity
@Table(name = "rulename")
@Data
@NoArgsConstructor

public class RuleName {
    @GeneratedValue
    @Id
    Integer id;
    @Column(nullable = false)
    String name;
    @Column(nullable = false)
    String description;
    @Column(nullable = false)
    String json;
    @Column(nullable = false)
    String template;
    @Column(nullable = false)
    String sqlStr;
    @Column(nullable = false)
    String sqlPart;
}
