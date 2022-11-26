package com.workshop.carauctionsystem.model;

import com.workshop.carauctionsystem.entity.Car;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class SafetySystemDTO {
    private Long id;

    private Car carId;

    private String air_bad;

    private String abs_brake;

    private String speedControl;

    private String tirePressure;

    private String otherDescription;
}
