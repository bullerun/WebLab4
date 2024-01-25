package ryskal.nikita.controllers

import lombok.RequiredArgsConstructor
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import ryskal.nikita.requsts.SignInRequest
import ryskal.nikita.requsts.SignUpRequest
import ryskal.nikita.responses.JwtAuthenticationResponse
import ryskal.nikita.services.AuthenticationService

@RestController
@RequiredArgsConstructor
class AuthController(private val authenticationService: AuthenticationService) {

    @PostMapping("/register")
    fun signUp(@RequestBody sign: SignUpRequest): JwtAuthenticationResponse {
        return authenticationService.signUp(sign)
    }

    @PostMapping("/login")
    fun signIn(@RequestBody sign: SignInRequest): JwtAuthenticationResponse {
        return authenticationService.signIn(sign)
    }
}