package com.nnk.springboot.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
    Integer tradeId;
    @Column(nullable = false)
    String account;
    @Column(nullable = false)
    String type;
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
