package com.workshop.carauctionsystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModelSpecieDTO {

    private Long id;
    private String nameType;
    private int seatNumber;
    private int status;
}
