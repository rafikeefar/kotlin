package be.barco.employee.dao

import be.barco.employee.entity.UserDetails
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<UserDetails, Int?> {
    fun findByUserName(userName: String): UserDetails
}