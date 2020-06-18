package com.application.data.repository;

import com.application.core.model.business.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
    Employee findFirstByUserIdUser(Integer id);
    List<Employee> findAll();
}
