/*
 * Copyrighted (Kord Extensions, 2024). Licensed under the EUPL-1.2
 * with the specific provision (EUPL articles 14 & 15) that the
 * applicable law is the (Republic of) Irish law and the Jurisdiction
 * Dublin.
 * Any redistribution must include the specific provision above.
 */

package dev.kordex.data.db

import dev.kordex.data.api.StringList
import dev.kordex.data.api.StringMap
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlinx.serialization.json.Json
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.json.json
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object DataTable : UUIDTable("data") {
	val submitted = datetime("submitted").clientDefault { Clock.System.now().toLocalDateTime(TimeZone.UTC) }
	val updated = datetime("updated").nullable()

	val metricType = text("metric_type")

	// Minimal data
	val devMode = bool("dev_mode")
	val kordExVersion = text("kordex_version")
	val kordVersion = text("kord_version")
	val modules = json<StringMap>("modules", Json)

	// Standard data
	val botId = text("bot_id").nullable().check { it regexp "\\d+" }
	val botName = text("bot_name").nullable()
	val extensionCount = integer("extension_count").nullable()
	val guildCount = integer("guild_count").nullable()
	val intents = json<StringList>("intents", Json).nullable()
	val pluginCount = integer("plugin_count").nullable()

	// Standard: Command Counts
	val chatCommandCount = integer("chat_command_count").nullable()
	val messageCommandCount = integer("message_command_count").nullable()
	val slashCommandCount = integer("slash_command_count").nullable()
	val userCommandCount = integer("user_command_count").nullable()

	// Extra data
	val cpuCount = integer("cpu_count").nullable()
	val cpuGhz = float("cpu_ghz").nullable()
	val eventHandlerTypes = json<StringList>("event_handler_types", Json).nullable()
	val extensions = json<StringList>("extensions", Json).nullable()
	val jvmVersion = text("jvm_version").nullable()
	val kotlinVersion = text("kotlin_version").nullable()
	val plugins = json<StringList>("plugins", Json).nullable()
	val ramAvailable = long("ram_available").nullable()
	val teamId = text("team_id").nullable()
	val teamName = text("team_name").nullable()
	val threadCount = integer("thread_count").nullable()
}
