package br.com.fabio.finances.mapper

import br.com.fabio.finances.form.TransactionForm
import br.com.fabio.finances.model.Transaction
import org.springframework.stereotype.Component

@Component
class TransactionFormMapper: Mapper<TransactionForm, Transaction> {
    override fun map(t: TransactionForm): Transaction {
        return Transaction(
            id = t.id,
            description = t.description,
            amount = t.amount,
            date = t.date,
            type = t.type
        )
    }

}
