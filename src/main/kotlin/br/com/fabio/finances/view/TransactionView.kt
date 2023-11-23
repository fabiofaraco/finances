package br.com.fabio.finances.view

import br.com.fabio.finances.model.TransactionType
import java.time.LocalDateTime

/**
 * Created by fabiofaraco on 14/11/2023.
 */

data class TransactionView(
    val id: Long?,
    val description: String,
    val amount: Double,
    val date: LocalDateTime,
    val type: TransactionType
)
