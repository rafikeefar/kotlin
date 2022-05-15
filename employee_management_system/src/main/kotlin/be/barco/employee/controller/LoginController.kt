package be.barco.employee.controller

import be.barco.employee.dto.AuthData
import be.barco.employee.exception.CustomException
import be.barco.employee.service.JwtTokenService
import be.barco.employee.utils.Constants
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
class LoginController(
    private val jwtTokenService: JwtTokenService,
    private val authenticationManager: AuthenticationManager
) {
    @PostMapping("/api/authenticate")
    fun generateToken(@RequestBody authData: @Valid AuthData?): ResponseEntity<String> {
        if (authData != null) {
            authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(authData.userName, authData.password)
            )
            return ResponseEntity(jwtTokenService.generateToken(authData.userName), HttpStatus.CREATED)
        }
        throw CustomException(Constants.INVALID_USERNAME_PASSWORD)
    }
}