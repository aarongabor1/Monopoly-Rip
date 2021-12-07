SYSC 3110 Fall 2021 Group Project
12.06.2021
Monopoly


This project can be reached at:
Website: www.brightspace.carleton.ca
Email:  Aaron Gabor: aarongabor@cmail.carleton.ca
	Brady Norton: bradynorton@cmail.carleton.ca
	Cam Sommerville: camsommerville@cmail.carleton.ca
	Braxton Martin: braxtonmartin@cmail.carleton.ca


Description:
____________
- This game is a simple GUI of a simplified version of Monopoly. 
  The game can handle up to 6 human players and 6 AI players.
  The game will start by asking the user how many players they would like to play the game.
  This program allows the user to be able to customize their own board or
   use the default board made by the creators of the code.
  At any time a user can save their game and load a saved version later.

Set Up Instructions:
____________________
1.	Download the ZIP folder
2.	Unzip the folder
3.      If you want to customize the board follow the Customization Steps, otherwise
        go to the next step
4.	In the folder locate the Monopoly.jar
5.	With your preferred JAVA IDE open the Monopoly.jar file
6.	Run Monoploy.jar
7.	Enter an integer for the number of players you want
8.	Then the Game will start
9.	Players will receive information through a read out.
10.	Players will be prompted with buttons that will allow them to roll, buy a property,
	pay rent, open a window to buy houses and hotels, and end their turn.
11.     Users can click on Game in the top right hand corner to be able to save their game.
12.     To load a saved game click Game in the top right hand corner and click load then select
        the saved file you would like to continue playing.
13.	Game will continue until there is a winner

Customization Steps:
____________________
1.	In the unzipped folder locate the CustomBoard.json
2.	With your preferred IDE open the CustomBoard.json
3.	Follow the instructions in the comments and only change values it says that you can
4.	Once finished making all required updates to the file save it.


Project Files:
______________
The project is made of 13 files:
AI.java			Creates and implements the AI players.
Board.java 		Creates and stores the board for the game.
Controller.java		Implements the functions of the buttons for the GUI and updates it.
Die.java		Creates and implements the dice in the game.
Game.java 		Stores and Runs the main game functions.
Jail.java		Implements Square to add jail functions.
Main.java		Runs the game.
Player.java		Creates and stores player information.
Property.java		Creates and stores property Information.
Railroad.java		Implements Square to add railroad functions.
Square.java		Adds extra functions to Property.java.
Utility.java		Implements Square to add utility property functions.
View.java		Creates and implements the GUI for the game.


PLEASE NOTE: MONOPOLY IS A COPYRIGHTED GAME AND 
THIS RECREATION OF THE GAME IS BEING USED FOR EDUCATIONAL PURPOSES ONLY. 
