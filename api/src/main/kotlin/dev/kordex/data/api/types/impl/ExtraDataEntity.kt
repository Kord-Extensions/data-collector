/*
 * Copyrighted (Kord Extensions, 2024). Licensed under the EUPL-1.2
 * with the specific provision (EUPL articles 14 & 15) that the
 * applicable law is the (Republic of) Irish law and the Jurisdiction
 * Dublin.
 * Any redistribution must include the specific provision above.
 */

package dev.kordex.data.api.types.impl

import dev.kordex.data.api.DataCollection
import dev.kordex.data.api.StringList
import dev.kordex.data.api.StringMap
import dev.kordex.data.api.serializers.UUIDSerializer
import dev.kordex.data.api.types.ExtraEntity
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import java.util.*

@Serializable
data class ExtraDataEntity(
	@Serializable(with = UUIDSerializer::class)
	override val id: UUID? = null,

	// Minimal data
	override val devMode: Boolean,
	override val kordExVersion: String,
	override val kordVersion: String,
	override val modules: StringMap,

	// Standard data
	override val botId: String,
	override val botName: String,
	override val extensionCount: Int,
	override val guildCount: Int,
	override val intents: StringList,
	override val pluginCount: Int,
	override val jvmVersion: String,
	override val kotlinVersion: String,

	// Extra data
	override val cpuCount: Int,
	override val cpuGhz: Float,
	override val eventHandlerTypes: StringList,
	override val extensions: StringList,
	override val plugins: StringList,
	override val ramAvailable: Long,
	override val threadCount: Int,

	override val teamId: String? = null,
	override val teamName: String? = null,
) : ExtraEntity {
	@Transient
	override val metricType: DataCollection = DataCollection.Extra

	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (javaClass != other?.javaClass) return false

		other as ExtraDataEntity

		if (teamId != other.teamId) return false
		if (teamName != other.teamName) return false

		if (botId != other.botId) return false
		if (botName != other.botName) return false
		if (cpuCount != other.cpuCount) return false
		if (cpuGhz != other.cpuGhz) return false
		if (devMode != other.devMode) return false
		if (extensionCount != other.extensionCount) return false
		if (guildCount != other.guildCount) return false
		if (id != other.id) return false
		if (jvmVersion != other.jvmVersion) return false
		if (kordExVersion != other.kordExVersion) return false
		if (kordVersion != other.kordVersion) return false
		if (kotlinVersion != other.kotlinVersion) return false
		if (metricType != other.metricType) return false
		if (modules != other.modules) return false
		if (pluginCount != other.pluginCount) return false
		if (ramAvailable != other.ramAvailable) return false
		if (threadCount != other.threadCount) return false

		if (!eventHandlerTypes.contentEquals(other.eventHandlerTypes)) return false
		if (!extensions.contentEquals(other.extensions)) return false
		if (!intents.contentEquals(other.intents)) return false
		if (!plugins.contentEquals(other.plugins)) return false

		return true
	}

	override fun hashCode(): Int {
		var result = id?.hashCode() ?: 0

		result = 31 * result + (teamId?.hashCode() ?: 0)
		result = 31 * result + (teamName?.hashCode() ?: 0)
		result = 31 * result + botId.hashCode()
		result = 31 * result + botName.hashCode()
		result = 31 * result + cpuCount
		result = 31 * result + cpuGhz.hashCode()
		result = 31 * result + devMode.hashCode()
		result = 31 * result + eventHandlerTypes.contentHashCode()
		result = 31 * result + extensionCount
		result = 31 * result + extensions.contentHashCode()
		result = 31 * result + guildCount
		result = 31 * result + intents.contentHashCode()
		result = 31 * result + jvmVersion.hashCode()
		result = 31 * result + kordExVersion.hashCode()
		result = 31 * result + kordVersion.hashCode()
		result = 31 * result + kotlinVersion.hashCode()
		result = 31 * result + metricType.hashCode()
		result = 31 * result + modules.hashCode()
		result = 31 * result + pluginCount
		result = 31 * result + plugins.contentHashCode()
		result = 31 * result + ramAvailable.hashCode()
		result = 31 * result + threadCount

		return result
	}
}
