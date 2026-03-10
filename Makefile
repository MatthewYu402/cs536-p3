ANTLR_VERSION := 4.13.2
ANTLR_JAR     := tools/antlr-$(ANTLR_VERSION)-complete.jar
GEN = gen

SRC = $(shell find src -name "*.java") $(shell find gen/madlang -maxdepth 1 -name "*.java")
OUT = out
FILE ?=

.PHONY: all run clean

$(ANTLR_JAR):
	mkdir -p tools
	curl -L -o $(ANTLR_JAR) https://www.antlr.org/download/antlr-$(ANTLR_VERSION)-complete.jar

$(GEN): $(ANTLR_JAR)
	mkdir -p $(GEN)/madlang
	java -jar $(ANTLR_JAR) -package madlang -visitor -o $(GEN)/madlang src/madlang/MadLang.g4
	cp $(GEN)/madlang/src/madlang/*.java $(GEN)/madlang/
	rm -rf $(GEN)/madlang/src

all: $(GEN)
	mkdir -p $(OUT)
	javac -cp $(ANTLR_JAR) -d $(OUT) $(SRC)

run: all
ifndef FILE
	@echo "usage: make run FILE=filename.madl"
	@exit 1
else
	java -cp $(OUT):$(ANTLR_JAR) madlang.Main $(FILE)
endif

clean:
	rm -rf $(OUT) $(GEN)
