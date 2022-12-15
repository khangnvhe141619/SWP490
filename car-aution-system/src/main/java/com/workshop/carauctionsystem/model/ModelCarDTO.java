package com.workshop.carauctionsystem.model;

import com.workshop.carauctionsystem.entity.Brand;
import com.workshop.carauctionsystem.entity.ModelSpecification;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ModelCarDTO {

    private Long id;
    private Brand brandId;
    private String modelName;
    private ModelSpecification modelSpecificationId;
    private int status;
}
