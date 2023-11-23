package br.com.fabio.finances.model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

/**
 * Created by fabiofaraco on 17/11/2023.
 */

@Entity
@Table(name="f01_user")
data class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val name: String,
    val email: String,
    val password: String,

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "f01_user_role")
    val role: List<Role> = mutableListOf()
)