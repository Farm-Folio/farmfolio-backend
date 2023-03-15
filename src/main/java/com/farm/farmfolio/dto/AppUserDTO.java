package com.farm.farmfolio.dto;

import lombok.Data;

@Data
public class AppUserDTO {
    private String id;
    private String name;
    private String lastName;
    private String email;
    private String mobileNumber;
    private Double latitude;
    private Double longitude;
    private Integer type;
}
