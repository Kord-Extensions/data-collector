/*
 * Copyrighted (Kord Extensions, 2024). Licensed under the EUPL-1.2
 * with the specific provision (EUPL articles 14 & 15) that the
 * applicable law is the Irish law and the Jurisdiction Dublin.
 * Any redistribution must include the specific provision above.
 */

package dev.kordex.data.api

import dev.kordex.data.api.serializers.UUIDSerializer
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class DataEntity(
	@Serializable(with = UUIDSerializer::class)
	val id: UUID? = null,

	val submitted: LocalDateTime = Clock.System.now().toLocalDateTime(TimeZone.UTC),
	val updated: LocalDateTime? = null,

	val metricType: DataCollection,

	// Minimal data
	val devMode: Boolean,
	val kordExVersion: String,
	val kordVersion: String,
	val modules: StringMap,

	// Standard data
	val botId: String? = null,
	val botName: String? = null,
	val extensionCount: Int? = null,
	val guildCount: Int? = null,
	val intents: StringList? = null,
	val pluginCount: Int? = null,

	// Extra data
	val cpuCount: Int? = null,
	val cpuGhz: Float? = null,
	val eventHandlerTypes: StringList? = null,
	val extensions: StringList? = null,
	val jvmVersion: String? = null,
	val kotlinVersion: String? = null,
	val plugins: StringList? = null,
	val ramAvailable: Long? = null,
	val teamId: String? = null,
	val teamName: String? = null,
	val threadCount: Int? = null,
) {
	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (javaClass != other?.javaClass) return false

		other as DataEntity

		if (id != other.id) return false
		if (submitted != other.submitted) return false
		if (metricType != other.metricType) return false
		if (devMode != other.devMode) return false
		if (kordExVersion != other.kordExVersion) return false
		if (kordVersion != other.kordVersion) return false
		if (modules != other.modules) return false
		if (botId != other.botId) return false
		if (botName != other.botName) return false
		if (extensionCount != other.extensionCount) return false
		if (guildCount != other.guildCount) return false

		if (intents != null) {
			if (other.intents == null) return false
			if (!intents.contentEquals(other.intents)) return false
		} else if (other.intents != null) {
			return false
		}

		if (pluginCount != other.pluginCount) return false
		if (cpuCount != other.cpuCount) return false
		if (cpuGhz != other.cpuGhz) return false

		if (eventHandlerTypes != null) {
			if (other.eventHandlerTypes == null) return false
			if (!eventHandlerTypes.contentEquals(other.eventHandlerTypes)) return false
		} else if (other.eventHandlerTypes != null) {
			return false
		}

		if (extensions != null) {
			if (other.extensions == null) return false
			if (!extensions.contentEquals(other.extensions)) return false
		} else if (other.extensions != null) {
			return false
		}

		if (jvmVersion != other.jvmVersion) return false
		if (kotlinVersion != other.kotlinVersion) return false

		if (plugins != null) {
			if (other.plugins == null) return false
			if (!plugins.contentEquals(other.plugins)) return false
		} else if (other.plugins != null) {
			return false
		}

		if (ramAvailable != other.ramAvailable) return false
		if (teamId != other.teamId) return false
		if (teamName != other.teamName) return false
		if (threadCount != other.threadCount) return false

		return true
	}

	override fun hashCode(): Int {
		var result = id?.hashCode() ?: 0

		result = 31 * result + (botId?.hashCode() ?: 0)
		result = 31 * result + (botName?.hashCode() ?: 0)
		result = 31 * result + (cpuCount ?: 0)
		result = 31 * result + (cpuGhz?.hashCode() ?: 0)
		result = 31 * result + (eventHandlerTypes?.contentHashCode() ?: 0)
		result = 31 * result + (extensionCount ?: 0)
		result = 31 * result + (extensions?.contentHashCode() ?: 0)
		result = 31 * result + (guildCount ?: 0)
		result = 31 * result + (intents?.contentHashCode() ?: 0)
		result = 31 * result + (jvmVersion?.hashCode() ?: 0)
		result = 31 * result + (kotlinVersion?.hashCode() ?: 0)
		result = 31 * result + (pluginCount ?: 0)
		result = 31 * result + (plugins?.contentHashCode() ?: 0)
		result = 31 * result + (ramAvailable?.hashCode() ?: 0)
		result = 31 * result + (teamId?.hashCode() ?: 0)
		result = 31 * result + (teamName?.hashCode() ?: 0)
		result = 31 * result + (threadCount ?: 0)
		result = 31 * result + devMode.hashCode()
		result = 31 * result + kordExVersion.hashCode()
		result = 31 * result + kordVersion.hashCode()
		result = 31 * result + metricType.hashCode()
		result = 31 * result + modules.hashCode()
		result = 31 * result + submitted.hashCode()

		return result
	}
}
