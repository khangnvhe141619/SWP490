package com.workshop.carauctionsystem.model;

import com.workshop.carauctionsystem.entity.ModelCar;
import com.workshop.carauctionsystem.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarModel {

    private Long id;

    private String carName;

    private String description;

    private Long upBoundPrice;

    private Long downBoundPrice;

    private ModelCar modelId;

    private String status;

    private User createdBy;

    private Timestamp createdAt;

    private Timestamp updatedAt;

}
