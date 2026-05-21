package com.rogeragostinho.employeemanagement.services;

import com.rogeragostinho.employeemanagement.dto.EmployeeDTO;
import com.rogeragostinho.employeemanagement.entity.Employee;
import com.rogeragostinho.employeemanagement.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    public EmployeeDTO create(EmployeeDTO data) {
        Employee employee = modelMapper.map(data, Employee.class);
        return modelMapper.map(repository.save(employee), EmployeeDTO.class);
    }

    public EmployeeDTO get(long id) {
        Employee employee = repository.findById(id).orElse(null);

        if (employee == null) {
            return null;
        }

        return modelMapper.map(employee, EmployeeDTO.class);
    }

    public List<EmployeeDTO> getAll() {
        return repository.findAll().stream()
                .map(e -> modelMapper.map(e, EmployeeDTO.class))
                .toList();
    }

    public EmployeeDTO update(EmployeeDTO data) {
        return modelMapper.map(repository.save(modelMapper.map(data, Employee.class)), EmployeeDTO.class);
    }

    public void delete(long id) {
        Employee employee = repository.findById(id).orElse(null);
        if (employee != null)
            repository.delete(employee);
    }

}
