package com.nnk.springboot.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.sql.Timestamp;


@Entity
@Table(name = "curvepoint")
@Data
@NoArgsConstructor
public class CurvePoint {
    @GeneratedValue
    @Id
    Integer id;
    Integer curveId;
    Timestamp asOfDate;
    @Column(nullable = false)
    @Positive
    @NotNull
    Double term;
    @Column(nullable = false)
    @Positive
    @NotNull
    Double value;
    Timestamp creationDate;

}
