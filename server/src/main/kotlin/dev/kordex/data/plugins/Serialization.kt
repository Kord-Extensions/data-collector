/*
 * Copyrighted (Kord Extensions, 2024). Licensed under the EUPL-1.2
 * with the specific provision (EUPL articles 14 & 15) that the
 * applicable law is the (Republic of) Irish law and the Jurisdiction
 * Dublin.
 * Any redistribution must include the specific provision above.
 */

package dev.kordex.data.plugins

import dev.kordex.data.api.DataCollection
import dev.kordex.data.api.serializers.DataCollectionSerializer
import dev.kordex.data.api.serializers.UUIDSerializer
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.contextual
import java.util.*

fun Application.configureSerialization() {
	install(ContentNegotiation) {
		json(
			Json {
				serializersModule = SerializersModule {
					include(Json.serializersModule)

					contextual<DataCollection>(DataCollectionSerializer)
					contextual<UUID>(UUIDSerializer)
				}
			}
		)
	}
}
