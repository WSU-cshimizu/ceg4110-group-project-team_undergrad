# Project Overview Proposal

* **Name**: `Check Out These Checkers`
* **Elevator Pitch**: 
    - Have you ever been bored by the mediocrity of checkers? No more. Now introducing checkers with a new revolutionary ruleset. 
* **N Things of Complexity**:
    - *Interface*
        - Menu
            - An interface design that will allow the user to interact and select various options. Those options include things such as start game, bot difficulty, and or access settings. 
        - Settings
            - Is a tab or button that exists somewhere on the screen. It allows the user to adjust how loud the sound is, and alternate background or interface looks. 
        - Bot
            - An interactive opponent that plays against the current user. 
    - *Database*
        - Txt File
            - A text file that will handle game data such as wins and losses.
            - Saves the game process if it is left uncomplicated.
    - *Backend*
        - AI/bot 
            - An opponent the user can play against.
        - Board
            - Color of the board and the shape of the checkers pieces. 
    - *Game Mechanics*
        - The things that make the game work. 
* **How the Components Fit Together**:
    - We believe the interface will provide an interactive menu to the user. It sends information for all the other pieces that comes after the user selection. Next is the database, it stores and loads information once the menu has gone through. Storing data such as win/loss against specific bots. After that is the backend, which gives the game a board and a bot to play against. Finally, we’ll make the game mechanics that define how the game works. The game mechanics will help further define how the bots are played and give players an interactive experience.
* **Language Framework**:
    - Java
        - We chose Java because as a team we feel confident that we all have enough experience with Java that we can comfortably program a game in it. Most of in the past have also create similar or close like games where their applications can be applied here. Java also has many libraries and other resources available that we can all easily, and cooperatively understand.
* **Predicted Life Cycle/Methodology**:
    - For our group, Scrum was selected. Although in the future or maybe now, we are also considering DSDM and FDD. Each of us are tackling one of each complexity and then further breaking down each into base methods that are functional.