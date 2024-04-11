help:
	@echo "help    Show this help"
	@echo "build   Assembles and tests this project."


.PHONY: \
	help \
	build \


build:
	./gradlew build
