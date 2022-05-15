package be.barco.employee.mapper

import be.barco.employee.dto.EmployeeData
import be.barco.employee.entity.Employee
import org.springframework.stereotype.Component

@Component
class EmployeeMapperImpl : EmployeeMapper<EmployeeData, Employee> {
    override fun fromEntity(entity: Employee): EmployeeData = EmployeeData(entity.id, entity.name, entity.role)

    override fun toEntity(domain: EmployeeData): Employee = Employee(domain.id, domain.name, domain.role)
}