plugins {
	id("io.ktor.plugin") version "2.3.12"

	kordex
}

application {
	mainClass.set("dev.kordex.data.ApplicationKt")

	val isDevelopment: Boolean = project.ext.has("development")
	applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

dependencies {
	implementation(project(":api"))

	implementation(libs.bundles.logging)
	implementation(libs.bundles.ktor)
	implementation(libs.bundles.database)
}
