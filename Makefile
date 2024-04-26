.PHONY: help \
	build \
	installDist \
	tasks \
	clean

help:
	@echo "help             Show this help"
	@echo "build            Assembles and tests this project."
	@echo "installDist      Generate command"
	@echo "tasks            Show all tasks"
	@echo "clean            Clean the project"

build:
	./gradlew build

installDist:
	./gradlew installDist

tasks:
	./gradlew tasks --all

clean:
	./gradlew clean
