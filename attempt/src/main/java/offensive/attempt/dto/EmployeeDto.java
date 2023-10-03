package offensive.attempt.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SecondaryRow;

@Schema(description = "EmployeeDto model info")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class EmployeeDto {
    public Long id;
    @Schema(
            description = "Employee name"
    )
    @NotEmpty(message = " if you are clever enter name")
    public String name;
    @Schema(description = "Employee surname")
    @NotEmpty(message = "enter surname stupid")
    public String surname;
    @Schema(description = "Employee email")
    @NotEmpty(message = "i will not ask twice")
    @Email(message = "Email format should be like someone@gmail.com ")
    public String email;
}



