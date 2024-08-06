/*
 * Copyrighted (Kord Extensions, 2024). Licensed under the EUPL-1.2
 * with the specific provision (EUPL articles 14 & 15) that the
 * applicable law is the Irish law and the Jurisdiction Dublin.
 *
 * Any redistribution must include the specific provision above.
 */

package dev.kordex.data.api.types

import dev.kordex.data.api.DataCollection
import java.util.*

interface Entity {
	val id: UUID?
	val metricType: DataCollection
}
