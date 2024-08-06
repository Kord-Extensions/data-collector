import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

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

	java {
		sourceCompatibility = JavaVersion.VERSION_13
		targetCompatibility = JavaVersion.VERSION_13
	}

	withType<KotlinCompile>().configureEach {
		compilerOptions {
			jvmTarget = JvmTarget.JVM_13
		}
	}
}
