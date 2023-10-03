package offensive.attempt.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import offensive.attempt.dto.EmployeeDto;

import offensive.attempt.manager.EmployeeManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
// AllArgs en vacibidi unutma o olmadan Http sorgularimiz hec ne ede bilmir
@Tag(
        name = "CRUD Rest APIs for employee Resource ",
        description = "CRUDs Rest APIs - Create,Update,Get,Post Employee"
)
@RestController
@AllArgsConstructor
@RequestMapping("api/employee")

public class EmployeeController {
    private EmployeeManager manager;
    @Operation(
            summary = "Get All Employee Rest API",
            description = "Get Employee Rest API is used to get employee in database"

    )
    @ApiResponse(responseCode = "200",
            description = "Http status 200 ok")

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAll() {
        List<EmployeeDto> employee = manager.getAll();
        return new ResponseEntity<>(employee, HttpStatus.OK);

    }
    @Operation(
            summary = "Get  Employee by id Rest API",
            description = "Get Employee Rest API is used to get employee by id in database"

    )
    @ApiResponse(responseCode = "200",
            description = "Http status 200 OK")

    @GetMapping("{id}")
    public ResponseEntity<EmployeeDto> getById(@PathVariable("id") Long id) {
        EmployeeDto employee = manager.getById(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }
    @Operation(
            summary = "Create Employee Rest API",
            description = "Create Employee Rest API is used to save employee in database"

    )
    @ApiResponse(responseCode = "201",
    description = "Http status 201 created")
    @PostMapping
    public ResponseEntity<EmployeeDto> create(@Valid @RequestBody EmployeeDto employee) {
        EmployeeDto employees = manager.create(employee);
        return new ResponseEntity<>(employee, HttpStatus.CREATED);
    }
    @Operation(
            summary = "Update Employee Rest API",
            description = "Update Employee Rest API is used to update employee in database"

    )
    @ApiResponse(responseCode = "200",
            description = "Http status 200 accepted")
    @PutMapping("{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("id") Long id, @RequestBody @Valid EmployeeDto employee) {
        employee.setId(id);
        EmployeeDto employee1 = manager.updateEmployee(employee);
        return new ResponseEntity<>(employee1, HttpStatus.ACCEPTED);
    }
    @Operation(
            summary = "Delete Employee Rest API",
            description = "Delete Employee Rest API is used to delete employee in database"

    )
    @ApiResponse(responseCode = "200",
            description = "Http status 200 ok")

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long id) {
        manager.deleteEmployee(id);
        return new ResponseEntity<>("successfully deleted from base", HttpStatus.OK);

//    }@ExceptionHandler(ResourceNotFound.class)
//    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFound exception, WebRequest webRequest){
//        ErrorDetails errorDetails = new ErrorDetails(
//                LocalDate.now(),
//                exception.getMessage(),
//                webRequest.getDescription(false),
//                "Employee_Not_Found"
//
//        );
//        return new ResponseEntity<>(errorDetails,HttpStatus.NOT_FOUND);
//    }
    }
}
