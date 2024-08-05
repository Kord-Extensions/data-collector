plugins {
	`kotlin-dsl`
}

repositories {
	google()
	gradlePluginPortal()

	mavenCentral()
}

dependencies {
	implementation(kotlin("gradle-plugin", version = "2.0.0"))
	implementation(kotlin("serialization", version = "2.0.0"))

	implementation("dev.yumi", "yumi-gradle-licenser", "1.2.0")
	implementation("io.gitlab.arturbosch.detekt", "detekt-gradle-plugin", "1.23.6")

	implementation(gradleApi())
	implementation(localGroovy())
}

beforeEvaluate {
	val projectVersion: String by project

	group = "dev.kordex.data"
	version = projectVersion
}
