package com.nnk.springboot.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
    @NotNull
    @NotEmpty
    String name;
    @Column(nullable = false)
    @NotNull
    @NotEmpty
    String description;
    @Column(nullable = false)
    @NotNull
    @NotEmpty
    String json;
    @Column(nullable = false)
    @NotNull
    @NotEmpty
    String template;
    @Column(nullable = false)
    @NotNull
    @NotEmpty
    String sqlStr;
    @Column(nullable = false)
    @NotNull
    @NotEmpty
    String sqlPart;
}
