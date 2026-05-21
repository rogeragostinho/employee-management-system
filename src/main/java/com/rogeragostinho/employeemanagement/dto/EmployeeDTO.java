package com.rogeragostinho.employeemanagement.dto;

import com.rogeragostinho.employeemanagement.enums.EmployeeStatus;

public record EmployeeDTO(
        long id,
        String name,
        String position,
        EmployeeStatus status,
        long departmentId
) {}