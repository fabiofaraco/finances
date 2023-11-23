package br.com.fabio.finances.service

import br.com.fabio.finances.model.UserDetail
import br.com.fabio.finances.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import java.lang.RuntimeException

/**
 * Created by fabiofaraco on 17/11/2023.
 */

@Service
class UserService(private val repository: UserRepository): UserDetailsService {

    override fun loadUserByUsername(username: String?): UserDetails {
        val user = repository.findByEmail(username) ?: throw RuntimeException()
        return UserDetail(user);
    }
}