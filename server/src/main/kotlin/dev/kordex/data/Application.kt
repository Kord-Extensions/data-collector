/*
 * Copyrighted (Kord Extensions, 2024). Licensed under the EUPL-1.2
 * with the specific provision (EUPL articles 14 & 15) that the
 * applicable law is the Irish law and the Jurisdiction Dublin.
 * Any redistribution must include the specific provision above.
 */

package dev.kordex.data

import dev.kordex.data.db.Database
import dev.kordex.data.plugins.configureHTTP
import dev.kordex.data.plugins.configureRouting
import dev.kordex.data.plugins.configureSerialization
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
	Database.db  // Causes migrations and such, when accessed for the first time

	embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
		.start(wait = true)
}

fun Application.module() {
	configureHTTP()
	configureSerialization()
	configureRouting()
}
