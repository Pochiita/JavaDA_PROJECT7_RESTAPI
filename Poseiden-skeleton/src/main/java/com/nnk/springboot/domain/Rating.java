package com.nnk.springboot.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
@Table(name = "rating")
@Data
@NoArgsConstructor
public class Rating {
    @Id
    @GeneratedValue
    Integer id;
    @Column(nullable = false)
    String moodysRating;
    @Column(nullable = false)
    String sandPRating;
    @Column(nullable = false)
    String fitchRating;
    @Column(nullable = false)
    Integer orderNumber;
}
