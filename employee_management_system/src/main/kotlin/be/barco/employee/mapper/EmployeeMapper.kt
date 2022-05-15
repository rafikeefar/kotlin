package be.barco.employee.mapper

interface EmployeeMapper<D, E> {
    fun fromEntity(entity: E): D
    fun toEntity(domain: D): E
}