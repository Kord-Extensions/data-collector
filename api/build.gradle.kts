plugins {
	`maven-publish`
	signing

	kordex
}

val sourceJar: Task by tasks.getting

dependencies {
	compileOnly("org.jetbrains.kotlin:kotlin-stdlib-jdk8:2.0.20")
	compileOnly("org.jetbrains.kotlinx:kotlinx-datetime:0.6.0")
	compileOnly("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.1")
}

afterEvaluate {
	publishing {
		repositories {
			maven {
				name = "KordEx"

				url = if (project.version.toString().contains("SNAPSHOT")) {
					uri("https://repo.kordex.dev/snapshots/")
				} else {
					uri("https://repo.kordex.dev/releases/")
				}

				credentials {
					username = project.findProperty("ossrhUsername") as String?
						?: System.getenv("KORDEX_MAVEN_USERNAME")

					password = project.findProperty("ossrhPassword") as String?
						?: System.getenv("KORDEX_MAVEN_PASSWORD")
				}

				version = project.version
			}
		}

		publications {
			create<MavenPublication>("maven") {
				from(components.getByName("java"))

				artifact(sourceJar)

				pom {
					name = "KordEx: Data Collection API"
					description = "Serializers and API definitions for the data collection API"

					url = "https://kordex.dev"

					packaging = "jar"

					scm {
						connection = "scm:git:https://github.com/Kord-Extensions/data-collector.git"
						developerConnection = "scm:git:git@github.com:Kord-Extensions/data-collector.git"
						url = "https://github.com/Kord-Extensions/data-collector.git"
					}

					licenses {
						license {
							name = "EUPL-1.2"
							url = "https://joinup.ec.europa.eu/collection/eupl/eupl-text-eupl-12"
						}
					}

					developers {
						developer {
							id = "gdude2002"
							name = "Gareth Coles"
						}
					}
				}
			}
		}
	}

	signing {
		val signingKey: String? by project ?: return@signing
		val signingPassword: String? by project ?: return@signing

		useInMemoryPgpKeys(signingKey, signingPassword)

		sign(publishing.publications["maven"])
	}
}
