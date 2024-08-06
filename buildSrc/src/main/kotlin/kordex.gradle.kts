plugins {
	id("dev.yumi.gradle.licenser")
	id("io.gitlab.arturbosch.detekt")

	kotlin("jvm")
	kotlin("plugin.serialization")
}

repositories {
	mavenCentral()
}

dependencies {
	detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.23.6")
	detektPlugins("io.gitlab.arturbosch.detekt:detekt-rules-libraries:1.23.6")
}

detekt {
	buildUponDefaultConfig = true
	config.from(rootProject.file("detekt.yml"))

	autoCorrect = true
}

license {
	rule(rootProject.file("codeformat/HEADER"))
}

val sourceJar = task("sourceJar", Jar::class) {
	dependsOn(tasks["classes"])
	archiveClassifier = "sources"
	from(sourceSets.main.get().allSource)
}

tasks {
	build {
		finalizedBy(sourceJar)
	}
}
