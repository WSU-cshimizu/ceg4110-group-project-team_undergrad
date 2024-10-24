# Meeting Minutes (10/24/2024)

## Administrivia
* Time: 5:00pm <br> (Time was moved becasue we needed to adjust to Michael scheduled doctor appointment)
* Location: Discord
* Scribe: Vichaka Houi


## Agenda
* Discuss due dates, work.
* Finish diagram work from Tuesday 10/22/2024

## Notes
* Michael had a draft of the database done.
* Barebones for the functionality of the game done.
* Logan has completed a interactive board for the game.
* Vichaka has completed a GUI with a menu to interact with. 
* All three was present. All 3 were definitely on time at the meeting.
* Worked on the diagram assignment on 10/22/2024
* Michael a necessary design in game mechanics called Board integration. It used to integrate the board with the board GUI.
* Updated the name of functionailty in game mechanics to base functionailty. We currently believe it is hard at this point of stage to try and complete a fully functional game.
* Also fixed some incorrect time dates on the SDP.

## Interaction Overview Diagram UML
* Open application leads to a menu of several buttons. Each button is based on a different class outside of the GUI. 
* Menu has several different options/decisions it can make, it can go to settings, choose bot difficulty, and or start game. If it chooses settings, it can go straight to playing the game. Same with the bot difficulty.
* The start game then can make several decisions after the main menu stage. It is able to choose to check/update the board and or exit the application. If it choose to check/update the board, it can make a move or check status. If it makes a move, it loops back to check/update the board. Otherwise, from the check/update position, it can check status. With check status, it can choose to exit application or if the game was not won yet, it goes back to check/update board.
* Can also exit application at the menu.

![InteractiveUML](InteractionOverviewUMLDiagram.png) <br>

## Action Items
* Vichaka
    * Complete bot difficulty selction in GUI
    * May integrate my own code with Logan's or Michael's
* Logan
    * Will begin work on making the bot functionality.
* Michael
    * Beginning database integration.
    * Will also begin integrating board gui with his own game mechanics code.
 

## Signatures
* Vichaka Houi
* Logan Scarberry
* Michael Mowad
