package br.com.fabio.finances.repository

import br.com.fabio.finances.model.Transaction
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Created by fabiofaraco on 14/11/2023.
 */

interface TransactionRepository: JpaRepository<Transaction, Long> {

    fun findByDescription(description: String, pagination: Pageable): Page<Transaction>;

    //@Query(native = true, "SELECT new br.com.fabio.ClasseParaRetornoDeDados")
    //fun getReport(): List<ClasseParaRetornoDeDados>


}
