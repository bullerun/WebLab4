package ryskal.nikita.services

import lombok.RequiredArgsConstructor
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import ryskal.nikita.models.User
import ryskal.nikita.requsts.SignInRequest
import ryskal.nikita.requsts.SignUpRequest
import ryskal.nikita.responses.JwtAuthenticationResponse

@Service
@RequiredArgsConstructor
class AuthenticationService(private val userService: UserServices,
        private val jwtService: JwtService,
        private val passwordEncoder: PasswordEncoder,
        private val authenticationManager: AuthenticationManager) {

    fun signUp(request: SignUpRequest): JwtAuthenticationResponse {
        val user = User(username = request.username, email = request.email, password = passwordEncoder.encode(request.password))
        userService.create(user)
        val jwt = jwtService.generateToken(user)
        return JwtAuthenticationResponse(jwt)
    }

    fun signIn(request: SignInRequest): JwtAuthenticationResponse {
        authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                request.username,
                request.password
            )
        )
        val user = userService.userDetailsService().loadUserByUsername(request.username)
        val jwt = jwtService.generateToken(user)
        return JwtAuthenticationResponse(jwt)
    }
}