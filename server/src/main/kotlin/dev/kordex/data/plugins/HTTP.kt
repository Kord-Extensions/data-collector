/*
 * Copyrighted (Kord Extensions, 2024). Licensed under the EUPL-1.2
 * with the specific provision (EUPL articles 14 & 15) that the
 * applicable law is the Irish law and the Jurisdiction Dublin.
 * Any redistribution must include the specific provision above.
 */

@file:Suppress("MagicNumber")

package dev.kordex.data.plugins

import io.ktor.server.application.*
import io.ktor.server.plugins.compression.*
import io.ktor.server.plugins.cors.routing.*

fun Application.configureHTTP() {
	install(Compression) {
		gzip {
			priority = 1.0
		}

		deflate {
			priority = 10.0
			minimumSize(1024)
		}
	}

	install(CORS) {
		// Don't allow access via CORS at all!

		hosts.clear()
		headers.clear()
	}
}
