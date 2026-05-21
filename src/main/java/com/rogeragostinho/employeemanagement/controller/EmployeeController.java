package com.rogeragostinho.employeemanagement.controller;

import com.rogeragostinho.employeemanagement.dto.EmployeeDTO;
import com.rogeragostinho.employeemanagement.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @PostMapping({"", "/"})
    public ResponseEntity<EmployeeDTO> create(@RequestBody EmployeeDTO data) {
        return ResponseEntity.ok(service.create(data));
    }

    @GetMapping({"", "/"})
    public ResponseEntity<List<EmployeeDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> get(@PathVariable long id) {
        EmployeeDTO dto = service.get(id);

        if (dto == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(service.get(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDTO> update(EmployeeDTO data) {
        return ResponseEntity.ok(service.update(data));
    }

    @DeleteMapping("/{id}")
    public void delete(@RequestParam long id) {
        service.delete(id);
    }
}
