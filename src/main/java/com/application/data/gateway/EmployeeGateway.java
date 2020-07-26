package com.application.data.gateway;

import com.application.core.model.business.Employee;
import com.application.core.model.dto.EmployeeDto;
import com.application.data.parser.EmployeeParser;
import com.application.data.repository.EmployeeRepository;
import lombok.SneakyThrows;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeGateway {
    private final EmployeeRepository repository;

    public EmployeeGateway(EmployeeRepository repository) {
        this.repository = repository;
    }
    @SneakyThrows
    public EmployeeDto persist(EmployeeDto employeeDto) {
        Employee employee = EmployeeParser.mapToRow(employeeDto);
        return EmployeeParser.mapToDto(repository.save(employee));
    }

    @SneakyThrows
    public Employee findByUser(Integer idUser) {
        Employee employee = repository.findFirstByUserIdUser(idUser);
        return employee;
    }

    @SneakyThrows
    public List<Employee> findAll() {
        List<Employee> employeeList = repository.findAll();
        return employeeList;
    }


}
