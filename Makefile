help:
	@echo "help             Show this help"
	@echo "build            Assembles and tests this project."
	@echo "tasks            Show all tasks"
	@echo "installDist      Generate command"

.PHONY: \
	help \
	build \
	tasks \


build:
	./gradlew build

installDist:
	./gradlew installDist
	@echo $(shell realpath build/install/command/bin/command)

tasks:
	./gradlew tasks --all
