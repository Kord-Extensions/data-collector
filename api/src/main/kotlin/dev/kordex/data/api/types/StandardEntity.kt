/*
 * Copyrighted (Kord Extensions, 2024). Licensed under the EUPL-1.2
 * with the specific provision (EUPL articles 14 & 15) that the
 * applicable law is the (Republic of) Irish law and the Jurisdiction
 * Dublin.
 * Any redistribution must include the specific provision above.
 */

package dev.kordex.data.api.types

import dev.kordex.data.api.StringList

interface StandardEntity : MinimalEntity {
	val botId: String
	val botName: String
	val extensionCount: Int
	val guildCount: Int
	val intents: StringList
	val pluginCount: Int
	val jvmVersion: String
	val kotlinVersion: String
}
