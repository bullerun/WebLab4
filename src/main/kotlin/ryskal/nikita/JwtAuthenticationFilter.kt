package ryskal.nikita

import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import lombok.RequiredArgsConstructor
import org.springframework.lang.NonNull
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import ryskal.nikita.services.JwtService
import ryskal.nikita.services.UserServices
import java.io.IOException

@Component
@RequiredArgsConstructor
class JwtAuthenticationFilter(private val jwtService: JwtService,
        private val userService: UserServices) : OncePerRequestFilter() {

    companion object {
        const val BEARER_PREFIX = "Bearer "
        const val HEADER_NAME = "Authorization"
    }
    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(
        @NonNull request: HttpServletRequest,
        @NonNull response: HttpServletResponse,
        @NonNull filterChain: FilterChain
    ) {try {


        var authHeader: String? = null
        authHeader = request.getHeader(HEADER_NAME)
        if (authHeader == null || !authHeader.startsWith(BEARER_PREFIX)) {
            filterChain.doFilter(request, response)
            return
        }

        // Обрезаем префикс и получаем имя пользователя из токена
        val jwt = authHeader.substring(BEARER_PREFIX.length)
        val username = jwtService.getNameFromJwt(jwt)
        if (!username.isEmpty() && SecurityContextHolder.getContext().authentication == null) {
            val userDetails = userService.userDetailsService().loadUserByUsername(username)

            // Если токен валиден, то аутентифицируем пользователя
            if (jwtService.isTokenValid(jwt, userDetails)) {
                val context = SecurityContextHolder.createEmptyContext()
                val authToken = UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    userDetails.authorities
                )
                authToken.details = WebAuthenticationDetailsSource().buildDetails(request)
                context.authentication = authToken
                SecurityContextHolder.setContext(context)
            }
        }
    }catch (_:Exception){}
        filterChain.doFilter(request, response)
    }


}