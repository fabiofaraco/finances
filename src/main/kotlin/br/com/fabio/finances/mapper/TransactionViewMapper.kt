package br.com.fabio.finances.mapper

import br.com.fabio.finances.model.Transaction
import br.com.fabio.finances.view.TransactionView
import org.springframework.stereotype.Component

/**
 * Created by fabiofaraco on 14/11/2023.
 */

@Component
class TransactionViewMapper: Mapper<Transaction, TransactionView> {
    override fun map(t: Transaction): TransactionView {
        return TransactionView(
            id = t.id,
            description = t.description,
            amount = t.amount,
            date = t.date,
            type = t.type
        )
    }
}