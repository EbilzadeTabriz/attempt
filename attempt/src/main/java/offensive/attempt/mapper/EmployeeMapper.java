package offensive.attempt.mapper;

import offensive.attempt.dto.EmployeeDto;
import offensive.attempt.entity.Employee;


public class EmployeeMapper {

    public static Employee toEmployee(EmployeeDto employeeDto) {
        Employee employee = new Employee(
                employeeDto.getId(),
                employeeDto.getName(),
                employeeDto.getSurname(),
                employeeDto.getEmail()
        );
        return employee;
    }

    public static EmployeeDto toEmployeeDto(Employee employee) {

        EmployeeDto employeeDto = new EmployeeDto(
                employee.getId(),
                employee.getName(),
                employee.getSurname(),
                employee.getEmail()
        );
        return employeeDto;
    }
}
