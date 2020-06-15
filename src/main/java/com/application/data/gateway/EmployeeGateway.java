package com.application.data.gateway;

import com.application.core.model.business.Employee;
import com.application.core.model.dto.EmployeeDto;
import com.application.data.parser.EmployeeParser;
import com.application.data.repository.EmployeeRepository;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeGateway {
    private final EmployeeRepository repository;

    public EmployeeGateway(EmployeeRepository repository) {
        this.repository = repository;
    }

    public EmployeeDto persist(EmployeeDto employeeDto) {
        Employee employee = EmployeeParser.mapToRow(employeeDto);
        return EmployeeParser.mapToDto(repository.save(employee));
    }
}
