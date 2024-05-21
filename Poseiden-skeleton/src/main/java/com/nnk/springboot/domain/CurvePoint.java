package com.nnk.springboot.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;


@Entity
@Table(name = "curvepoint")
@Data
@NoArgsConstructor
public class CurvePoint {
    @GeneratedValue
    @Id
    Integer id;
    @Column(nullable = false)
    Integer curveId;
    Timestamp asOfDate;
    @Column(nullable = false)
    Double term;
    @Column(nullable = false)
    Double value;
    Timestamp creationDate;

}
