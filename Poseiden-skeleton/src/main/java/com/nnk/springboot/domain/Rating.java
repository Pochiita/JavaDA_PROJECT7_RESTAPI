package com.nnk.springboot.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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
    @NotNull
    @NotEmpty
    String moodysRating;
    @Column(nullable = false)
    @NotNull
    @NotEmpty
    String sandPRating;
    @Column(nullable = false)
    @NotNull
    @NotEmpty
    String fitchRating;
    @Column(nullable = false)
    @Positive
    @NotNull
    Integer orderNumber;
}
