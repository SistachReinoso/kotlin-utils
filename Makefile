.PHONY: help \
	build \
	installDist \
	dependencyUpdates \
	tasks \
	clean

help:
	@echo "help               Show this help"
	@echo "build              Assembles and tests this project."
	@echo "installDist        Generate command"
	@echo "dependencyUpdates  Show the dependencies you can update"
	@echo "tasks              Show all tasks"
	@echo "clean              Clean the project"

build:
	./gradlew build

installDist:
	./gradlew installDist
	myCommand --generate-completion=bash > ~/.bashrc.d/myCommandCompletion.sh

dependencyUpdates:
	./gradlew dependencyUpdates

tasks:
	./gradlew tasks --all

clean:
	./gradlew clean
