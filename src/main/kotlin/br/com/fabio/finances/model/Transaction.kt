package br.com.fabio.finances.model

import jakarta.persistence.*
import java.time.LocalDateTime

/**
 * Created by fabiofaraco on 13/11/2023.
 */

@Entity
data class Transaction (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    var description: String,
    var amount: Double,
    var date: LocalDateTime = LocalDateTime.now(),
    @Enumerated(value=EnumType.STRING)
    var type: TransactionType
)