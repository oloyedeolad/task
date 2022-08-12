package com.oi.shared.dtos;


import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class OrderDto {

    private Long id;

    @NotEmpty(message = "Name cannot be empty")
    private String name;
    @NotEmpty(message = "phone cannot be empty")
    @Size(min = 10, message = "phone numbers should not be less than 10 characters")
    private String phone;
    @NotEmpty(message = "Address cannot be empty")
    private String address;
    @NotEmpty(message = "Installation date cannot be empty")
    private String installationDate;
    @NotEmpty(message = "Time slot cannot be empty")
    private String timeSlot;
    @NotEmpty(message = "Product details cannot be empty")
    private String productDetails;
}
