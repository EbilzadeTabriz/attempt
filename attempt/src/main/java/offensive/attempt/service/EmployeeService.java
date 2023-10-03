package offensive.attempt.service;

import offensive.attempt.dto.EmployeeDto;
import offensive.attempt.entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<EmployeeDto> getAll();

    EmployeeDto getById(Long id);

    EmployeeDto create(EmployeeDto employee);

    EmployeeDto updateEmployee(EmployeeDto employee);

    void deleteEmployee(Long id);


}
