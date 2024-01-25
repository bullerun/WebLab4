package ryskal.nikita.services

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.util.*
import javax.crypto.SecretKey

@Service
class JwtService {
    @Value("\${testing.app.secret}")
    private val jwtSigningKey: String? = null

    @Value("\${testing.app.lifetime}")
    private val lifeTime: Long = 0
    fun generateToken(user: UserDetails): String {
        return Jwts.builder()
            .subject(user.username)
            .issuedAt(Date(System.currentTimeMillis()))
            .expiration(Date(System.currentTimeMillis() + lifeTime * 60 * 24))
            .signWith(signingKey)
            .compact()
    }

    fun getNameFromJwt(token: String?): String {
        return Jwts.parser().verifyWith(signingKey).build().parseSignedClaims(token).payload.subject
    }

    private val signingKey: SecretKey
        get() = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSigningKey))

    private fun isTokenExpired(token: String?): Boolean {
        return Jwts.parser().verifyWith(signingKey).build().parseSignedClaims(token).payload.expiration.before(Date())
    }

    fun isTokenValid(token: String?, userDetails: UserDetails): Boolean {
        val userName = getNameFromJwt(token)
        return userName == userDetails.username && !isTokenExpired(token)
    }
}
