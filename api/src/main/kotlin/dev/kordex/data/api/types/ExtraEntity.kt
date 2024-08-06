/*
 * Copyrighted (Kord Extensions, 2024). Licensed under the EUPL-1.2
 * with the specific provision (EUPL articles 14 & 15) that the
 * applicable law is the Irish law and the Jurisdiction Dublin.
 * Any redistribution must include the specific provision above.
 */

package dev.kordex.data.api.types

import dev.kordex.data.api.StringList

interface ExtraEntity : StandardEntity {
	val cpuCount: Int
	val cpuGhz: Float
	val eventHandlerTypes: StringList
	val extensions: StringList
	val jvmVersion: String
	val kotlinVersion: String
	val plugins: StringList
	val ramAvailable: Long
	val threadCount: Int

	val teamId: String?
	val teamName: String?
}
