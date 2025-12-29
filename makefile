
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
NEUTRONSTARUI = $(NEUTRONSTAR)/UI
NEUTRONSTARINTERACTION = $(NEUTRONSTAR)/Interaction
NEUTRONSTARSTATS = $(NEUTRONSTAR)/Stats

GAMEPHYSICSNODES = $(GAMEPHY)/Nodes


GAMECAMERASNODES = $(GAMECAM)/Nodes

NEUTRONSTARBUILDINGSEFFECTS = $(NEUTRONSTARBUILDINGS)/Effects


CORE = 	$(RENDERING)/*.java 					\
		$(PHYSICS)/*.java 						\
		$(USERIO)/*.java 						\
		$(GAME)/*.java 							\
		$(GAMECORE)/*.java 						\
		$(GAMEVISUALNODES)/*.java 				\
		$(GAMEPHYSICSNODES)/*.java 				\
		$(GAMECAMERASNODES)/*.java  			\
		$(GAMEVISUALRES)/*.java 				\
		$(GAMEUI)/*.java

GAME_FILES = 									\
		$(NEUTRONSTARWORLD)/*.java 				\
		$(NEUTRONSTARBUILDINGS)/*.java 			\
		$(NEUTRONSTARECONOMY)/*.java 			\
		$(NEUTRONSTARGLOBAL)/*.java 			\
		$(NEUTRONSTARBUILDINGSEFFECTS)/*.java 	\
		$(NEUTRONSTARUI)/*.java 				\
		$(NEUTRONSTARINTERACTION)/*.java		\
		$(NEUTRONSTARSTATS)/*.java


USERIO = Src/UserIO
UTILS = Src/Utils

compile:
	rm -r Bin/
	mkdir Bin
	javac -d Bin -cp "Libs/*" $(CORE) $(GAME_FILES)


run:
	java -cp "Libs/*;Bin" Game.Core.GameEntry



all: compile run