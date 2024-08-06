/*
 * Copyrighted (Kord Extensions, 2024). Licensed under the EUPL-1.2
 * with the specific provision (EUPL articles 14 & 15) that the
 * applicable law is the Irish law and the Jurisdiction Dublin.
 * Any redistribution must include the specific provision above.
 */

@file:Suppress("MagicNumber")

package dev.kordex.data.db

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import dev.kordex.data.DB_DRIVER
import dev.kordex.data.DB_PASSWORD
import dev.kordex.data.DB_URL
import dev.kordex.data.DB_USER
import dev.kordex.data.db.Database.db
import kotlinx.coroutines.Dispatchers
import org.flywaydb.core.Flyway
import org.jetbrains.exposed.sql.DatabaseConfig
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.Transaction
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.Database as ExposedDB

object Database {
	private val hikariConfig by lazy {
		HikariConfig().apply {
			driverClassName = DB_DRIVER

			jdbcUrl = "jdbc:$DB_URL"
			username = DB_USER
			password = DB_PASSWORD

			isReadOnly = false
			maximumPoolSize = 6
			transactionIsolation = "TRANSACTION_SERIALIZABLE"
		}
	}

	private val dataSource by lazy { HikariDataSource(hikariConfig) }

	val db by lazy {
		ExposedDB.connect(
			dataSource,

			databaseConfig = DatabaseConfig {
			}
		)
	}

	init {
		val flyway = Flyway.configure()
			.driver(DB_DRIVER)
			.dataSource("jdbc:$DB_URL", DB_USER, DB_PASSWORD)
			.validateMigrationNaming(true)
			.load()

		if (!System.getenv().contains("SKIP_MIGRATIONS")) {
			flyway.migrate()
		}
	}

	suspend fun <T> transaction(body: suspend Transaction.() -> T) =
		newSuspendedTransaction(Dispatchers.IO, db, statement = body)
}

fun main() {
	transaction(db) {
		SchemaUtils.createStatements(DataTable).forEach(::println)
	}
}
