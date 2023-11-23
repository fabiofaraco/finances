package br.com.fabio.finances.service

import br.com.fabio.finances.exception.NotFoundException
import br.com.fabio.finances.form.TransactionForm
import br.com.fabio.finances.mapper.TransactionFormMapper
import br.com.fabio.finances.mapper.TransactionViewMapper
import br.com.fabio.finances.repository.TransactionRepository
import br.com.fabio.finances.view.TransactionView
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.stream.Collectors

/**
 * Created by fabiofaraco on 13/11/2023.
 */

@Service
class TransactionService(
    private val repository: TransactionRepository,
    private val transactionViewMapper: TransactionViewMapper,
    private val transactionFormMapper: TransactionFormMapper
) {

    fun list(description: String?, pagination: Pageable): Page<TransactionView> {
        val transactions = if (description == null) {
            repository.findAll(pagination)
        } else {
            repository.findByDescription(description, pagination)
        }

        return transactions.map { t -> transactionViewMapper.map(t) };
    }

    fun findById(id: Long): TransactionView? {
        val transaction = repository.findById(id).orElseThrow { NotFoundException("Transação não encontrada") };
        return transactionViewMapper.map(transaction);
    }

    fun create(form: TransactionForm): TransactionView {
        val transaction = transactionFormMapper.map(form);
        repository.save(transaction);
        return transactionViewMapper.map(transaction);
    }

    fun update(form: TransactionForm) {
        val transaction =
            form.id?.let { id ->
                repository.findById(id).orElseThrow { NotFoundException("Transação não encontrada") }
            };

        transaction?.type = form.type;
        transaction?.amount = form.amount;
        transaction?.description = form.description;
        transaction?.date = form.date;
    }

    fun delete(id: Long) {
        repository.deleteById(id);
    }

}

