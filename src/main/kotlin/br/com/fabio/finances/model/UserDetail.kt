package br.com.fabio.finances.model

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

/**
 * Created by fabiofaraco on 17/11/2023.
 */

class UserDetail(private val user: User): UserDetails {
    override fun getAuthorities() = user.role

    override fun getPassword() = user.password

    override fun getUsername() =  user.email

    override fun isAccountNonExpired() = true

    override fun isAccountNonLocked() = true

    override fun isCredentialsNonExpired() = true

    override fun isEnabled() = true
}