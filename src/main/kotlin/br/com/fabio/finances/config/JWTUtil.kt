package br.com.fabio.finances.config

import br.com.fabio.finances.model.Role
import br.com.fabio.finances.service.UserService
import io.jsonwebtoken.Jwts
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.util.*
import javax.crypto.spec.SecretKeySpec

/**
 * Created by fabiofaraco on 18/11/2023.
 */

@Component
class JWTUtil(private val userService: UserService) {
    private val expiration: Long = 60000;

    @Value("\${jwt.secret}")
    private lateinit var secret: String;

    fun generateToken(username: String, authorities: List<Role>): String? {

        return Jwts.builder().subject(username).claim("role", authorities)
            .expiration(Date(System.currentTimeMillis() + expiration))
            .signWith(getKey(), Jwts.SIG.HS512)
            .compact()
    }

    fun isValid(jwt: String?): Boolean {
        return try {
            Jwts.parser().verifyWith(getKey()).build().parseSignedClaims(jwt)
            true
        } catch (e: IllegalArgumentException) {
            false
        }
    }

    fun getAuthentication(jwt: String?): Authentication {
        val username = Jwts.parser().verifyWith(getKey()).build()
            .parseSignedClaims(jwt).payload.subject
        val authorities = userService.loadUserByUsername(username).authorities

        return UsernamePasswordAuthenticationToken(username, null, authorities)
    }

    fun getKey(): SecretKeySpec = SecretKeySpec(secret.toByteArray(), "HmacSHA512")

}