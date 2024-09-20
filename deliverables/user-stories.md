# Team Undergrad User-Stories
---
## Overview:
- The game should allow for the user to play checkers against a bot with varying difficulties and be able to click and move pieces on an interactive board with savable settings.
### User-Stories
- As a user, a menu with different options is prompted and gives me multiple choices to make. I can adjust the appearance of my layout screen and how difficult my opponent can be. 
- As a player, I want to sign into my account, decide an opponent's difficulty, so that I can start a game where I will be able to move the pieces and have an opponent react with my progress being saved until and after the game concludes. 
- As a user, I would like an option to leave a Checkers game at any given stage, and then resume it again at a later time.
- As a user, I would like an option that activates an alternate play mode where we can move the pieces backwards, not just forwards.
### Developer stories
- As a front-end developer, I want an interface with interactive mechanics so that other users can click and interact with. So, the player will be able to reach the board with a single button. And be able to apply certain settings to the board. 
- As a back-end developer for the Checkers GUI as well as the backend for the AI/bot functionality, I want to design a GUI that will be able to send and receive data from the game mechanics back-end so that the board reacts and functions properly with user input clicks so that the board can highlight to indicate available moves after a piece has been selected.  
- As a back-end developer I want to send and receive data from the front-end so that I can determine the bot difficulty and user-clicks so that I can create a functioning bot that makes moves based on user-selected difficulty. 
- As a developer, we will tackle this issue by adding a database which will store the user's name and their saved game up to the last move made. We can then restore this data from the database when the user wants to reopen their saved game
- As a developer, we will handle this option by adding an alternate play mode with modified rules to allow this style of play. We will also add a visualization helper that will show highlighted paths of all valid moves that the player can make next.
##### Note: Michael wasn't able to participate due to medical absence.
