package com.rogeragostinho.employeemanagement.dto;

public record EmployeeRequest(
        long id,
        String name,
        String position
) { }
