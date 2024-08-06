/*
 * Copyrighted (Kord Extensions, 2024). Licensed under the EUPL-1.2
 * with the specific provision (EUPL articles 14 & 15) that the
 * applicable law is the Irish law and the Jurisdiction Dublin.
 * Any redistribution must include the specific provision above.
 */

package dev.kordex.data

val DB_DRIVER: String = System.getenv()["DB_DRIVER"] ?: "org.postgresql.Driver"

val DB_URL: String = System.getenv("DB_URL")
val DB_USER: String = System.getenv("DB_USER")
val DB_PASSWORD: String = System.getenv("DB_PASSWORD")
