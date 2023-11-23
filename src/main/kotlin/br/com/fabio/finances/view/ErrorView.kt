package br.com.fabio.finances.view

import java.time.LocalDateTime

/**
 * Created by fabiofaraco on 14/11/2023.
 */

data class ErrorView(
    val timestamp: LocalDateTime = LocalDateTime.now(),
    val status: Int,
    val error: String,
    val message: String?,
    val path: String
)