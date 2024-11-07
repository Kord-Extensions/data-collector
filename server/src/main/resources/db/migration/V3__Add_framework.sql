ALTER TABLE "data"
	ADD framework TEXT NULL;

UPDATE "data"
	SET framework = NULL

	WHERE true;
