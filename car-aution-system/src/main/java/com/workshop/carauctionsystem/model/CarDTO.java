package com.workshop.carauctionsystem.model;


import com.workshop.carauctionsystem.entity.ModelCar;
import com.workshop.carauctionsystem.entity.User;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter

public class CarDTO {
    private Long id;

    private String carName;
    private String manufacturing;
    private String air_bad;
    private String description;

    private Long upBoundPrice;
    private Long downBoundPrice;
    private ModelCar modelId;
    private int status;

    private User createdBy;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    private CarSpecificationDTO carSpec;
    private SafetySystemDTO safeSys;
}
