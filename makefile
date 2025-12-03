
RENDERING = Src/Rendering
Physics = Src/Physics
GAME = Src/Game
GAMECORE = Src/Game/Core
GAMENODES = Src/Game/Nodes
GAMEVISUALNODES = $(GAMENODES)/Visuals
GAMEPHYSICSNODES = $(GAMENODES)/Physics
GAMECAMERASNODES = $(GAMENODES)/Cameras

USERIO = Src/UserIO
UTILS = Src/Utils

compile:
	rm -r Bin/
	mkdir Bin
	javac -d Bin -cp "Libs/*" $(RENDERING)/*.java $(Physics)/*.java $(USERIO)/*.java $(GAME)/*.java $(GAMECORE)/*.java $(GAMEVISUALNODES)/*.java $(GAMEPHYSICSNODES)/*.java $(GAMECAMERASNODES)/*.java


run:
	java -cp "Libs/*;Bin" Game.Core.GameEntry



all: compile run