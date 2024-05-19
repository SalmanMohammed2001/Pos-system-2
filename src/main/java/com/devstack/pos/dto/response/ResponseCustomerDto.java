package com.devstack.pos.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ResponseCustomerDto {
    private long publicId;
    private String name;
    private String address;
    private double salary;
    private boolean activeState;
}
