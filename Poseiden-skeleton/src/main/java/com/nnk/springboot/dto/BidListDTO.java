package com.nnk.springboot.dto;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BidListDTO {
    @NotNull
    @NotEmpty
    String account;
    @NotNull
    @NotEmpty
    String type;
    @Positive
    @NotNull
    Double bidQuantity;
}
