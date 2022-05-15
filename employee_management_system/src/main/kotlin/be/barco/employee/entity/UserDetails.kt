package be.barco.employee.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class UserDetails(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,
    @Column(unique = true, nullable = false)
    var userName: String,
    @Column(nullable = false)
    var password: String,
    @Column(unique = true, nullable = false)
    var email: String
)
