SRC = Src
BIN = Bin
LIBS = Libs/*

CORE = $(SRC)/Rendering \
       $(SRC)/Physics \
       $(SRC)/UserIO \
       $(SRC)/Utils \
       $(SRC)/Game \
       $(SRC)/Game/Core \
       $(SRC)/Game/UI \
       $(SRC)/Game/Visuals/Nodes \
       $(SRC)/Game/Visuals/Resources \
       $(SRC)/Game/Physics/Nodes \
       $(SRC)/Game/Cameras/Nodes

NS = $(SRC)/CodeNameNeutronStar

NS_MODULES = $(NS)/World \
             $(NS)/Buildings \
             $(NS)/Buildings/Effects \
             $(NS)/Buildings/Effects/Factor \
             $(NS)/Buildings/Effects/Production \
             $(NS)/Economy \
             $(NS)/Global \
             $(NS)/UI \
             $(NS)/Gameplay \
             $(NS)/Interaction \
             $(NS)/Stats    \
             $(NS)/Units    \
             $(NS)/Menu



CORE_FILES = $(foreach d,$(CORE),$(d)/*.java)
GAME_FILES = $(foreach d,$(NS_MODULES),$(d)/*.java)

compile:
	rm -rf $(BIN)
	mkdir $(BIN)
	javac -d $(BIN) -cp "$(LIBS)" $(CORE_FILES) $(GAME_FILES)

run:
	java -cp "$(LIBS);$(BIN)" Game.Core.GameEntry

all: compile run
