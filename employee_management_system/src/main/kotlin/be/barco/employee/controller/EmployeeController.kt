package be.barco.employee.controller

import be.barco.employee.dto.EmployeeData
import be.barco.employee.service.EmployeeService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/employee")
class EmployeeController(
    private val employeeService: EmployeeService
) : SecuredController {

    @PostMapping
    fun addEmployee(@RequestBody employeeData: EmployeeData): ResponseEntity<EmployeeData> =
        ResponseEntity(employeeService.addEmployee(employeeData), HttpStatus.CREATED)

    @GetMapping("/id/{id}")
    fun getEmployee(@PathVariable id: Int): ResponseEntity<EmployeeData> =
        ResponseEntity.ok(employeeService.getEmployee(id))

    @GetMapping("/all")
    fun getEmployee(): ResponseEntity<List<EmployeeData>> =
        ResponseEntity.ok(employeeService.getEmployee())

    @PutMapping
    fun updateEmployee(@RequestBody employeeData: EmployeeData): ResponseEntity<EmployeeData> =
        ResponseEntity(employeeService.updateEmployee(employeeData), HttpStatus.ACCEPTED)

    @DeleteMapping("/id/{id}")
    fun deleteEmployee(@PathVariable id: Int): ResponseEntity<Unit> =
        ResponseEntity(employeeService.deleteEmployee(id), HttpStatus.NO_CONTENT)

}