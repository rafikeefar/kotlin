package be.barco.employee.service

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Service
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Service
class JwtFilterService(
    private val jwtTokenService: JwtTokenService,
    private val service: AuthService
) : OncePerRequestFilter() {

    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(
        httpServletRequest: HttpServletRequest,
        httpServletResponse: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val authorizationHeader = httpServletRequest.getHeader("Authorization")
        var token: String? = null
        var userName: String? = null
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            token = authorizationHeader.substring(7)
            userName = jwtTokenService.extractUsername(token)
        }
        if (userName != null && SecurityContextHolder.getContext().authentication == null) {
            val userDetails: UserDetails = service.loadUserByUsername(userName)
            if (jwtTokenService.validateToken(token, userDetails)) {
                val usernamePasswordAuthenticationToken =
                    UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)
                usernamePasswordAuthenticationToken.details =
                    WebAuthenticationDetailsSource().buildDetails(httpServletRequest)
                SecurityContextHolder.getContext().authentication = usernamePasswordAuthenticationToken
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse)
    }
}