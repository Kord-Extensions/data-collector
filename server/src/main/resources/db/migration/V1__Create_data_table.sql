CREATE TABLE IF NOT EXISTS "data"
(
	id                  uuid PRIMARY KEY,
	submitted           TIMESTAMP NOT NULL,
	updated             TIMESTAMP NULL,

	dev_mode            BOOLEAN   NOT NULL,
	kord_version        TEXT      NOT NULL,
	kordex_version      TEXT      NOT NULL,
	metric_type         TEXT      NOT NULL,
	modules             JSON      NOT NULL,

	bot_id              TEXT      NULL,
	bot_name            TEXT      NULL,
	cpu_count           INT       NULL,
	cpu_ghz             REAL      NULL,
	event_handler_types JSON      NULL,
	extension_count     INT       NULL,
	extensions          JSON      NULL,
	guild_count         INT       NULL,
	intents             JSON      NULL,
	jvm_version         TEXT      NULL,
	kotlin_version      TEXT      NULL,
	plugin_count        INT       NULL,
	plugins             JSON      NULL,
	ram_available       BIGINT    NULL,
	team_id             TEXT      NULL,
	team_name           TEXT      NULL,
	thread_count        INT       NULL,

	CONSTRAINT check_data_0 CHECK (bot_id ~ '\d+')
)

