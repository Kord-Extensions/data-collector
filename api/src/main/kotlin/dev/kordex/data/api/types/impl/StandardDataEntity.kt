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
import dev.kordex.data.api.serializers.KXUUIDSerializer
import dev.kordex.data.api.types.StandardEntity
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import java.util.*

@Serializable
data class StandardDataEntity(
	@Serializable(with = KXUUIDSerializer::class)
	override val id: UUID? = null,

	// Minimal data
	override val devMode: Boolean,
	override val kordExVersion: String,
	override val kordVersion: String,
	override val modules: StringMap,
	override val fork: String? = null,

	// Standard data
	override val botId: String,
	override val botName: String,
	override val extensionCount: Int,
	override val guildCount: Int,
	override val intents: StringList,
	override val pluginCount: Int,
	override val jvmVersion: String,
	override val kotlinVersion: String,

	override val chatCommandCount: Int?,
	override val messageCommandCount: Int?,
	override val slashCommandCount: Int?,
	override val userCommandCount: Int?,
) : StandardEntity {
	@Transient
	override val metricType: DataCollection = DataCollection.Standard

	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (javaClass != other?.javaClass) return false

		other as StandardDataEntity

		if (fork != other.fork) return false
		if (botId != other.botId) return false
		if (botName != other.botName) return false
		if (devMode != other.devMode) return false
		if (extensionCount != other.extensionCount) return false
		if (guildCount != other.guildCount) return false
		if (id != other.id) return false
		if (kordExVersion != other.kordExVersion) return false
		if (kordVersion != other.kordVersion) return false
		if (metricType != other.metricType) return false
		if (modules != other.modules) return false
		if (pluginCount != other.pluginCount) return false

		if (chatCommandCount != other.chatCommandCount) return false
		if (messageCommandCount != other.messageCommandCount) return false
		if (slashCommandCount != other.slashCommandCount) return false
		if (userCommandCount != other.userCommandCount) return false

		if (!intents.contentEquals(other.intents)) return false

		return true
	}

	override fun hashCode(): Int {
		var result = id?.hashCode() ?: 0

		result = 31 * result + botId.hashCode()
		result = 31 * result + botName.hashCode()
		result = 31 * result + devMode.hashCode()
		result = 31 * result + extensionCount
		result = 31 * result + guildCount
		result = 31 * result + intents.contentHashCode()
		result = 31 * result + kordExVersion.hashCode()
		result = 31 * result + kordVersion.hashCode()
		result = 31 * result + metricType.hashCode()
		result = 31 * result + modules.hashCode()
		result = 31 * result + fork.hashCode()
		result = 31 * result + pluginCount

		result = 31 * result + (chatCommandCount ?: 0)
		result = 31 * result + (messageCommandCount ?: 0)
		result = 31 * result + (slashCommandCount ?: 0)
		result = 31 * result + (userCommandCount ?: 0)

		return result
	}
}
