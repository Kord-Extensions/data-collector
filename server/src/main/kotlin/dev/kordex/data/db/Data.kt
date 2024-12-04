/*
 * Copyrighted (Kord Extensions, 2024). Licensed under the EUPL-1.2
 * with the specific provision (EUPL articles 14 & 15) that the
 * applicable law is the (Republic of) Irish law and the Jurisdiction
 * Dublin.
 * Any redistribution must include the specific provision above.
 */

package dev.kordex.data.db

import dev.kordex.data.api.DataCollection
import dev.kordex.data.api.DataEntity
import dev.kordex.data.api.types.Entity
import dev.kordex.data.api.types.ExtraEntity
import dev.kordex.data.api.types.MinimalEntity
import dev.kordex.data.api.types.StandardEntity
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.statements.UpdateBuilder
import org.slf4j.LoggerFactory
import java.util.*

object Data {
	private val logger = LoggerFactory.getLogger(Data::class.java)

	suspend fun upsert(entity: Entity): UUID {
		val entityId = entity.id

		if (entityId == null) {
			val uuid = create(entity)

			logger.info("Received entity with no ID - created new UUID: $uuid")

			return uuid
		}

		if (read(entityId) == null) {
			logger.info("Entity $entityId not found - creating it")

			val uuid = create(entity)

			logger.info("Entity $entityId created with UUID $uuid")

			return uuid
		}

		logger.info("Updating entity with ID $entityId")

		update(entity)

		return entity.id!!
	}

	suspend fun create(entity: Entity): UUID = Database.transaction {
		DataTable.insert {
			it[submitted] = Clock.System.now().toLocalDateTime(TimeZone.UTC)

			if (entity.id != null) {
				it[id] = entity.id!!
			}

			DataTable.entityToStatement(entity, it)
		}[DataTable.id].value
	}

	suspend fun read(id: UUID): DataEntity? = Database.transaction {
		DataTable.selectAll()
			.where { DataTable.id eq id }
			.map { DataTable.entityFromRow(it) }
			.singleOrNull()
	}

	suspend fun update(entity: Entity): Int = Database.transaction {
		DataTable.update({ DataTable.id eq entity.id }) {
			it[updated] = Clock.System.now().toLocalDateTime(TimeZone.UTC)

			DataTable.entityToStatement(entity, it)
		}
	}

	suspend fun delete(id: UUID): Int = Database.transaction {
		DataTable.deleteWhere { DataTable.id eq id }
	}
}

fun DataTable.entityToStatement(entity: Entity, statement: UpdateBuilder<*>) {
	statement[metricType] = entity.metricType.readable

	if (entity is MinimalEntity) {
		statement[devMode] = entity.devMode
		statement[kordExVersion] = entity.kordExVersion
		statement[kordVersion] = entity.kordVersion
		statement[modules] = entity.modules
		statement[fork] = entity.fork
	}

	if (entity is StandardEntity) {
		statement[botId] = entity.botId
		statement[botName] = entity.botName
		statement[extensionCount] = entity.extensionCount
		statement[guildCount] = entity.guildCount
		statement[intents] = entity.intents
		statement[pluginCount] = entity.pluginCount
		statement[jvmVersion] = entity.jvmVersion
		statement[kotlinVersion] = entity.kotlinVersion

		statement[chatCommandCount] = entity.chatCommandCount
		statement[messageCommandCount] = entity.messageCommandCount
		statement[slashCommandCount] = entity.slashCommandCount
		statement[userCommandCount] = entity.userCommandCount
	}

	if (entity is ExtraEntity) {
		statement[cpuCount] = entity.cpuCount
		statement[cpuGhz] = entity.cpuGhz
		statement[eventHandlerTypes] = entity.eventHandlerTypes
		statement[extensions] = entity.extensions
		statement[plugins] = entity.plugins
		statement[ramAvailable] = entity.ramAvailable
		statement[teamId] = entity.teamId
		statement[teamName] = entity.teamName
		statement[threadCount] = entity.threadCount
	}
}

fun DataTable.entityFromRow(row: ResultRow) = DataEntity(
	id = row[id].value,

	submitted = row[submitted],
	metricType = DataCollection.fromDB(row[metricType]),

	devMode = row[devMode],
	kordExVersion = row[kordExVersion],
	kordVersion = row[kordVersion],
	modules = row[modules],
	fork = row[fork],

	botId = row[botId],
	botName = row[botName],
	extensionCount = row[extensionCount],
	guildCount = row[guildCount],
	intents = row[intents],
	pluginCount = row[pluginCount],

	chatCommandCount = row[chatCommandCount],
	messageCommandCount = row[messageCommandCount],
	slashCommandCount = row[slashCommandCount],
	userCommandCount = row[userCommandCount],

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
