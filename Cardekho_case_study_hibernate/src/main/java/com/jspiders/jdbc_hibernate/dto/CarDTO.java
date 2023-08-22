package com.jspiders.jdbc_hibernate.dto;


import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class CarDTO {
    
    @Id
    private int carId;
    private String carName;
    private String carBrand;
    private String fuelType;
    private int price;

    // Constructors, additional methods, etc.
}