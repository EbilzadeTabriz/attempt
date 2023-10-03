package offensive.attempt.manager;


import lombok.RequiredArgsConstructor;
import offensive.attempt.dto.EmployeeDto;
import offensive.attempt.entity.Employee;
import offensive.attempt.exception.EmailAlreadyExist;
import offensive.attempt.exception.ResourceNotFound;
import offensive.attempt.mapper.AutoEmployeeMapper;
import offensive.attempt.repository.EmployeeRepository;
import offensive.attempt.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class EmployeeManager implements EmployeeService {

    private final EmployeeRepository repository;
    private final ModelMapper modelMapper;

    //private final EmployeeMapper mapper;
    @Override
    public List<EmployeeDto> getAll() {
        List<Employee> employees = repository.findAll();
//        return employees.stream().map(EmployeeMapper::toEmployeeDto).collect(Collectors.toList());
//        return employees.stream().map((employee) -> modelMapper.map(employee, EmployeeDto.class)).collect(Collectors.toList());
        return employees.stream().map((employee) -> AutoEmployeeMapper.MAPPER.mapToEmployeeDto(employee)).collect(Collectors.toList());


    }

    @Override
    public EmployeeDto getById(Long id) {
//        Optional<Employee> optionalEmployee = repository.findById(id).orElseThrow(());
        Employee employee = repository.findById(id).orElseThrow(()->new ResourceNotFound("Employee","id",id));

//        Employee employee = optionalEmployee.get();
//        return EmployeeMapper.toEmployeeDto(employee);
//        return modelMapper.map(employee, EmployeeDto.class);
//        return AutoEmployeeMapper.MAPPER.mapToEmployeeDto(optionalEmployee.get());
        return AutoEmployeeMapper.MAPPER.mapToEmployeeDto(employee);




    }

    @Override
    public EmployeeDto create(EmployeeDto employeeDto) {
//     Employee employee = EmployeeMapper.toEmployee(employeeDto);
//        Employee employee = modelMapper.map(employeeDto, Employee.class);
        Optional<Employee> optionalEmployee = repository.findByEmail(employeeDto.getEmail());
        if (optionalEmployee.isPresent()){
            throw new EmailAlreadyExist("Email already exist for employee");

        }
        Employee employee = AutoEmployeeMapper.MAPPER.mapToEmployee(employeeDto);
        Employee savedEmployee = repository.save(employee);
//     EmployeeDto savedEmployeeDto = EmployeeMapper.toEmployeeDto(savedEmployee);
//        EmployeeDto savedEmployeeDto = modelMapper.map(savedEmployee, EmployeeDto.class);
        EmployeeDto savedEmployeeDto = AutoEmployeeMapper.MAPPER.mapToEmployeeDto(savedEmployee);

        return savedEmployeeDto;
    }

    @Override
    public EmployeeDto updateEmployee(EmployeeDto employee) {
        Employee existingEmployee = repository.findById(employee.getId()).orElseThrow(
                ()-> new ResourceNotFound("Employee","id", employee.getId())

        );
        existingEmployee.setId(existingEmployee.getId());
        existingEmployee.setName(employee.getName());
        existingEmployee.setSurname(employee.getSurname());
        existingEmployee.setEmail(employee.getEmail());
        Employee update = repository.save(existingEmployee);
//        return EmployeeMapper.toEmployeeDto(update);
//        return modelMapper.map(update, EmployeeDto.class);
        return AutoEmployeeMapper.MAPPER.mapToEmployeeDto(update);



    }

    @Override
    public void deleteEmployee(Long id) {

        Employee existingEmployee = repository.findById(id).orElseThrow(
                ()-> new ResourceNotFound("Employee","id", id)

        );
        repository.deleteById(id);

    }
}
