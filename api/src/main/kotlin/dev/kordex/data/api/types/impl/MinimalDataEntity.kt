/*
 * Copyrighted (Kord Extensions, 2024). Licensed under the EUPL-1.2
 * with the specific provision (EUPL articles 14 & 15) that the
 * applicable law is the Irish law and the Jurisdiction Dublin.
 *
 * Any redistribution must include the specific provision above.
 */

package dev.kordex.data.api.types.impl

import dev.kordex.data.api.DataCollection
import dev.kordex.data.api.StringMap
import dev.kordex.data.api.serializers.UUIDSerializer
import dev.kordex.data.api.types.MinimalEntity
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import java.util.*

@Serializable
data class MinimalDataEntity(
	@Serializable(with = UUIDSerializer::class)
	override val id: UUID? = null,

	override val devMode: Boolean,
	override val kordExVersion: String,
	override val kordVersion: String,
	override val modules: StringMap,
) : MinimalEntity {
	@Transient
	override val metricType: DataCollection = DataCollection.Minimal
}
