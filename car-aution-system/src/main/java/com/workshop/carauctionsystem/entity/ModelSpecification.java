package com.workshop.carautionsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Entity
@Table(name = "modelspecification")
public class ModelSpecification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Pattern(regexp = "[a-zA-Z]+",message = "Name not null and no number")
    @Column(name = "name")
    private String nameType;

    @Min(value = 2, message = "Min 2 max 32")
    @Max(value = 32, message = "Min 2 max 32")
    @Column(name = "seatnumber")
    private Long seatNumber;

}
