package br.com.fabio.finances

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching

@SpringBootApplication
@EnableCaching
class FinancesApplication

fun main(args: Array<String>) {
	runApplication<FinancesApplication>(*args)
}
