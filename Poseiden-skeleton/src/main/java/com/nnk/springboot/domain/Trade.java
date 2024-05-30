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
@Table(name = "trade")
@Data
@NoArgsConstructor

public class Trade {
    @GeneratedValue
    @Id
    Integer id;
    @Column(nullable = false)
    @NotNull
    @NotEmpty
    String account;
    @Column(nullable = false)
    @NotNull
    @NotEmpty
    String type;
    @Column(nullable = false)
    @Positive
    @NotNull
    Double buyQuantity;
    Double sellQuantity;
    Double buyPrice;
    Double sellPrice;
    String benchmark;
    Timestamp tradeDate;
    String security;
    String status;
    String trader;
    String book;
    String creationName;
    Timestamp creationDate;
    String revisionName;
    Timestamp revisionDate;
    String dealName;
    String dealType;
    String sourceListId;
    String side;

}
