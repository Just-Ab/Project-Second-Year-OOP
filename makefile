
RENDERING = Src/Rendering
PHYSICS = Src/Physics
GAME = Src/Game
GAMECORE = Src/Game/Core
NEUTRONSTAR = Src/CodeNameNeutronStar



GAMEVIS = Src/Game/Visuals
GAMEPHY = Src/Game/Physics
GAMECAM = Src/Game/Cameras
GAMEUI = src/Game/UI

GAMEVISUALNODES = $(GAMEVIS)/Nodes
GAMEVISUALRES = $(GAMEVIS)/Resources


NEUTRONSTARWORLD = $(NEUTRONSTAR)/World
NEUTRONSTARBUILDINGS = $(NEUTRONSTAR)/Buildings
NEUTRONSTARECONOMY = $(NEUTRONSTAR)/Economy
NEUTRONSTARGLOBAL = $(NEUTRONSTAR)/Global


GAMEPHYSICSNODES = $(GAMEPHY)/Nodes


GAMECAMERASNODES = $(GAMECAM)/Nodes

HELLO = $(RENDERING)/*.java $(PHYSICS)/*.java $(USERIO)/*.java $(GAME)/*.java $(GAMECORE)/*.java $(GAMEVISUALNODES)/*.java $(GAMEPHYSICSNODES)/*.java $(GAMECAMERASNODES)/*.java  $(GAMEVISUALRES)/*.java $(NEUTRONSTARWORLD)/*.java $(NEUTRONSTARBUILDINGS)/*.java $(NEUTRONSTARECONOMY)/*.java $(NEUTRONSTARGLOBAL)/*.java $(GAMEUI)/*.java

USERIO = Src/UserIO
UTILS = Src/Utils

compile:
	rm -r Bin/
	mkdir Bin
	javac -d Bin -cp "Libs/*" $(HELLO)


run:
	java -cp "Libs/*;Bin" Game.Core.GameEntry



all: compile run