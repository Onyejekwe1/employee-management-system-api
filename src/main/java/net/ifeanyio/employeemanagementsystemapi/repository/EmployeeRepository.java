package net.ifeanyio.employeemanagementsystemapi.repository;

import net.ifeanyio.employeemanagementsystemapi.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
