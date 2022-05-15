package be.barco.employee.configuration

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.annotations.security.SecurityScheme
import org.springframework.context.annotation.Configuration


@Configuration
@OpenAPIDefinition(
    info = Info(
        title = "BARCO Employee Management Application",
        description = "Generate JWT token through /api/authenticate api(credential: admin/admin) and authorize the application using the JWT token",
        version = "v1"
    )
)
@SecurityScheme(
    name = "Authorization",
    type = SecuritySchemeType.HTTP,
    bearerFormat = "JWT",
    scheme = "Bearer"
)
class SwaggerConfiguration