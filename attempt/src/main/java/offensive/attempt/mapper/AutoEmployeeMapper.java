package offensive.attempt.mapper;

import offensive.attempt.dto.EmployeeDto;
import offensive.attempt.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AutoEmployeeMapper {
    //bu sekilde yazdiqda interface implement olacaq compilation time da ve bu instance mapping methodlarini cagirmaq ucun ustifade edirik
    AutoEmployeeMapper MAPPER = Mappers.getMapper(AutoEmployeeMapper.class);
//    @Mapping(source = "email",target = "emailAddress")
    // eger Dto icersindeki hansisa field in adi deyisilibse biz burada gelib handle ede bilerik
    EmployeeDto mapToEmployeeDto(Employee employee);
    Employee mapToEmployee(EmployeeDto employeeDto);
}
