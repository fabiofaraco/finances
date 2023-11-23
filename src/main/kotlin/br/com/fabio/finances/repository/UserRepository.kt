package br.com.fabio.finances.repository

import br.com.fabio.finances.model.User
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Created by fabiofaraco on 17/11/2023.
 */

interface UserRepository : JpaRepository<User, Long> {

    fun findByEmail(email: String?): User?
}