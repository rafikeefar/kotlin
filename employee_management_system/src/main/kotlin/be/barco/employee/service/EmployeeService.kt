package be.barco.employee.service

import be.barco.employee.dto.EmployeeData

interface EmployeeService {
    fun addEmployee(employeeData: EmployeeData): EmployeeData
    fun getEmployee(id: Int): EmployeeData
    fun getEmployee(): List<EmployeeData>
    fun updateEmployee(employeeData: EmployeeData): EmployeeData
    fun deleteEmployee(id: Int)
}