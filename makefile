
RENDERING = Src/Rendering
PHYSICS = Src/Physics
GAME = Src/Game
GAMECORE = Src/Game/Core
NEUTRONSTAR = Src/CodeNameNeutronStar



GAMEVIS = Src/Game/Visuals
GAMEPHY = Src/Game/Physics
GAMECAM = Src/Game/Cameras

GAMEVISUALNODES = $(GAMEVIS)/Nodes
GAMEVISUALRES = $(GAMEVIS)/Resources


GAMEPHYSICSNODES = $(GAMEPHY)/Nodes


GAMECAMERASNODES = $(GAMECAM)/Nodes


USERIO = Src/UserIO
UTILS = Src/Utils

compile:
	rm -r Bin/
	mkdir Bin
	javac -d Bin -cp "Libs/*" $(RENDERING)/*.java $(PHYSICS)/*.java $(USERIO)/*.java $(GAME)/*.java $(GAMECORE)/*.java $(GAMEVISUALNODES)/*.java $(GAMEPHYSICSNODES)/*.java $(GAMECAMERASNODES)/*.java  $(GAMEVISUALRES)/*.java $(NEUTRONSTAR)/*.java


run:
	java -cp "Libs/*;Bin" Game.Core.GameEntry



all: compile run