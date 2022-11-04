package com.workshop.carautionsystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseObject {

    public static final String STATUS_SUSCCES = "SUCCESS";
    public static final String STATUS_FAIL = "FAIL";
    private String status;
    private String message;
    private Object data;

}
