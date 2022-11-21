package com.workshop.carauctionsystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BrandModel {
    private Long id;
    private String name;
    private String imgPath;

}
