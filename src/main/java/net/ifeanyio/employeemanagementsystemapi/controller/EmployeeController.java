package net.ifeanyio.employeemanagementsystemapi.controller;

import net.ifeanyio.employeemanagementsystemapi.model.Employee;
import net.ifeanyio.employeemanagementsystemapi.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.module.ResolutionException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/employees")
    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    @PostMapping("/employees")
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id){
        return ResponseEntity.ok(employeeRepository
                .findById(id)
                .orElseThrow(() -> new ResolutionException("Employee with Id " + id + "not found")));
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> UpdateEmployee(@PathVariable Long id, @RequestBody Employee employee){
        Employee employeeDetails = employeeRepository
                .findById(id)
                .orElseThrow(() -> new ResolutionException("Employee with Id " + id + "not found"));

        employeeDetails.setFirstName(employee.getFirstName());
        employeeDetails.setLastName(employee.getLastName());
        employeeDetails.setEmailId(employee.getEmailId());

        Employee updatedEmployee = employeeRepository.save(employeeDetails);
        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id){
        Employee employeeDetails = employeeRepository
                .findById(id)
                .orElseThrow(() -> new ResolutionException("Employee with Id " + id + "not found"));

        employeeRepository.delete(employeeDetails);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return ResponseEntity.ok(response);
    }
}
