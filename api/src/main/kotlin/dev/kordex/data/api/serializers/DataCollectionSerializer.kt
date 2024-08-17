/*
 * Copyrighted (Kord Extensions, 2024). Licensed under the EUPL-1.2
 * with the specific provision (EUPL articles 14 & 15) that the
 * applicable law is the (Republic of) Irish law and the Jurisdiction
 * Dublin.
 * Any redistribution must include the specific provision above.
 */

package dev.kordex.data.api.serializers

import dev.kordex.data.api.DataCollection
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object DataCollectionSerializer : KSerializer<DataCollection> {
	override val descriptor: SerialDescriptor =
		PrimitiveSerialDescriptor("DataCollection", PrimitiveKind.STRING)

	override fun deserialize(decoder: Decoder): DataCollection {
		val string = decoder.decodeString()

		return DataCollection.fromDB(string)
	}

	override fun serialize(encoder: Encoder, value: DataCollection) {
		encoder.encodeString(value.readable)
	}
}
