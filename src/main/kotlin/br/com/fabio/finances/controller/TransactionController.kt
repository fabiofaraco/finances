package br.com.fabio.finances.controller

import br.com.fabio.finances.form.TransactionForm
import br.com.fabio.finances.service.TransactionService
import br.com.fabio.finances.view.TransactionView
import jakarta.transaction.Transactional
import jakarta.validation.Valid
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder

/**
 * Created by fabiofaraco on 13/11/2023.
 */

@RestController
@RequestMapping("/transactions")
class TransactionController(private val transactionService: TransactionService) {

    @GetMapping
    @Cacheable("transaction-list")
    fun list(
        @RequestParam(required = false) description: String?,
        @PageableDefault(size = 3, sort = ["date"], direction = Sort.Direction.DESC) pagination: Pageable
    ): Page<TransactionView> {
        return transactionService.list(description, pagination);
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): TransactionView? {
        return transactionService.findById(id);
    }

    @PostMapping
    @Transactional
    @CacheEvict(value = ["transaction-list"], allEntries = true)
    fun create(@RequestBody @Valid form: TransactionForm, uriBuilder: UriComponentsBuilder): ResponseEntity<TransactionView> {
        val transactionView = transactionService.create(form);
        val uri = uriBuilder.path("/transactions/${transactionView.id}").build().toUri();
        return ResponseEntity.created(uri).body(transactionView)
    }

    @PutMapping
    @Transactional
    @CacheEvict(value = ["transaction-list"], allEntries = true)
    fun update(@RequestBody @Valid form: TransactionForm) {
        transactionService.update(form);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    @CacheEvict(value = ["transaction-list"], allEntries = true)
    fun delete(@PathVariable id: Long) {
        transactionService.delete(id);
    }
}