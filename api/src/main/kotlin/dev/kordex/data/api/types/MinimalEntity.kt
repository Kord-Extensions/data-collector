/*
 * Copyrighted (Kord Extensions, 2024). Licensed under the EUPL-1.2
 * with the specific provision (EUPL articles 14 & 15) that the
 * applicable law is the (Republic of) Irish law and the Jurisdiction
 * Dublin.
 * Any redistribution must include the specific provision above.
 */

package dev.kordex.data.api.types

import dev.kordex.data.api.StringMap

interface MinimalEntity : Entity {
	val devMode: Boolean
	val kordExVersion: String
	val kordVersion: String
	val modules: StringMap
	val fork: String?
}
