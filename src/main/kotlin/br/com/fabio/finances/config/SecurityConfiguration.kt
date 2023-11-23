package br.com.fabio.finances.config

import br.com.fabio.finances.security.JWTAuthenticationFilter
import br.com.fabio.finances.security.JWTLoginFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.Customizer
import org.springframework.security.config.Customizer.withDefaults
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.filter.OncePerRequestFilter

/**
 * Created by fabiofaraco on 17/11/2023.
 */

@Configuration
@EnableWebSecurity
class SecurityConfiguration(
    private val authConfiguration: AuthenticationConfiguration,
    private val jwtUtil: JWTUtil
) {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf { c -> c.disable() }
            .authorizeHttpRequests { auth ->
                auth.requestMatchers("/transactions").hasAuthority("READ_WRITE")
                auth.requestMatchers(HttpMethod.POST, "/login").permitAll()
                auth.anyRequest().authenticated()
            }
            .addFilterBefore(
                JWTLoginFilter(authManager = authConfiguration.authenticationManager, jwtUtil = jwtUtil),
                UsernamePasswordAuthenticationFilter()::class.java
            )
            .addFilterBefore(
                JWTAuthenticationFilter(jwtUtil = jwtUtil),
                UsernamePasswordAuthenticationFilter()::class.java
            )
            .sessionManagement { sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }

        return http.build()
    }

    @Bean
    fun encoder(): PasswordEncoder? {
        return BCryptPasswordEncoder()
    }


}