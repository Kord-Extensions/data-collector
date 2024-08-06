/*
 * Copyrighted (Kord Extensions, 2024). Licensed under the EUPL-1.2
 * with the specific provision (EUPL articles 14 & 15) that the
 * applicable law is the Irish law and the Jurisdiction Dublin.
 *
 * Any redistribution must include the specific provision above.
 */

package dev.kordex.data.plugins

import dev.kordex.data.api.DataEntity
import dev.kordex.data.api.types.impl.ExtraDataEntity
import dev.kordex.data.api.types.impl.MinimalDataEntity
import dev.kordex.data.api.types.impl.NoneDataEntity
import dev.kordex.data.api.types.impl.StandardDataEntity
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
			val uuid = try {
				UUID.fromString(call.parameters["uuid"]!!)
			} catch (e: IllegalArgumentException) {
				return@delete call.respond(HttpStatusCode.BadRequest, "Invalid ID format")
			}

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
				return@get call.respond(HttpStatusCode.BadRequest, "Invalid ID format")
			}

			val entity: DataEntity? = Data.read(uuid)

			if (entity != null) {
				call.respond<DataEntity>(HttpStatusCode.Companion.OK, entity)
			} else {
				call.respond(HttpStatusCode.NotFound)
			}
		}

		post("/data/none") {
			val entity = call.receive<NoneDataEntity>()

			val uuid = entity.id
				?: return@post call.respond(HttpStatusCode.BadRequest, "ID must not be null")

			val result = Data.delete(uuid)

			if (result > 0) {
				call.respond(HttpStatusCode.Companion.OK)
			} else {
				call.respond(HttpStatusCode.NotFound)
			}
		}

		post("/data/minimal") {
			val entity = call.receive<MinimalDataEntity>()
			val uuid = Data.upsert(entity)

			call.respond<String>(HttpStatusCode.OK, uuid.toString())
		}

		post("/data/standard") {
			val entity = call.receive<StandardDataEntity>()
			val uuid = Data.upsert(entity)

			call.respond<String>(HttpStatusCode.OK, uuid.toString())
		}

		post("/data/extra") {
			val entity = call.receive<ExtraDataEntity>()
			val uuid = Data.upsert(entity)

			call.respond<String>(HttpStatusCode.OK, uuid.toString())
		}
	}
}
