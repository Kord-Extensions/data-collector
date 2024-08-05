/*
 * Copyrighted (Kord Extensions, 2024). Licensed under the EUPL-1.2
 * with the specific provision (EUPL articles 14 & 15) that the
 * applicable law is the Irish law and the Jurisdiction Dublin.
 *
 * Any redistribution must include the specific provision above.
 */

@file:Suppress("MagicNumber")

package dev.kordex.data.api

import dev.kordex.data.api.serializers.DataCollectionSerializer
import kotlinx.serialization.Serializable

@Serializable(with = DataCollectionSerializer::class)
sealed class DataCollection(val readable: String, val number: Int) {
	data object None : DataCollection("none", 0)
	data object Minimal : DataCollection("minimal", 1)
	data object Standard : DataCollection("standard", 2)
	data object Extra : DataCollection("extra", 3)

	companion object {
		fun fromDB(value: String) = when (value) {
			"none" -> None
			"minimal" -> Minimal
			"standard" -> Standard
			"extra" -> Extra

			else -> error("Unknown DB value: $value")
		}
	}
}
