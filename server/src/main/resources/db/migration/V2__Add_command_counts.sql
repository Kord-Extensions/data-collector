ALTER TABLE "data"
	ADD chat_command_count INT NULL,
	ADD message_command_count INT NULL,
	ADD slash_command_count INT NULL,
	ADD user_command_count INT NULL;

UPDATE "data"
	SET chat_command_count = NULL,
	    message_command_count = NULL,
	    slash_command_count = NULL,
	    user_command_count = NULL
	WHERE true;
