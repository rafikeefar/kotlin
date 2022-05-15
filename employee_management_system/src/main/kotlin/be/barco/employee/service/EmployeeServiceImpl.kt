package be.barco.employee.service

import be.barco.employee.dao.EmployeeRepository
import be.barco.employee.dto.EmployeeData
import be.barco.employee.exception.CustomException
import be.barco.employee.mapper.EmployeeMapperImpl
import be.barco.employee.utils.Constants
import mu.KotlinLogging
import org.springframework.stereotype.Service

@Service
class EmployeeServiceImpl(
    private val employeeRepository: EmployeeRepository,
    private val employeeMapper: EmployeeMapperImpl
) : EmployeeService {

    private val log = KotlinLogging.logger { }

    override fun addEmployee(employeeData: EmployeeData): EmployeeData {
        if (employeeData.id != 0) {
            log.warn("Id is not null for the employee : {}", employeeData.toString())
            throw CustomException(Constants.ID_MUST_NULL)
        }
        val employee = employeeRepository.save(employeeMapper.toEntity(employeeData))
        return employeeMapper.fromEntity(employee)
    }

    override fun getEmployee(id: Int): EmployeeData {
        val employee = employeeRepository.findById(id)
        if (employee.isPresent)
            return employeeMapper.fromEntity(employee.get())
        log.warn("No employee found for the id : {}", id)
        throw CustomException(Constants.EMPLOYEE_NOT_FOUND)
    }

    override fun getEmployee(): List<EmployeeData> {
        val employees = employeeRepository.findAll()
        if (employees.isEmpty()) {
            log.warn(Constants.EMPLOYEE_LIST_EMPTY)
            throw Exception(Constants.EMPLOYEE_LIST_EMPTY)
        }
        return employees.map { employeeMapper.fromEntity(it) }
    }

    override fun updateEmployee(employeeData: EmployeeData): EmployeeData {
        val exists = employeeRepository.existsById(employeeData.id)
        if (exists) {
            employeeRepository.save(employeeMapper.toEntity(employeeData))
            return employeeData
        }
        log.warn("{} not found ", employeeData.toString())
        throw CustomException(Constants.EMPLOYEE_NOT_FOUND)
    }

    override fun deleteEmployee(id: Int) {
        val exists = employeeRepository.existsById(id)
        if (exists)
            return employeeRepository.deleteById(id)
        log.warn("No employee found for the id : {}", id)
        throw CustomException(Constants.EMPLOYEE_NOT_FOUND)
    }

}