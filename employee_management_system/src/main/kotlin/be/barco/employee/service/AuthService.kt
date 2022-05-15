package be.barco.employee.service

import be.barco.employee.dao.UserRepository
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val userRepository: UserRepository
) : UserDetailsService {

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(userName: String): UserDetails {
        val userDetails: be.barco.employee.entity.UserDetails = userRepository.findByUserName(userName)
        return User(userDetails.userName, userDetails.password, ArrayList())
    }
}