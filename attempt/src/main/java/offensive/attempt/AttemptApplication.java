package offensive.attempt;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title ="Spring Boot Rest Api Documentation",
                description = "Spring Boot Rest Api Documentation",
                version = "offensive.0.0.1",
                contact = @Contact(
                        name = "Tabriz",
                        email = "tabriz.abilzada@gmail.com",
                        url = "http://www.javaguides.net"

                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "http://www.javaguides.net"

                )

        ),
        externalDocs = @ExternalDocumentation(
                description = "Spring Boot Employee Documentation",
                url = "http://www.javaguides.net/employee_management"
        )
)
public class AttemptApplication {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    public static void main(String[] args) {
        SpringApplication.run(AttemptApplication.class, args);
    }

}
