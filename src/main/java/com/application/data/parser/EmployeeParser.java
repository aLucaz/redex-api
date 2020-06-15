package com.application.data.parser;

import com.application.core.model.business.Branch;
import com.application.core.model.business.Employee;
import com.application.core.model.business.User;
import com.application.core.model.dto.EmployeeDto;
import com.application.core.model.dto.UserDto;

public class EmployeeParser {

    public static EmployeeDto mapToDto(Employee employee){
        return new EmployeeDto()
                .setIdEmployee(employee.getIdEmployee())
                .setIdBranch(employee.getBranch().getIdBranch())
                .setIdUser(employee.getUser().getIdUser());
    }

    public static Employee mapToRow(EmployeeDto employeeDto){
        return new Employee()
                .setBranch(new Branch().setIdBranch(employeeDto.getIdBranch()))
                .setUser(new User().setIdUser(employeeDto.getIdUser()));
    }

    public static EmployeeDto mapToDto(UserDto userDto){
        return new EmployeeDto()
                .setIdUser(userDto.getIdUser())
                .setIdBranch(userDto.getIdBranch());
    }
}
