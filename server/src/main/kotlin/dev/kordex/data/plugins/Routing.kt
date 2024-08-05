/*
 * Copyrighted (Kord Extensions, 2024). Licensed under the EUPL-1.2
 * with the specific provision (EUPL articles 14 & 15) that the
 * applicable law is the Irish law and the Jurisdiction Dublin.
 *
 * Any redistribution must include the specific provision above.
 */

package dev.kordex.data.plugins

import dev.kordex.data.api.DataEntity
import dev.kordex.data.api.SubmittedDataEntity
import dev.kordex.data.db.Data
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.util.*

fun Application.configureRouting() {
	routing {
		delete("/data/{uuid}") {
			val uuid = UUID.fromString(call.parameters["uuid"]!!)
			val result = Data.delete(uuid)

			if (result > 0) {
				call.respond(HttpStatusCode.Companion.OK)
			} else {
				call.respond(HttpStatusCode.NotFound)
			}
		}

		get("/data/{uuid}") {
			val uuid = try {
				UUID.fromString(call.parameters["uuid"]!!)
			} catch (e: IllegalArgumentException) {
				call.respond(HttpStatusCode.BadRequest, "Invalid ID format")

				return@get
			}

			val entity: DataEntity? = Data.read(uuid)

			if (entity != null) {
				call.respond<DataEntity>(HttpStatusCode.Companion.OK, entity)
			} else {
				call.respond(HttpStatusCode.NotFound)
			}
		}

		post("/data") {
			val entity = call.receive<SubmittedDataEntity>()
			val uuid = Data.upsert(entity)

			call.respond<String>(HttpStatusCode.OK, uuid.toString())
		}
	}
}
