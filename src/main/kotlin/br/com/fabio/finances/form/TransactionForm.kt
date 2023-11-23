package br.com.fabio.finances.form

import br.com.fabio.finances.model.TransactionType
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Positive
import java.time.LocalDateTime

/**
 * Created by fabiofaraco on 14/11/2023.
 */

data class TransactionForm(
    val id: Long,

    @field:NotEmpty
    val description: String,

    @field:Positive
    val amount: Double,

    val date: LocalDateTime = LocalDateTime.now(),

    val type: TransactionType
)
