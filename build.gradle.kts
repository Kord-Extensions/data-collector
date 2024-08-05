val projectVersion: String by project

group = "dev.kordex.data"
version = projectVersion

subprojects {
	this.group = "dev.kordex.data"
	this.version = projectVersion
}
