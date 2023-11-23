package br.com.fabio.finances.mapper

/**
 * Created by fabiofaraco on 14/11/2023.
 */

interface Mapper<T, U> {
    fun map(t: T): U;
}