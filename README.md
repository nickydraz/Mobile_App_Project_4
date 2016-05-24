<<<<<<< HEAD
# Mobile_Project3
Repo for the third project of CSC 410

Project 3 will be your first step into mobile game development. 
- You will be graded on how well the game performs and how enjoyable it is!  

<h2>Items to Complete:</h2>
- All done!

<h2>Completed Steps</h2>
- Player
  - Stage 1: Spawns at a fixed location, fires towards the location the player taps 
  - Stage 2: Spawns at a fixed location. Fires in a straight line
  - Stage 2: Players can drag the avatar and tap to fire
- Multiple types of enemies  
  - Stage 1: Enemies spawn in random locations at random intervals and remain stationary 
  - Stage 2: Enemies spawn as in Stage 1. Enemies fire at player. Enemies move randomly.
- Multiple Stages  
  - Stage 1: Player has X time to kill enemies on the screen. Must kill at least 10 to continue to stage 2  
  - Stage 2: Player must kill all enemies on the screen without dying 
- Count how many times the player shoots
- Count how many enemies the player hits
- Count how many enemies the player kills
- Track time during stages
- Multiple Stages  
  - Stage 1: Player and enemies spawn. each enemy killed adds time.
  - Stage 2: Player and enemies spawn.  
- Notes:  
  - Game Over Stage: display stats and player score  
  - You may use simple hit detection object.dest.contains(object.position)
  - Limit the number of player projectiles on the screen at a given time 
  - Must have multiple enemy graphics
  - You must calculate and display a score based on the statistics captured during play (weights are up to you, but must be logical)
- Sounds:
  - Player fire/kill
  - Background music
  - Enemy fires  
  - Enemy Hit  

<h2>Camel Sprite Coordinates</h2>
- Sprite 1 
  - (0, 0) (77, 0)
  - (0, 52) (77, 52)
- Sprite (old)
  - (80, 183) (160, 183) 
  - (80, 237) (160, 237)
- Sprite 3 (old)
  - (160, 183) (241, 183)
  - (160, 237) (241, 237)

<h2>Snake Green</h2>
- Stationary Sprite
  - (0, 70) (56, 70)
  - (0, 126) (56, 126)
- Attack Sprite
  - (66, 70) (142, 70)
  - (66, 126) (142, 126)

<h2>Snake Blue</h2>
- Attack Sprite
  - (144, 70) (225, 70)
  - (144, 126) (225, 126)
- Stationary Sprite
  - (231, 70) (293, 70)
  - (231, 126) (293, 126)

<h2>Spit</h2>
  - (0, 150) (45, 150)
  - (0, 167) (45, 167)

<h2>Venom</h2>
  - (63, 154) (102, 154)
  - (63, 163) (102, 163)


=======
# Project 4: Finishing Touches

<h2>Things To Complete</h2>
- Add a menu with the following items
  - Stage Selection
  - High Scores: Local
  - High Scores: Global
  - About
- Track the High Scores
  - Store local high scores in an SQLite table
  - Submit high scores to http://craiginsdev.com/highscore/scores.php using an http post (week 8 thursday notes)
  - You will need to submit the following:
    - game=yourgame
    - name=ask player for initials
    - score=final game score
    - datetime=current time
  - Get high scores from http://craiginsdev.com/highscore/scores.php?game=yourgame (project 2 zipcode lookup)
    - Will return a JSON array like:
    - [{"game":"harry","name":"BAC","score":"29916","datetime":"Sun May 22 2016 16:56:55 GMT-0500 (Central Daylight Time)"},{"game":"halo","name":"BAC","score":"17997","datetime":"Sun May 22 2016 17:00:02 GMT-0500 (Central Daylight Time)"},{"game":"awesome game","name":"BAC","score":"14742","datetime":"Sun May 22 2016 17:31:59 GMT-0500 (Central Daylight Time)"},{"game":"ff6","name":"BAC","score":"14742","datetime":"Sun May 22 2016 17:31:59 GMT-0500 (Central Daylight Time)"}]
    - Manually check high scores here: http://craiginsdev.com/highscore/listHighScores.html
- Add a 3rd stage to the game
  - Enemies move towards the players
  - Stage continues until the player dies (gets hit by the enemy)
  - Score continually increases until the player dies
>>>>>>> 83f74c842ef9a68d7891ab1f7b2b72529d55eef9
