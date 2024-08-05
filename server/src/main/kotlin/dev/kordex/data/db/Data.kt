/*
 * Copyrighted (Kord Extensions, 2024). Licensed under the EUPL-1.2
 * with the specific provision (EUPL articles 14 & 15) that the
 * applicable law is the Irish law and the Jurisdiction Dublin.
 *
 * Any redistribution must include the specific provision above.
 */

package dev.kordex.data.db

import dev.kordex.data.api.DataCollection
import dev.kordex.data.api.DataEntity
import dev.kordex.data.api.SubmittedDataEntity
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.statements.InsertStatement
import org.jetbrains.exposed.sql.statements.UpdateStatement
import java.util.*

object Data {
	suspend fun upsert(entity: SubmittedDataEntity): UUID {
		if (entity.id == null) {
			return create(entity)
		}

		read(entity.id!!)
			?: return create(entity)

		update(entity)

		return entity.id!!
	}

	suspend fun create(entity: SubmittedDataEntity): UUID = Database.transaction {
		DataTable.insert {
			DataTable.entityToInsertStatement(entity, it)
		}[DataTable.id].value
	}

	suspend fun read(id: UUID): DataEntity? = Database.transaction {
		DataTable.select { DataTable.id eq id }
			.map { DataTable.entityFromRow(it) }
			.singleOrNull()
	}

	suspend fun update(entity: SubmittedDataEntity): Int = Database.transaction {
		DataTable.update({ DataTable.id eq entity.id }) {
			DataTable.entityToUpdateStatement(entity, it)
		}
	}

	suspend fun delete(id: UUID): Int = Database.transaction {
		DataTable.deleteWhere { DataTable.id eq id }
	}
}

fun DataTable.entityToInsertStatement(entity: SubmittedDataEntity, statement: InsertStatement<*>) {
	statement[metricType] = entity.metricType.readable

	statement[devMode] = entity.devMode
	statement[kordExVersion] = entity.kordExVersion
	statement[kordVersion] = entity.kordVersion
	statement[modules] = entity.modules

	statement[botId] = entity.botId
	statement[botName] = entity.botName
	statement[extensionCount] = entity.extensionCount
	statement[guildCount] = entity.guildCount
	statement[intents] = entity.intents
	statement[pluginCount] = entity.pluginCount

	statement[averageEventDelay] = entity.averageEventDelay
	statement[averageEventThroughput] = entity.averageEventThroughput
	statement[cpuCount] = entity.cpuCount
	statement[cpuGhz] = entity.cpuGhz
	statement[eventHandlerTypes] = entity.eventHandlerTypes
	statement[extensions] = entity.extensions
	statement[jvmVersion] = entity.jvmVersion
	statement[kotlinVersion] = entity.kotlinVersion
	statement[plugins] = entity.plugins
	statement[ramAvailable] = entity.ramAvailable
	statement[teamId] = entity.teamId
	statement[teamName] = entity.teamName
	statement[threadCount] = entity.threadCount
}

fun DataTable.entityToUpdateStatement(entity: SubmittedDataEntity, statement: UpdateStatement) {
	statement[metricType] = entity.metricType.readable

	statement[devMode] = entity.devMode
	statement[kordExVersion] = entity.kordExVersion
	statement[kordVersion] = entity.kordVersion
	statement[modules] = entity.modules

	statement[botId] = entity.botId
	statement[botName] = entity.botName
	statement[extensionCount] = entity.extensionCount
	statement[guildCount] = entity.guildCount
	statement[intents] = entity.intents
	statement[pluginCount] = entity.pluginCount

	statement[averageEventDelay] = entity.averageEventDelay
	statement[averageEventThroughput] = entity.averageEventThroughput
	statement[cpuCount] = entity.cpuCount
	statement[cpuGhz] = entity.cpuGhz
	statement[eventHandlerTypes] = entity.eventHandlerTypes
	statement[extensions] = entity.extensions
	statement[jvmVersion] = entity.jvmVersion
	statement[kotlinVersion] = entity.kotlinVersion
	statement[plugins] = entity.plugins
	statement[ramAvailable] = entity.ramAvailable
	statement[teamId] = entity.teamId
	statement[teamName] = entity.teamName
	statement[threadCount] = entity.threadCount
}

fun DataTable.entityFromRow(row: ResultRow) = DataEntity(
	id = row[id].value,

	submitted = row[submitted],
	metricType = DataCollection.fromDB(row[metricType]),

	devMode = row[devMode],
	kordExVersion = row[kordExVersion],
	kordVersion = row[kordVersion],
	modules = row[modules],

	botId = row[botId],
	botName = row[botName],
	extensionCount = row[extensionCount],
	guildCount = row[guildCount],
	intents = row[intents],
	pluginCount = row[pluginCount],

	averageEventDelay = row[averageEventDelay],
	averageEventThroughput = row[averageEventThroughput],
	cpuCount = row[cpuCount],
	cpuGhz = row[cpuGhz],
	eventHandlerTypes = row[eventHandlerTypes],
	extensions = row[extensions],
	jvmVersion = row[jvmVersion],
	kotlinVersion = row[kotlinVersion],
	plugins = row[plugins],
	ramAvailable = row[ramAvailable],
	teamId = row[teamId],
	teamName = row[teamName],
	threadCount = row[threadCount]
)
